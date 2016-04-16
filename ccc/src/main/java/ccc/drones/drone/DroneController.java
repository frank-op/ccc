package ccc.drones.drone;

import static ccc.drones.sim.SimulatorCommunicator.communication;

import java.util.List;

import ccc.drones.sim.Check;
import ccc.drones.sim.MissionControl;
import ccc.drones.sim.Scenario;
import ccc.drones.sim.Status;
import ccc.drones.sim.steps.Step;

public class DroneController {

	public static final Double SMALL_TICK_TIME = 0.01;
	public static final Double FULL_THROTTLE = 1.0;
	public static final Double DROP_THROTTLE = 0.0;
	public static final Double MAX_SPEED_UP = 12.0;
	public static final Double MAX_SPEED_DOWN = -5.0;

	private final Drone drone;

	private int currentStep = -1;
	private List<Step> steps;

	public DroneController(Drone drone) {
		this.drone = drone;
	}

	public void startScenario() {
		nextStep();
	}

	public void nextStep() {
		currentStep++;
		if (currentStep < steps.size()) {
			Step step = steps.get(currentStep);
			Check check = step.getCheck();
			MissionControl.instance().addCheck(check);
			step.doIt();
		} else {
			System.out.println("Scenario of drone " + drone + " is done!");
		}
	}

	public void setScenario(Scenario scenario) {
		steps = scenario.getSteps();
	}

	public void sendDroneToMinZ(Double z) {
		changeSpeedOnZToMetersPerSecond(MAX_SPEED_UP);
		tickAndWeightForMinZToBeMet(z);
		hoverOnZ();
	}

	public void sendDroneToMaxZ(Double z) {
		changeSpeedOnZToMetersPerSecond(MAX_SPEED_DOWN);
		tickAndWeightForMaxZToBeMet(z + 3);
		hoverOnZ();
		changeSpeedOnZToMetersPerSecond(-0.45);
		tickAndWeightForMaxZToBeMet(z);
		hoverOnZ();
	}

	public void land() {
		Integer droneId = drone.getDroneId();
		System.out.println("LAND drone: " + droneId);
		changeThrottleForDrone(0.0);
		communication().sendToSimulator("LAND " + droneId);
		String response = communication().getNextStringFromSimulator();
		tick(1.0);
		System.out.println("LANDOk: " + response + "\n");
	}

	public void hoverOnZ() {
		changeSpeedOnZToMetersPerSecond(0.0);
	}

	public void hoverOnZ(Double seconds) {
		hoverOnZ();
		tick(seconds);
	}

	public void changeSpeedOnZToMetersPerSecond(Double nextVZ) {

		Double currentVZForDrone = getCurrentVZForDrone();
		if (nextVZ < currentVZForDrone) {
			brakeDroneToVZ(nextVZ);
		} else {
			accelerateToVZ(nextVZ);
		}
	}

	private void brakeDroneToVZ(Double vz) {
		changeThrottleForDrone(DROP_THROTTLE);
		while (vz < getCurrentVZForDrone()) {
			tick(SMALL_TICK_TIME);
		}
		changeThrottleForDrone(drone.getThrottleToOvercomeGravity());
		tick(SMALL_TICK_TIME);
	}

	private void accelerateToVZ(Double vz) {
		changeThrottleForDrone(FULL_THROTTLE);
		while (vz > getCurrentVZForDrone()) {
			tick(SMALL_TICK_TIME);
		}
		changeThrottleForDrone(drone.getThrottleToOvercomeGravity());
		tick(SMALL_TICK_TIME);
	}

	public void changeThrottleForDrone(Double throttle) {
		synchronized (MissionControl.sync) {
			Integer droneId = drone.getDroneId();
			System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneId);
			communication().sendToSimulator("THROTTLE " + droneId + " " + throttle);
			String response = communication().getNextStringFromSimulator();
			System.out.println("ThrottleOK: " + response + "\n");
		}
	}

	private void tickAndWeightForMinZToBeMet(Double z) {
		Double currentZForDrone = getCurrentZForDrone();

		while (currentZForDrone < z) {
			tick(SMALL_TICK_TIME);
			currentZForDrone = getCurrentZForDrone();
		}
	}

	private void tickAndWeightForMaxZToBeMet(Double z) {
		Double currentZForDrone = getCurrentZForDrone();

		while (currentZForDrone > z) {
			tick(SMALL_TICK_TIME);
			currentZForDrone = getCurrentZForDrone();
		}
	}

	private void tick(Double tick) {
		System.out.println("ADD TICK " + tick);
		communication().sendToSimulator("TICK " + tick);
		String response = communication().getNextStringFromSimulator();
		System.out.println("TickSuccess " + response + "\n");
		if ("SUCCESS".equals(response)) {

			System.out.println("SUCCESS! WILL SHUTDOWN");
			System.exit(0);
		}
	}

	private Double getCurrentZForDrone() {
		double z = getStatusForDrone().getZ();
		System.out.println("CURRENT Z: " + z + " for Drone: " + drone.getDroneId() + " \n");
		return z;
	}

	public Double getCurrentVZForDrone() {
		Double vz = getStatusForDrone().getVz();
		System.out.println("CURRENT VZ: " + vz + " for Drone: " + drone.getDroneId() + " \n");
		return vz;
	}

	private Status getStatusForDrone() {

		communication().sendToSimulator("STATUS " + drone.getDroneId());
		String response = communication().getNextStringFromSimulator();
		return new Status(response);
	}

	public Drone getDrone() {
		return drone;
	}
}