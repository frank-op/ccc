package ccc.drones_training.sim.steps;

import ccc.drones_training.drone.DroneController;
import ccc.drones_training.sim.Check;
import ccc.drones_training.sim.Status;

public class LandThatDrone extends Step {

	public LandThatDrone(DroneController controller) {
		super(controller);
	}

	@Override
	public Check getCheck() {
		return new TrueCheck(getController());
	}

	@Override
	public void doIt() {
		getController().changeThrottleForDrone(0.0);
		getController().landThatDamnThing();
	}

	@Override
	public String toString() {
		return "Landing the drone " + getController().getDrone();
	}
}