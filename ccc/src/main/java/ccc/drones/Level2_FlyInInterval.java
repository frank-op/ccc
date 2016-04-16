package ccc.drones;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import ccc.drones.drone.Drone;
import ccc.drones.level.BaseLevel;
import ccc.drones.level.DroneFactory;

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

	public void testDrone() {

		List<Drone> drones = DroneFactory.gimmeDrones(numberOfDrones);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Level2_FlyInInterval level = new Level2_FlyInInterval();
		level.setUp();
		level.testDrone();
	}
}