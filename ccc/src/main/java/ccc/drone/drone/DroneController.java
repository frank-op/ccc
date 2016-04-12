package ccc.drone.drone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DroneController implements AutoCloseable {

	public final static String HOST = "127.0.0.1";
	public final static int PORT = 4444;

	private OutputStream outputStream;
	private PrintStream printStream;
	private InputStream inputStream;
	private Scanner scanner;
	private Socket clientSocket;

	public DroneController() {

		try {
			initComunicationWithSimulator();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void initComunicationWithSimulator() throws IOException, UnknownHostException {
		clientSocket = new Socket(HOST, PORT);
		outputStream = clientSocket.getOutputStream();
		printStream = new PrintStream(outputStream);
		inputStream = clientSocket.getInputStream();
		scanner = new Scanner(inputStream);
	}

	public void sendDroneToMinZ(int droneID, double z) {

		changeThrottleForDrone(droneID, 0.7);
		while (true) {
			tick(0.05);
			if (getCurrentZForDrone(droneID) > z) {
				break;
			}
		}
		changeThrottleForDrone(droneID, Drone.HOVERING_THROTTLE);
	}

	private void changeThrottleForDrone(int droneID, double throttle) {
		System.out.println("SET THROTTLE: " + throttle + " for drone: " + droneID);
		printStream.println("THROTTLE " + droneID + " " + throttle);
		String response = scanner.nextLine();
		System.out.println("ThrottleOK: " + response);
		System.out.println();
	}

	private void tick(Double tick) {
		System.out.println("ADD TICK " + tick);
		printStream.println("TICK " + tick);
		String response = scanner.nextLine();
		System.out.println("TickSuccess " + response);
	}

	public double getCurrentXForDrone(int droneID) {
		return getStatusForDrone(droneID).getX();
	}

	public double getCurrentYForDrone(int droneID) {
		return getStatusForDrone(droneID).getY();
	}

	public double getCurrentZForDrone(int droneID) {
		return getStatusForDrone(droneID).getZ();
	}

	private Status getStatusForDrone(int droneID) {

		printStream.println("STATUS " + droneID);
		String response = scanner.nextLine();
		System.out.println("Status " + droneID + " :" + response);
		if (response.equals("SUCCESS")) {
			return Status.success();
		}
		return new Status(response);
	}

	@Override
	public void close() throws Exception {
		clientSocket.close();
		scanner.close();
	}
}