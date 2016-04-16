package ccc.drones.drone;

import static ccc.drones.sim.SimulatorCommunicator.communication;

import ccc.drones.level.LevelDoneException;
import ccc.drones.sim.Status;

public class DroneController {

	private static final Double SMALL_TICK_TIME = 0.01;
	private static final Double FULL_THROTTLE = 1.0;
	private static final Double DROP_THROTTLE = 0.0;
	private static final Double MAX_SPEED_UP = 12.0;
	private static final Double MAX_SPEED_DOWN = -5.0;

	public void sendDroneToMinZ(Drone drone, Double z) {
		changeSpeedOnZToMetersPerSecond(drone, MAX_SPEED_UP);
		tickAndWeightForMinZToBeMet(drone, z);
		hoverOnZ(drone);
	}

	public void sendDroneToMaxZ(Drone drone, Double z) {
		changeSpeedOnZToMetersPerSecond(drone, MAX_SPEED_DOWN);
		tickAndWeightForMaxZToBeMet(drone, z + 3);
		hoverOnZ(drone);
		changeSpeedOnZToMetersPerSecond(drone, -0.45);
		tickAndWeightForMaxZToBeMet(drone, z);
		hoverOnZ(drone);
	}

	public void land(Drone drone) {
		Integer droneId = drone.getDroneId();
		System.out.println("LAND drone: " + droneId);
		changeThrottleForDrone(drone, 0.0);
		communication().sendToSimulator("LAND " + droneId);
		String response = communication().getNextStringFromSimulator();
		tick(1.0);
		System.out.println("LANDOk: " + response + "\n");
	}

	public void hoverOnZ(Drone drone) {
		changeSpeedOnZToMetersPerSecond(drone, 0.0);
	}

	public void hoverOnZ(Drone drone, Double seconds) {
		hoverOnZ(drone);
		tick(seconds);
	}

	private void changeSpeedOnZToMetersPerSecond(Drone drone, Double nextVZ) {

		Double currentVZForDrone = getCurrentVZForDrone(drone);
		if (nextVZ < currentVZForDrone) {
			brakeDroneToVZ(drone, nextVZ);
		} else {
			accelerateToVZ(drone, nextVZ);
		}
	}

	private void brakeDroneToVZ(Drone drone, Double vz) {
		changeThrottleForDrone(drone, DROP_THROTTLE);
		while (vz < getCurrentVZForDrone(drone)) {
			tick(SMALL_TICK_TIME);
		}
		changeThrottleForDrone(drone, drone.getThrottleToOvercomeGravity());
		tick(SMALL_TICK_TIME);
	}

	private void accelerateToVZ(Drone drone, Double vz) {
		changeThrottleForDrone(drone, FULL_THROTTLE);
		while (vz > getCurrentVZForDrone(drone)) {
			tick(SMALL_TICK_TIME);
		}
		changeThrottleForDrone(drone, drone.getThrottleToOvercomeGravity());
		tick(SMALL_TICK_TIME);
	}

	private void changeThrottleForDrone(Drone drone, Double throttle) {
		Integer droneId = drone.getDroneId();
		System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneId);
		communication().sendToSimulator("THROTTLE " + droneId + " " + throttle);
		String response = communication().getNextStringFromSimulator();
		System.out.println("ThrottleOK: " + response + "\n");
	}

	private void tickAndWeightForMinZToBeMet(Drone drone, Double z) {
		Double currentZForDrone = getCurrentZForDrone(drone);

		while (currentZForDrone < z) {
			tick(SMALL_TICK_TIME);
			currentZForDrone = getCurrentZForDrone(drone);
		}
	}

	private void tickAndWeightForMaxZToBeMet(Drone drone, Double z) {
		Double currentZForDrone = getCurrentZForDrone(drone);

		while (currentZForDrone > z) {
			tick(SMALL_TICK_TIME);
			currentZForDrone = getCurrentZForDrone(drone);
		}
	}

	private void tick(Double tick) {
		System.out.println("ADD TICK " + tick);
		communication().sendToSimulator("TICK " + tick);
		String response = communication().getNextStringFromSimulator();
		System.out.println("TickSuccess " + response + "\n");
		if ("SUCCESS".equals(response)) {
			throw new LevelDoneException();
		}
	}

	private Double getCurrentXForDrone(Drone drone) {
		return getStatusForDrone(drone).getX();
	}

	private Double getCurrentYForDrone(Drone drone) {
		return getStatusForDrone(drone).getY();
	}

	private Double getCurrentZForDrone(Drone drone) {
		double z = getStatusForDrone(drone).getZ();
		System.out.println("CURRENT Z: " + z + " for Drone: " + drone.getDroneId() + " \n");
		return z;
	}

	private Double getCurrentVZForDrone(Drone drone) {
		Double vz = getStatusForDrone(drone).getVz();
		System.out.println("CURRENT VZ: " + vz + " for Drone: " + drone.getDroneId() + " \n");
		return vz;
	}

	private Status getStatusForDrone(Drone drone) {

		communication().sendToSimulator("STATUS " + drone.getDroneId());
		String response = communication().getNextStringFromSimulator();
		return new Status(response);
	}
}