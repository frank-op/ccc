package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class SetThrottleToOvercomeGravity extends Step {

	public SetThrottleToOvercomeGravity(DroneController controller) {
		super(controller);
	}

	@Override
	public Check getCheck() {

		return new Check(getController()) {

			@Override
			public boolean check(Status status) {

				return true;
			}
		};
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(getController().getDrone().getThrottleToOvercomeGravity());
	}
}