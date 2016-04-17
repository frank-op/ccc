package ccc.drones;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import ccc.drones.drone.Drone;
import ccc.drones.drone.DroneController;
import ccc.drones.level.BaseLevel;
import ccc.drones.level.DroneFactory;
import ccc.drones.sim.Scenario;

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
		List<DroneController> controllers = new ArrayList<>();

		for (Drone drone : drones) {
			DroneController droneController = new DroneController(drone);

			Scenario scenario = Scenario.build(droneController) //
					.sendDroneToMinZ(Double.valueOf(minZ)) //
					.hoverForSeconds(10.0) //
					.landThatDamnThing() //
					.scenario();

			droneController.setScenario(scenario);
			controllers.add(droneController);
		}

		for (DroneController droneController : controllers) {
			droneController.startScenario();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Level2_FlyInInterval level = new Level2_FlyInInterval();
		level.setUp();
		level.testDrone();
	}
}