package ccc.drones_training.level;

import java.io.IOException;
import java.net.UnknownHostException;

import ccc.drones_training.sim.SimulatorCommunicator;

public abstract class BaseLevel {

	public void setUp() throws UnknownHostException, IOException {
		SimulatorCommunicator.communication().open();
		doSetUp();
	}

	public void tearDown() throws IOException {
		SimulatorCommunicator.communication().close();
	}

	protected abstract void doSetUp();

	public SimulatorCommunicator communication() {
		return SimulatorCommunicator.communication();
	}
}