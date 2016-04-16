package ccc.drones;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import ccc.drones.drone.Drone;
import ccc.drones.drone.Target;
import ccc.drones.level.BaseLevel;
import ccc.drones.level.LevelDoneException;

public class Level3_FlyToTarget extends BaseLevel {

	private String constrainedArea;
	private Integer numberOfDrones;
	private Map<Drone, Target> drones = new LinkedHashMap<>();

	@Override
	public void doSetUp() {
		constrainedArea = communication().getNextStringFromSimulator();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = Integer.parseInt(communication().getNextStringFromSimulator());
		System.out.println("Number of Drones: " + numberOfDrones);

		for (int i = 0; i < numberOfDrones; i++) {

			String targetAsString = communication().getNextStringFromSimulator();
			String[] split = targetAsString.split(" ");
			drones.put(new Drone(i),
					new Target(Double.valueOf(split[0]), Double.valueOf(split[1]), Double.valueOf(split[2])));
		}
	}

	@Test()
	public void testDrone() {

		System.out.println(drones);
	}
}