package ccc.drone.drone;

import static ccc.drone.drone.SimulatorCommunicator.communication;

import java.io.IOException;
import java.net.UnknownHostException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseDroneTest {

	@BeforeClass
	public void setUp() throws UnknownHostException, IOException {
		communication().open();
		doSetUp();
	}

	@AfterClass
	public void tearDown() throws IOException {
		communication().close();
	}

	protected abstract void doSetUp();
}