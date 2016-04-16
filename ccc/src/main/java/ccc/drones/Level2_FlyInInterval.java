package ccc.drones;

import java.util.List;

import org.testng.annotations.Test;

import ccc.drones.drone.Drone;
import ccc.drones.drone.DroneFactory;
import ccc.drones.level.BaseLevel;
import ccc.drones.level.LevelDoneException;

public class Level2_FlyInInterval extends BaseLevel {

	private String constrainedArea;
	private Integer numberOfDrones;
	private Double minZ = 20.0;

	@Override
	public void doSetUp() {
		constrainedArea = communication().getNextStringFromSimulator();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = Integer.parseInt(communication().getNextStringFromSimulator());
		System.out.println("Number of Drones: " + numberOfDrones);
	}

	@Test(expectedExceptions = LevelDoneException.class)
	public void testDrone() {

		List<Drone> drones = DroneFactory.gimmeDrones(numberOfDrones);
		for (Drone drone : drones) {
			drone.flyToZCoordinate(minZ);
		}
		for (Drone drone : drones) {
			drone.hoverSeconds(10.0);
		}
		for (Drone drone : drones) {
			drone.landSafely();
		}
	}
}