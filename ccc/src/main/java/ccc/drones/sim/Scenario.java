package ccc.drones.sim;

import java.util.ArrayList;
import java.util.List;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.steps.AccelerateOnZToMetersPerSecond;
import ccc.drones.sim.steps.BreakOnZToMetersPerSecond;
import ccc.drones.sim.steps.BreakToZeroMetersPerSecondOnZ;
import ccc.drones.sim.steps.HoverOnZForSeconds;
import ccc.drones.sim.steps.LandThatDrone;
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

		public ScenarioBuilder landThatDamnThing() {

			scenario.getSteps().add(new BreakOnZToMetersPerSecond(controller, DroneController.MAX_SPEED_DOWN));
			scenario.getSteps().add(new SetThrottleToOvercomeGravity(controller));
			scenario.getSteps().add(new WaitForMaxZToBeMet(controller, 3.0));
			scenario.getSteps().add(new AccelerateOnZToMetersPerSecond(controller, 0.0));
			scenario.getSteps().add(new BreakOnZToMetersPerSecond(controller, -0.45));
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