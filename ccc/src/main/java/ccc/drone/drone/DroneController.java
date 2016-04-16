package ccc.drone.drone;

import static ccc.drone.drone.SimulatorCommunicator.communication;

public class DroneController {

	private static final Double SMALL_TICK_TIME = 0.01;
	private static final Double FULL_THROTTLE = 1.0;
	private static final Double MAX_SPEED = 13.5;

	public void sendDroneToMinZ(Drone drone, Double z) {

		accelerateOnZToMetersPerSecond(drone, 13.5);
		tickAndWeightForZToBeMet(drone, z);
	}

	private void accelerateOnZToMetersPerSecond(Drone drone, Double d) {
		changeThrottleForDrone(drone, FULL_THROTTLE);
		while (getCurrentVZForDrone(drone) < MAX_SPEED) {
			tick(SMALL_TICK_TIME);
		}
		changeThrottleForDrone(drone, drone.getThrottleToOvercomeGravity());
	}

	private void changeThrottleForDrone(Drone drone, Double throttle) {
		Integer droneId = drone.getDroneId();
		System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneId);
		communication().sendToSimulator("THROTTLE " + droneId + " " + throttle);
		String response = communication().getNextStringFromSimulator();
		System.out.println("ThrottleOK: " + response + "\n");
	}

	private void tickAndWeightForZToBeMet(Drone drone, Double z) {
		while (getCurrentZForDrone(drone) < z) {
			tick(SMALL_TICK_TIME);
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

	public double getCurrentXForDrone(Drone drone) {
		return getStatusForDrone(drone).getX();
	}

	public double getCurrentYForDrone(Drone drone) {
		return getStatusForDrone(drone).getY();
	}

	public double getCurrentZForDrone(Drone drone) {
		double z = getStatusForDrone(drone).getZ();
		System.out.println("CURRENT Z: " + z + " for Drone: " + drone.getDroneId() + " \n");
		return z;
	}

	public double getCurrentVZForDrone(Drone drone) {
		double vz = getStatusForDrone(drone).getVz();
		System.out.println("CURRENT VZ: " + vz + " for Drone: " + drone.getDroneId() + " \n");
		return vz;
	}

	private Status getStatusForDrone(Drone drone) {

		communication().sendToSimulator("STATUS " + drone.getDroneId());
		String response = communication().getNextStringFromSimulator();
		return new Status(response);
	}
}