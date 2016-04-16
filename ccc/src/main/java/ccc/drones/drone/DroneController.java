package ccc.drones.drone;

import static ccc.drones.sim.SimulatorCommunicator.communication;

import java.util.List;

import ccc.drones.sim.Check;
import ccc.drones.sim.MissionControl;
import ccc.drones.sim.Scenario;
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

	public void changeThrottleForDrone(Double throttle) {
		synchronized (MissionControl.communicationLock) {
			Integer droneId = drone.getDroneId();
			System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneId);
			communication().sendToSimulator("THROTTLE " + droneId + " " + throttle);
			String response = communication().getNextStringFromSimulator();
			System.out.println("ThrottleOK: " + response + "\n");
		}
	}

	public void setScenario(Scenario scenario) {
		steps = scenario.getSteps();
	}

	public Drone getDrone() {
		return drone;
	}
}