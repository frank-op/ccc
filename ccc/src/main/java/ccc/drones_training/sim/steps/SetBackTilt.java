package ccc.drones_training.sim.steps;

import ccc.drones_training.drone.DroneController;
import ccc.drones_training.sim.Check;

public class SetBackTilt extends Step {

	private double tiltX;
	private double tiltY;

	public SetBackTilt(DroneController controller, double tiltX, double tiltY) {
		super(controller);
		this.tiltX = tiltX;
		this.tiltY = tiltY;
	}

	@Override
	public Check getCheck() {
		return new TrueCheck(getController());
	}

	@Override
	public void doIt() {
		getController().turnOnXY(tiltX, tiltY);
	}

	@Override
	public String toString() {
		return "Setting tiltX " + tiltX + " tiltY " + tiltY;
	}
}