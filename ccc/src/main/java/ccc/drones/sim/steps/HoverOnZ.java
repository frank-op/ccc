package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;

public class HoverOnZ extends Step {

	public HoverOnZ(DroneController controller) {
		super(controller);
	}

	@Override
	public void doIt() {
		getController().changeSpeedOnZToMetersPerSecond(0.0);
	}

	@Override
	public Check getCheck() {
		return null;
	}
}