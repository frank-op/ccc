package ccc.drones.sim;

import java.util.ArrayList;
import java.util.List;

import ccc.drones.drone.DroneController;
import ccc.drones.sim.steps.AccelerateOnZToMetersPerSecond;
import ccc.drones.sim.steps.HoverOnZ;
import ccc.drones.sim.steps.SetThrottleToOvercomeGravity;
import ccc.drones.sim.steps.Step;
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
			scenario.getSteps().add(new HoverOnZ(controller));
			return this;
		}

		public Scenario scenario() {
			return scenario;
		}
	}
}