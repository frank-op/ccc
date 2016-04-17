package ccc.drones.sim.steps;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.Check;
import ccc.drones.sim.MissionControl;
import ccc.drones.sim.Status;

public class HoverOnZForSeconds extends Step {

	private Double seconds;
	private Double startTimeStamp;

	public HoverOnZForSeconds(DroneController controller, Double seconds) {
		super(controller);
		this.seconds = seconds;
	}

	@Override
	public Check getCheck() {

		return new Check(getController()) {

			@Override
			public boolean check(Status status) {

				Double currentTime = status.getCurrentTime();
				Double timeDone = currentTime - startTimeStamp;

				System.out.println(getDroneController().getDrone() + ": " + timeDone + " / " + seconds);

				return timeDone > seconds;
			}
		};
	}

	@Override
	public void doIt() {
		startTimeStamp = MissionControl.instance().getCurrentTime();
	}

	@Override
	public String toString() {
		return "Wait for Time to go by, seconds: " + seconds + ". Drone: " + getController().getDrone();
	}
}