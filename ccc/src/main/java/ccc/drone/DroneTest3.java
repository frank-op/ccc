package ccc.drone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.Test;

public class DroneTest3 {

	private final static String HOST = "127.0.0.1";
	private final static int PORT = 4444;
	private int minHeight = 10;
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
			Integer drones = Integer.parseInt(numberOfDrones);
			System.out.println("Number of Drones: " + numberOfDrones);

			List<String> dronePositions = new ArrayList<>();
			for (int i = 0; i < drones; i++) {
				String targetPosition = scanner.nextLine();
				dronePositions.add(targetPosition);
			}
			System.out.println("targetPositions: " + dronePositions);

			int i = 0;
			for (String droneTarget : dronePositions) {
				startAndHover(i);
				flyToTarget(droneTarget, i);
				prepareLanding(i);
				landTheDamnThing(i);
				i++;
			}

			scanner.close();
		}
	}

	private void flyToTarget(String droneTarget, Integer droneId) {

		turn(droneId, 10, 6, 4);
		while (true) {
			tick(0.2);
			Status status = status(droneId);
			if (status.getX() > 5.1 && status.getY() > 12.1) {
				break;
			}
		}
		turn(droneId, -10, -6, -4);
	}

	private void turn(int droneId, int x, int y, int z) {

		printStream.println("TURN " + droneId + " " + x + " " + y + " " + z);
		String response = scanner.nextLine();
		System.out.println("TurnOK: " + response);
	}

	private Double startAndHover(Integer drones) {
		double height = 0;
		double tickInHeight = 0;
		double tick = 0.5;

		while (true) {

			if (height <= minHeight) {
				throttle("0.57", drones);
				tick(tick);
				height = status(drones).getZ();
			} else if (height > minHeight) {
				throttle("0.565", drones);
				tick(tick);
				height = status(drones).getZ();
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

			multiThrottle("0.565", drones);
			tick(0.1);
			height = status(drones).getZ();

			if (height < 15) {
				break;
			}
		}

		while (true) {
			iteration++;
			if (iteration % 3 == 0) {
				multiThrottle("0.595", drones);
				tick(0.1);
			} else {
				multiThrottle("0.56", drones);
				tick(0.1);
			}
			height = status(drones).getZ();
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

	private void multiThrottle(String throttle, Integer numberOfDrones) {

		for (int i = 0; i < numberOfDrones; i++) {
			throttle(throttle, i);
		}
	}

	private void throttle(String throttle, int i) {
		printStream.println("THROTTLE " + i + " " + throttle);
		String response = scanner.nextLine();
		System.out.println("ThrottleOK: " + response);
	}

	private Status status(int i) {
		printStream.println("STATUS " + i);
		String response = scanner.nextLine();
		System.out.println("Status " + i + " :" + response);
		return Status.getStatus(response);
	}

	private void tick(Double tick) {
		printStream.println("TICK " + tick);
		String response = scanner.nextLine();
		System.out.println("TickSuccess " + response);
	}

	static class Status {
		private double x, y, z, vx, vy, vz, rx, ry, rz;

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getZ() {
			return z;
		}

		public double getVx() {
			return vx;
		}

		public double getVy() {
			return vy;
		}

		public double getVz() {
			return vz;
		}

		public double getRx() {
			return rx;
		}

		public double getRy() {
			return ry;
		}

		public double getRz() {
			return rz;
		}

		private static Status getStatus(String statusAsString) {

			String[] split = statusAsString.split(" ");
			Status status = new Status();
			status.x = Double.parseDouble(split[0]);
			status.y = Double.parseDouble(split[1]);
			status.z = Double.parseDouble(split[2]);
			status.vx = Double.parseDouble(split[3]);
			status.vy = Double.parseDouble(split[4]);
			status.vz = Double.parseDouble(split[5]);
			status.rx = Double.parseDouble(split[6]);
			status.ry = Double.parseDouble(split[7]);
			status.rz = Double.parseDouble(split[8]);
			return status;
		}

		@Override
		public String toString() {
			return "Status [x=" + x + ", y=" + y + ", z=" + z + ", vx=" + vx + ", vy=" + vy + ", vz=" + vz + ", rx="
					+ rx + ", ry=" + ry + ", rz=" + rz + "]";
		}
	}
}