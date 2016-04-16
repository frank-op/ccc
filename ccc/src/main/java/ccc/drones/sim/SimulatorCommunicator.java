package ccc.drones.sim;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public final class SimulatorCommunicator {

	public final static String HOST = "127.0.0.1";
	public final static int PORT = 4444;

	private OutputStream outputStream;
	private PrintStream printStream;
	private InputStream inputStream;
	private Scanner scanner;
	private Socket clientSocket;

	private static SimulatorCommunicator instance;

	public static SimulatorCommunicator communication() {
		if (instance == null) {
			synchronized (SimulatorCommunicator.class) {
				if (instance == null) {
					instance = new SimulatorCommunicator();
				}
			}
		}
		return instance;
	}

	private SimulatorCommunicator() {
	}

	public void open() throws IOException, UnknownHostException {
		clientSocket = new Socket(HOST, PORT);
		outputStream = clientSocket.getOutputStream();
		printStream = new PrintStream(outputStream);
		inputStream = clientSocket.getInputStream();
		scanner = new Scanner(inputStream);
	}

	public void sendToSimulator(String goesToSimulator) {
		printStream.println(goesToSimulator);
		printStream.flush();
	}

	public String getNextStringFromSimulator() {
		return scanner.nextLine();
	}

	public void close() throws IOException {
		clientSocket.close();
		scanner.close();
	}
}