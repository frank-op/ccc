package ccc.drone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.testng.annotations.Test;

public class DroneTest1 {

	private final static String HOST = "127.0.0.1";
	private final static int PORT = 4444;

	@Test
	public void testDrone() throws UnknownHostException, IOException {

		try (Socket clientSocket = new Socket(HOST, PORT)) {

			OutputStream outputStream = clientSocket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			InputStream inputStream = clientSocket.getInputStream();
			Scanner scanner = new Scanner(inputStream);

			String constrainedArea = scanner.nextLine();
			System.out.println("Constrained Area: " + constrainedArea);
			String numberOfDrones = scanner.nextLine();
			System.out.println("Number of Drones: " + numberOfDrones);
			String height = scanner.nextLine();
			System.out.println("Height " + height);

			printStream.println("THROTTLE 0 0.6");

			String response = scanner.nextLine();
			System.out.println("ThrottleOK: " + response);

			printStream.println("TICK 10");

			response = scanner.nextLine();
			System.out.println("TickSuccess " + response);

			printStream.println("STATUS 0");
			response = scanner.nextLine();
			System.out.println("Status: " + response);

			scanner.close();
		}
	}
}