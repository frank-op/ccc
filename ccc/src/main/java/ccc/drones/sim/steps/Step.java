package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;

public abstract class Step {

	private final DroneController controller;

	public Step(DroneController controller) {
		super();
		this.controller = controller;
	}

	public abstract Check getCheck();

	public abstract void doIt();

	public DroneController getController() {
		return controller;
	}
}