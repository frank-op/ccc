package ccc.drones.level;

import ccc.drones.sim.SimulatorCommunicator;

import java.io.IOException;
import java.net.UnknownHostException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseLevel {

	@BeforeClass
	public void setUp() throws UnknownHostException, IOException {
		SimulatorCommunicator.communication().open();
		doSetUp();
	}

	@AfterClass
	public void tearDown() throws IOException {
		SimulatorCommunicator.communication().close();
	}

	protected abstract void doSetUp();

	public SimulatorCommunicator communication() {
		return SimulatorCommunicator.communication();
	}
}