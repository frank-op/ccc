package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class BrakeOnXY extends Step {

	private Double breakTiltX;
	private Double breakTiltY;

	public BrakeOnXY(DroneController controller, Double breakTiltX, Double breakTiltY) {
		super(controller);
		this.breakTiltX = breakTiltX;
		this.breakTiltY = breakTiltY;
	}

	@Override
	public Check getCheck() {
		return new Check(getController()) {

			@Override
			public boolean check(Status status) {
				double currentVX = Math.abs(status.getVx());
				double currentVY = Math.abs(status.getVy());

				return (currentVX + currentVY) <= 0.1;
			}
		};
	}

	@Override
	public void doIt() {
		getController().turnOnXY(breakTiltX, breakTiltY);
	}

	@Override
	public String toString() {
		return "Setting tiltX " + breakTiltX + " tiltY " + breakTiltY;
	}
}