package ccc.drones_training.sim.steps;

import ccc.drones_training.drone.DroneController;
import ccc.drones_training.sim.Check;
import ccc.drones_training.sim.Status;

public class BreakToZeroMetersPerSecondOnZ extends Step {

	public BreakToZeroMetersPerSecondOnZ(DroneController controller) {
		super(controller);
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(0.0);
	}

	@Override
	public Check getCheck() {

		return new Check(getController()) {

			@Override
			public boolean check(Status status) {
				return status.getVz() < 0.1 && status.getVz() > -0.1;
			}
		};
	}

	@Override
	public String toString() {
		return "Breaking to Zero m/s on Z. Drone: " + getController().getDrone();
	}
}