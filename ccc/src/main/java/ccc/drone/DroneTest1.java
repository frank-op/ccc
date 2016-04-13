package ccc.drone;

import static ccc.drone.drone.SimulatorCommunicator.communication;

import org.testng.annotations.Test;

import ccc.drone.drone.BaseDroneTest;
import ccc.drone.drone.Drone;
import ccc.drone.drone.LevelDoneException;

public class DroneTest1 extends BaseDroneTest {

	private String constrainedArea;
	private String numberOfDrones;
	private String height;

	@Override
	public void doSetUp() {
		constrainedArea = communication().getNextStringFromSimulator();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = communication().getNextStringFromSimulator();
		System.out.println("Number of Drones: " + numberOfDrones);
		height = communication().getNextStringFromSimulator();
		System.out.println("Height " + height + "\n");
	}

	@Test(expectedExceptions = LevelDoneException.class)
	public void testDrone() {
		Drone drone = new Drone(0);
		drone.flyToZCoordinate(Double.valueOf(height));
	}
}