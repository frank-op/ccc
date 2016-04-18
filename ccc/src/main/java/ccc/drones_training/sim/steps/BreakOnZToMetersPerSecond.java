package ccc.drones_training.sim.steps;

import ccc.drones_training.drone.DroneController;
import ccc.drones_training.sim.Check;
import ccc.drones_training.sim.Status;

public class BreakOnZToMetersPerSecond extends Step {

	private Double velocity;

	public BreakOnZToMetersPerSecond(DroneController controller, Double velocity) {
		super(controller);
		this.velocity = velocity;
	}

	@Override
	public Check getCheck() {
		return new VelocityOnZOverGivenValue(getController());
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(0.0);
	}

	private final class VelocityOnZOverGivenValue extends Check {

		public VelocityOnZOverGivenValue(DroneController droneController) {
			super(droneController);
		}

		@Override
		public boolean check(Status status) {
			return velocity > status.getVz();
		}
	}

	@Override
	public String toString() {
		return "Breaking to " + velocity + " m/s on Z. Drone: " + getController().getDrone();
	}
}