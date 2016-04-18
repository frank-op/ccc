package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;

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