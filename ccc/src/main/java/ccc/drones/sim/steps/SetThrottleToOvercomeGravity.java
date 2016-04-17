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
		return new TrueCheck(getController());
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(getController().getDrone().getThrottleToOvercomeGravity());
	}

	@Override
	public String toString() {
		return "Set throttle to overcome drones gravity pull: " + getController().getDrone();
	}
}