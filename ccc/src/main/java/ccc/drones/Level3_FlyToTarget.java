package ccc.drones;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ccc.drones.drone.Drone;
import ccc.drones.drone.DroneController;
import ccc.drones.drone.Target;
import ccc.drones.level.BaseLevel;
import ccc.drones.sim.Scenario;

public class Level3_FlyToTarget extends BaseLevel {

	private String constrainedArea;
	private Integer numberOfDrones;
	private Map<Drone, Target> dronesAndTheirTargets = new LinkedHashMap<>();
	private Double minZ = 20.0;

	@Override
	public void doSetUp() {
		constrainedArea = communication().getNextStringFromSimulator();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = Integer.parseInt(communication().getNextStringFromSimulator());
		System.out.println("Number of Drones: " + numberOfDrones);

		for (int i = 0; i < numberOfDrones; i++) {

			String targetAsString = communication().getNextStringFromSimulator();
			String[] split = targetAsString.split(" ");
			dronesAndTheirTargets.put(new Drone(i),
					new Target(Double.valueOf(split[0]), Double.valueOf(split[1]), Double.valueOf(split[2])));
		}

		System.out.println(dronesAndTheirTargets);
	}

	public void start() {

		List<DroneController> controllers = new ArrayList<>();

		for (Entry<Drone, Target> entry : dronesAndTheirTargets.entrySet()) {

			Drone drone = entry.getKey();
			Target target = entry.getValue();

			DroneController droneController = new DroneController(drone);

			Scenario scenario = Scenario.build(droneController) //
					.sendDroneToMinZ(Double.valueOf(minZ + (drone.getDroneId() * 2))) //
					.flyToTarget(target) //
					.landThatDamnThing() //
					.scenario();

			droneController.setScenario(scenario);
			controllers.add(droneController);
		}

		// for (DroneController droneController : controllers) {
		// droneController.startScenario();
		// }
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Level3_FlyToTarget level = new Level3_FlyToTarget();
		level.setUp();
		level.start();
	}
}