package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class AccelerateOnXYToMetersPerSecond extends Step {

	private Double tiltX;
	private Double tiltY;
	private Double velocity;

	public AccelerateOnXYToMetersPerSecond(DroneController controller, Double tiltX, Double tiltY, Double velocity) {
		super(controller);
		this.tiltX = tiltX;
		this.tiltY = tiltY;
		this.velocity = velocity;
	}

	@Override
	public Check getCheck() {
		return new Check(getController()) {

			@Override
			public boolean check(Status status) {

				double currentVX = Math.abs(status.getVx());
				double currentVY = Math.abs(status.getVy());

				return (currentVX + currentVY) > velocity;
			}
		};
	}

	@Override
	public void doIt() {
		getController().turnOnXY(tiltX, tiltY);
		getController().changeThrottleForDrone(0.6);
	}

	@Override
	public String toString() {
		return "Setting tiltX " + tiltX + " tiltY " + tiltY;
	}
}