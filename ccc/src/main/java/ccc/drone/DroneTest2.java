package ccc.drone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.testng.annotations.Test;

public class DroneTest2 {

	private final static String HOST = "127.0.0.1";
	private final static int PORT = 4444;
	private int minHeight = 20;
	private int maxHeight = 40;
	private PrintStream printStream;
	private InputStream inputStream;
	private Scanner scanner;

	@Test
	public void testDrone() throws UnknownHostException, IOException {

		try (Socket clientSocket = new Socket(HOST, PORT)) {

			OutputStream outputStream = clientSocket.getOutputStream();
			printStream = new PrintStream(outputStream);
			inputStream = clientSocket.getInputStream();
			scanner = new Scanner(inputStream);

			String constrainedArea = scanner.nextLine();
			System.out.println("Constrained Area: " + constrainedArea);
			String numberOfDrones = scanner.nextLine();
			System.out.println("Number of Drones: " + numberOfDrones);

			Integer drones = Integer.parseInt(numberOfDrones);

			startAndHover(drones);
			prepareLanding(drones);
			landTheDamnThing(drones);

			scanner.close();
		}
	}

	private Double startAndHover(Integer drones) {
		double height = 0;
		double tickInHeight = 0;
		double tick = 0.5;

		while (true) {

			if (height <= minHeight) {
				changeThrottle("0.57", drones);
				tick(tick);
				height = status(drones);
			} else if (height > minHeight) {
				changeThrottle("0.565", drones);
				tick(tick);
				height = status(drones);
				tickInHeight = tickInHeight + tick;
			} else if (height > maxHeight - 5) {
				changeThrottle("0.2", drones);
				tick(tick);
				height = status(drones);
				tickInHeight = tickInHeight + tick;
			}

			System.out.println("tickInHeight: " + tickInHeight);
			if (tickInHeight >= 10) {
				break;
			}
		}
		return height;
	}

	private void prepareLanding(Integer drones) {

		double height;
		int iteration = 0;

		while (true) {
			iteration++;

			changeThrottle("0.565", drones);
			tick(0.1);
			height = status(drones);

			if (height < 15) {
				break;
			}
		}

		while (true) {
			iteration++;
			if (iteration % 3 == 0) {
				changeThrottle("0.595", drones);
				tick(0.1);
			} else {
				changeThrottle("0.56", drones);
				tick(0.1);
			}
			height = status(drones);
			if (height < 0.3) {
				break;
			}
		}
	}

	private void landTheDamnThing(Integer drones) {

		for (int i = 0; i < drones; i++) {
			printStream.println("THROTTLE " + i + " 0.0");
			String response = scanner.nextLine();
			System.out.println("ThrottleOK: " + response);

			printStream.println("LAND " + i);
			response = scanner.nextLine();
			System.out.println("LAND: " + response);
		}

		printStream.println("TICK 0.1");
		String response = scanner.nextLine();
		System.out.println("TickSuccess " + response);
	}

	private void changeThrottle(String throttle, Integer drones) {

		for (int i = 0; i < drones; i++) {
			printStream.println("THROTTLE " + i + " " + throttle);
			String response = scanner.nextLine();
			System.out.println("ThrottleOK: " + response);
		}
	}

	private Double status(Integer drones) {
		String response = "";
		for (int i = 0; i < drones; i++) {
			printStream.println("STATUS " + i);
			response = scanner.nextLine();
			System.out.println("Status " + i + " :" + response);
		}
		String heightAsString = response.split(" ")[2];
		return Double.parseDouble(heightAsString);
	}

	private void tick(Double tick) {
		printStream.println("TICK " + tick);
		String response = scanner.nextLine();
		System.out.println("TickSuccess " + response);
	}
}