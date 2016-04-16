package ccc.drones;

import org.testng.annotations.Test;

import ccc.drones.drone.Drone;
import ccc.drones.level.BaseLevel;
import ccc.drones.level.LevelDoneException;

public class Level1_FlyAboveZ extends BaseLevel {

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