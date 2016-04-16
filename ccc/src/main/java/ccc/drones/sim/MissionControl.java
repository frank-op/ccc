package ccc.drones.sim;

import static ccc.drones.sim.SimulatorCommunicator.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ccc.drones.drone.Drone;

public class MissionControl {

	private static final Double SMALL_TICK_TIME = 0.01;

	private List<Check> checks = new ArrayList<>();
	private ExecutorService executor = Executors.newSingleThreadExecutor();

	public static Object communicationLock = new Object();

	private static MissionControl instance;

	public static MissionControl instance() {
		if (instance == null) {
			synchronized (MissionControl.class) {
				if (instance == null) {
					instance = new MissionControl();
					instance.start();
				}
			}
		}
		return instance;
	}

	public void start() {
		executor.execute(new WaitForCheckRunnables());
	}

	public void addCheck(Check check) {
		checks.add(check);
	}

	public Double getCurrentTime() {
		return tick(0.0);
	}

	private Double tick() {
		return tick(SMALL_TICK_TIME);
	}

	private Double tick(Double timeInSeconds) {

		synchronized (MissionControl.communicationLock) {
			System.out.println("ADD TICK " + timeInSeconds);
			communication().sendToSimulator("TICK " + timeInSeconds);
			String response = communication().getNextStringFromSimulator();
			System.out.println("TickSuccess " + response + "\n");
			if ("SUCCESS".equals(response)) {
				System.out.println("LEVEL DONE!");
				System.exit(0);
			}
			return Double.valueOf(response);
		}

	}

	private Status getStatusForDrone(Drone drone) {

		synchronized (MissionControl.communicationLock) {
			communication().sendToSimulator("STATUS " + drone.getDroneId());
			String response = communication().getNextStringFromSimulator();
			return new Status(response);
		}

	}

	private final class WaitForCheckRunnables implements Runnable {

		@Override
		public void run() {
			while (checks.isEmpty()) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			executor.execute(new RunChecksRunnable());
		}
	}

	private final class RunChecksRunnable implements Runnable {

		@Override
		public void run() {

			while (!checks.isEmpty()) {

				List<Check> checksToCallAndRemove = new ArrayList<>();

				for (Check check : checks) {
					Drone drone = check.getDroneController().getDrone();

					Status statusForDrone = getStatusForDrone(drone);

					boolean doesCheckOut = check.check(statusForDrone);
					if (doesCheckOut) {
						checksToCallAndRemove.add(check);
					}
				}

				checksToCallAndRemove.stream().forEach(x -> x.callBack());
				checks.removeAll(checksToCallAndRemove);
				tick();
			}

			executor.execute(new WaitForCheckRunnables());
		}
	}
}