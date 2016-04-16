package ccc.drones;

import org.testng.annotations.Test;

import ccc.drones.level.BaseLevel;
import ccc.drones.level.LevelDoneException;

public class Level2_FlyInInterval extends BaseLevel {

	private String constrainedArea;
	private String numberOfDrones;

	@Override
	public void doSetUp() {
		constrainedArea = communication().getNextStringFromSimulator();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = communication().getNextStringFromSimulator();
		System.out.println("Number of Drones: " + numberOfDrones);
	}

	@Test(expectedExceptions = LevelDoneException.class)
	public void testDrone() {

	}
}