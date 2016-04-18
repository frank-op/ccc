package ccc.drones_training.sim.steps;

import ccc.drones_training.drone.DroneController;
import ccc.drones_training.sim.Check;
import ccc.drones_training.sim.Status;

final class TrueCheck extends Check {
	TrueCheck(DroneController droneController) {
		super(droneController);
	}

	@Override
	public boolean check(Status status) {
		return true;
	}
}