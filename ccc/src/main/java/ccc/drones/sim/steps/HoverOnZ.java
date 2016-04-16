package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class HoverOnZ extends Step {

	public HoverOnZ(DroneController controller) {
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
}