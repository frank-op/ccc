package ccc.drones.drone;

import static ccc.drones.sim.SimulatorCommunicator.communication;

import java.util.List;

import ccc.drones.sim.Check;
import ccc.drones.sim.MissionControl;
import ccc.drones.sim.Scenario;
import ccc.drones.sim.steps.Step;

public class DroneController {

	public static final Double SMALL_TICK_TIME = 0.01;
	public static final Double FULL_THROTTLE = 0.8;
	public static final Double DROP_THROTTLE = 0.0;
	public static final Double MAX_SPEED_UP = 8.0;
	public static final Double MAX_SPEED_DOWN = -5.0;
	public static final Double MAX_SPEED_X = 3.0;
	public static final Double MAX_SPEED_Y = 3.0;
	public static final Double TURN_ANGLE = 0.5;
	public static final Double BREAK_ANGLE = 5.0;

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
			logCurrentStep(step);
			addCurrentCheckToMissionControl(step);
			step.doIt();
		} else {
			System.out.println("Scenario of drone " + drone + " is done!");
		}
	}

	private void logCurrentStep(Step step) {
		System.out.println("EXECUTING STEP: (" + step.getClass().getSimpleName() + ") " + step);
	}

	private void addCurrentCheckToMissionControl(Step step) {
		Check check = step.getCheck();
		MissionControl.instance().addCheck(check);
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

	public void landThatDamnThing() {
		synchronized (MissionControl.communicationLock) {
			System.out.println("Landing drone " + drone);
			communication().sendToSimulator("LAND " + drone.getDroneId());
			String response = communication().getNextStringFromSimulator();
			System.out.println("LANDOk: " + response + "\n");
		}
	}

	public void turnOnX(double tiltAngle) {
		synchronized (MissionControl.communicationLock) {
			communication().sendToSimulator("TURN " + drone.getDroneId() + " " + tiltAngle + " " + 0.0 + " " + 0.0);
			String response = communication().getNextStringFromSimulator();
			System.out.println("TurnOK: " + response);
		}
	}

	public void turnOnY(double tiltAngle) {
		synchronized (MissionControl.communicationLock) {
			communication().sendToSimulator("TURN " + drone.getDroneId() + " " + 0.0 + " " + tiltAngle + " " + 0.0);
			String response = communication().getNextStringFromSimulator();
			System.out.println("TurnOK: " + response);
		}
	}

	public void setScenario(Scenario scenario) {
		steps = scenario.getSteps();
	}

	public Drone getDrone() {
		return drone;
	}
}