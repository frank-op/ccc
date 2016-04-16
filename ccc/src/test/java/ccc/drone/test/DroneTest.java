package ccc.drone.test;

import org.testng.annotations.Test;

import ccc.drone.drone.Drone;

public class DroneTest {

	@Test
	public void basicDroneTest() {
		Drone drone = new Drone(0);
		System.out.println(drone.getThrustForThrottle(0.569052216728));
		System.out.println(drone.getThrottleToOvercomeGravity());
	}
}