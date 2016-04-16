package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class WaitForMinZToBeMet extends Step {

	private Double z;

	public WaitForMinZToBeMet(DroneController controller, Double z) {
		super(controller);
		this.z = z;
	}

	@Override
	public Check getCheck() {

		return new Check(getController()) {

			@Override
			public boolean check(Status status) {
				System.out.println("Drone " + getDroneController().getDrone().getDroneId() + " Z: " + status.getZ());
				return z < status.getZ();
			}
		};
	}

	@Override
	public void doIt() {
		// nothing to do
	}
}