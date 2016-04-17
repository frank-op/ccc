package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class AccelerateOnZToMetersPerSecond extends Step {

	private Double velocity;

	public AccelerateOnZToMetersPerSecond(DroneController controller, Double velocity) {
		super(controller);
		this.velocity = velocity;
	}

	@Override
	public Check getCheck() {
		return new VelocityOnZOverGivenValue(getController());
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(DroneController.FULL_THROTTLE);
	}

	private final class VelocityOnZOverGivenValue extends Check {

		public VelocityOnZOverGivenValue(DroneController droneController) {
			super(droneController);
		}

		@Override
		public boolean check(Status status) {
			return velocity < status.getVz();
		}
	}

	@Override
	public String toString() {
		return "Accelerating to " + velocity + " on Z. Drone: " + getController().getDrone();
	}
}