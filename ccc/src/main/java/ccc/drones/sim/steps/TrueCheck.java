package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

final class TrueCheck extends Check {
	TrueCheck(DroneController droneController) {
		super(droneController);
	}

	@Override
	public boolean check(Status status) {
		return true;
	}
}