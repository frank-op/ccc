package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.drone.Target;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class ReachTarget extends Step {

	private Target target;

	public ReachTarget(DroneController controller, Target target) {
		super(controller);
		this.target = target;
	}

	@Override
	public Check getCheck() {
		return new Check(getController()) {

			@Override
			public boolean check(Status status) {

				Double targetX = Math.abs(target.getX());
				Double targetY = Math.abs(target.getY());

				Double currentX = status.getX();
				Double currentY = status.getY();

				Double distX = Math.abs(targetX - currentX);
				Double distY = Math.abs(targetY - currentY);

				return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) <= 0.5;
			}
		};
	}

	@Override
	public void doIt() {
		// do nothing...
	}

	@Override
	public String toString() {
		return "Waiting to reach target: " + target;
	}
}