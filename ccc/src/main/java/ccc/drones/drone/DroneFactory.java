package ccc.drones.drone;

import java.util.ArrayList;
import java.util.List;

public class DroneFactory {

	public static List<Drone> gimmeDrones(int count) {
		List<Drone> drones = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			drones.add(new Drone(i));
		}
		return drones;
	}
}