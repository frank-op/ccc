package ccc.drones;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

import ccc.drones.drone.Drone;
import ccc.drones.drone.Target;
import ccc.drones.level.BaseLevel;

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

	public static void main(String[] args) throws UnknownHostException, IOException {
		Level3_FlyToTarget level = new Level3_FlyToTarget();
		level.setUp();
	}
}