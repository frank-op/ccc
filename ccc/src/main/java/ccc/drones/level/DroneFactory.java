package ccc.drones.level;

import java.util.ArrayList;
import java.util.List;

import ccc.drones.drone.Drone;

public class DroneFactory {

	public static List<Drone> gimmeDrones(int count) {
		List<Drone> drones = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			drones.add(new Drone(i));
		}
		return drones;
	}
}