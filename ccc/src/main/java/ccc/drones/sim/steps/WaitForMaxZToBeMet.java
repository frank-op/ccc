package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.Status;

public class WaitForMaxZToBeMet extends Step {

	private Double z;

	public WaitForMaxZToBeMet(DroneController controller, Double z) {
		super(controller);
		this.z = z;
	}

	@Override
	public Check getCheck() {

		return new Check(getController()) {

			@Override
			public boolean check(Status status) {
				System.out.println("Drone " + getDroneController().getDrone().getDroneId() + " Z: " + status.getZ());
				return z > status.getZ();
			}
		};
	}

	@Override
	public void doIt() {
		// nothing to do
	}

	@Override
	public String toString() {
		return "Waiting for Drone to be under Z: " + z + ". Drone: " + getController().getDrone();
	}
}