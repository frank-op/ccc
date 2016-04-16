package ccc.drones.sim;

import ccc.drones.drone.DroneController;

public abstract class Check {

	private final DroneController droneController;

	public Check(DroneController droneController) {
		this.droneController = droneController;
	}

	public abstract boolean check(Status status);

	public void callBack() {
		droneController.nextStep();
	}

	public DroneController getDroneController() {
		return droneController;
	}
}