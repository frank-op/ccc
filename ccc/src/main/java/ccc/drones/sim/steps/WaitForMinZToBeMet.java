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
				Double currentZ = status.getZ();
				System.out.println(status);
				return z < currentZ;
			}
		};
	}

	@Override
	public void doIt() {
		// nothing to do
	}

	@Override
	public String toString() {
		return "Waiting for Drone to be over Z: " + z + ". Drone: " + getController().getDrone();
	}
}