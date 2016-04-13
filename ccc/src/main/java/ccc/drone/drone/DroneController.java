package ccc.drone.drone;

import static ccc.drone.drone.SimulatorCommunicator.communication;

public class DroneController {

	public void sendDroneToMinZ(Drone drone, double z) {

		Integer droneId = drone.getDroneId();

		changeThrottleForDrone(droneId, 0.7);
		while (true) {
			tick(0.05);
			if (getCurrentVZForDrone(droneId) > 13) {
				changeThrottleForDrone(droneId, drone.getHoveringThrottle());
			}
			if (getCurrentZForDrone(droneId) > z) {
				break;
			}
		}
		changeThrottleForDrone(droneId, drone.getHoveringThrottle());
	}

	private void changeThrottleForDrone(int droneID, double throttle) {
		System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneID);
		communication().sendToSimulator("THROTTLE " + droneID + " " + throttle);
		String response = communication().getNextStringFromSimulator();
		System.out.println("ThrottleOK: " + response + "\n");
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

	public double getCurrentXForDrone(int droneID) {
		return getStatusForDrone(droneID).getX();
	}

	public double getCurrentYForDrone(int droneID) {
		return getStatusForDrone(droneID).getY();
	}

	public double getCurrentZForDrone(int droneID) {
		double z = getStatusForDrone(droneID).getZ();
		System.out.println("CURRENT Z: " + z + " for Drone: " + droneID + " \n");
		return z;
	}

	public double getCurrentVZForDrone(int droneID) {
		double vz = getStatusForDrone(droneID).getVz();
		System.out.println("CURRENT VZ: " + vz + " for Drone: " + droneID + " \n");
		return vz;
	}

	private Status getStatusForDrone(int droneID) {

		communication().sendToSimulator("STATUS " + droneID);
		String response = communication().getNextStringFromSimulator();
		return new Status(response);
	}
}