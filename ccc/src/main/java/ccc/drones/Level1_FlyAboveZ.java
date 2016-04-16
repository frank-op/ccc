package ccc.drones;

import java.io.IOException;
import java.net.UnknownHostException;

import ccc.drones.drone.Drone;
import ccc.drones.drone.DroneController;
import ccc.drones.level.BaseLevel;
import ccc.drones.sim.Scenario;

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

	public void testDrone() {
		Drone drone = new Drone(0);
		DroneController droneController = drone.getDroneController();
		Scenario scenario = Scenario.build(droneController).sendDroneToMinZ(Double.valueOf(height)).scenario();
		droneController.setScenario(scenario);
		droneController.startScenario();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		Level1_FlyAboveZ level = new Level1_FlyAboveZ();
		level.setUp();
		level.testDrone();
	}
}