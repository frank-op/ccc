package ccc.drones.sim;

import java.util.ArrayList;
import java.util.List;

import ccc.drones.drone.DroneController;
import ccc.drones.drone.Target;
import ccc.drones.sim.steps.AccelerateOnXYToMetersPerSecond;
import ccc.drones.sim.steps.AccelerateOnZToMetersPerSecond;
import ccc.drones.sim.steps.BrakeOnXY;
import ccc.drones.sim.steps.BreakOnZToMetersPerSecond;
import ccc.drones.sim.steps.BreakToZeroMetersPerSecondOnZ;
import ccc.drones.sim.steps.HoverOnZForSeconds;
import ccc.drones.sim.steps.LandThatDrone;
import ccc.drones.sim.steps.ReachTarget;
import ccc.drones.sim.steps.SetBackTilt;
import ccc.drones.sim.steps.SetThrottleToOvercomeGravity;
import ccc.drones.sim.steps.Step;
import ccc.drones.sim.steps.WaitForMaxZToBeMet;
import ccc.drones.sim.steps.WaitForMinZToBeMet;

public class Scenario {

	private final List<Step> steps = new ArrayList<>();

	private Scenario() {

	}

	public static ScenarioBuilder build(DroneController controller) {
		return new ScenarioBuilder(controller);
	}

	public List<Step> getSteps() {
		return steps;
	}

	public static class ScenarioBuilder {

		private Scenario scenario;
		private DroneController controller;

		public ScenarioBuilder(DroneController controller) {
			this.controller = controller;
			this.scenario = new Scenario();
		}

		public ScenarioBuilder sendDroneToMinZ(Double z) {

			scenario.getSteps().add(new AccelerateOnZToMetersPerSecond(controller, DroneController.MAX_SPEED_UP));
			scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			scenario.getSteps().add(new WaitForMinZToBeMet(controller, z));
			scenario.getSteps().add(new BreakToZeroMetersPerSecondOnZ(controller));
			scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			return this;
		}

		public ScenarioBuilder hoverForSeconds(Double seconds) {
			scenario.getSteps().add(new HoverOnZForSeconds(controller, seconds));
			return this;
		}

		public ScenarioBuilder flyToTarget(Target target) {

			Status status = MissionControl.instance().getStatusForDrone(controller.getDrone());

			System.out.println(status);
			System.out.println(target);

			Double currentX = status.getX();
			Double targetX = target.getX();
			Double wayToGoX = targetX - currentX;

			Double currentY = status.getY();
			Double targetY = target.getY();
			Double wayToGoY = targetY - currentY;

			Double tiltX;
			Double tiltY;

			if (!(wayToGoX == 0 && wayToGoY == 0)) {

				if (wayToGoX == 0) {
					tiltX = 0.0;
					tiltY = 0.5;
				} else if (wayToGoY == 0) {
					tiltX = 0.5;
					tiltY = 0.0;
				} else {
					if (wayToGoX >= wayToGoY) {
						Double ratioXY = Math.abs(wayToGoX / wayToGoY);
						Double parts = ratioXY + 1;
						Double onePart = 0.5 / parts;
						tiltX = ratioXY * onePart;
						tiltY = 0.5 - tiltX;
					} else {
						Double ratioYX = Math.abs(wayToGoY / wayToGoX);
						Double parts = ratioYX + 1;
						Double onePart = 0.5 / parts;
						tiltY = ratioYX * onePart;
						tiltX = 0.5 - tiltY;
					}
					if (currentX > targetX) {
						tiltX = tiltX * -1;
					}

					if (currentY > targetY) {
						tiltY = tiltY * -1;
					}
				}

				scenario.getSteps().add(
						new AccelerateOnXYToMetersPerSecond(controller, tiltX, tiltY, DroneController.MAX_SPEED_XY));
				scenario.getSteps().add(new SetBackTilt(controller, -tiltX, -tiltY));
				scenario.getSteps().add(new AccelerateOnZToMetersPerSecond(controller, 0.0));
				scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
				scenario.getSteps().add(new ReachTarget(controller, target));

				Double breakTiltX = tiltX * -10;
				Double breakTiltY = tiltY * -10;

				scenario.getSteps().add(new BrakeOnXY(controller, breakTiltX, breakTiltY));
				scenario.getSteps().add(new SetBackTilt(controller, -breakTiltX, -breakTiltY));
				scenario.getSteps().add(new AccelerateOnZToMetersPerSecond(controller, 0.0));
				scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			}

			return this;
		}

		public ScenarioBuilder landThatDamnThing() {

			scenario.getSteps().add(new BreakOnZToMetersPerSecond(controller, DroneController.MAX_SPEED_DOWN));
			scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			scenario.getSteps().add(new WaitForMaxZToBeMet(controller, 3.0));
			scenario.getSteps().add(new AccelerateOnZToMetersPerSecond(controller, 0.0));
			scenario.getSteps().add(new BreakOnZToMetersPerSecond(controller, -0.35));
			scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			scenario.getSteps().add(new WaitForMaxZToBeMet(controller, 0.3));
			scenario.getSteps().add(new LandThatDrone(controller));
			return this;
		}

		public Scenario scenario() {
			return scenario;
		}
	}
}