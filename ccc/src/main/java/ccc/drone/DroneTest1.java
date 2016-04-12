package ccc.drone;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ccc.drone.drone.Drone;
import ccc.drone.drone.DroneController;

public class DroneTest1 {

	private String constrainedArea;
	private String numberOfDrones;
	private String height;

	@BeforeClass
	public void setUp() throws UnknownHostException, IOException {

		Socket clientSocket = new Socket(DroneController.HOST, DroneController.PORT);

		InputStream inputStream = clientSocket.getInputStream();
		Scanner scanner = new Scanner(inputStream);

		constrainedArea = scanner.nextLine();
		System.out.println("Constrained Area: " + constrainedArea);
		numberOfDrones = scanner.nextLine();
		System.out.println("Number of Drones: " + numberOfDrones);
		height = scanner.nextLine();
		System.out.println("Height " + height + "\n");

		scanner.close();
		clientSocket.close();
	}

	@Test
	public void testDrone() {

		Drone drone = new Drone(0);
		drone.flyToZCoordinate(Double.valueOf(height));
	}
}