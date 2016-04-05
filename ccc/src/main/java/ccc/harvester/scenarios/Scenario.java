package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.East;
import ccc.harvester.steps.EastMultiMow;
import ccc.harvester.steps.EastMultiMowFaky;
import ccc.harvester.steps.EastWithoutMowing;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.North;
import ccc.harvester.steps.NorthMultiMow;
import ccc.harvester.steps.NorthMultiMowFaky;
import ccc.harvester.steps.NorthWithoutMowing;
import ccc.harvester.steps.South;
import ccc.harvester.steps.SouthMultiMow;
import ccc.harvester.steps.SouthMultiMowFaky;
import ccc.harvester.steps.SouthWithoutMowing;
import ccc.harvester.steps.West;
import ccc.harvester.steps.WestMultiMow;
import ccc.harvester.steps.WestMultiMowFaky;
import ccc.harvester.steps.WestWithoutMowing;

public abstract class Scenario {

	private List<HarvestStep> steps;

	public Scenario(List<HarvestStep> steps) {
		this.steps = steps;
	}

	public abstract String executeSteps(CornField field, int row, int column);

	public List<HarvestStep> getSteps() {
		return steps;
	}

	public void setSteps(List<HarvestStep> steps) {
		this.steps = steps;
	}

	public static ScenarioBuilder build(CornField field) {
		return new ScenarioBuilder(field);
	}

	public static class ScenarioBuilder {

		private List<HarvestStep> steps = new ArrayList<>();
		private CornField field;

		public ScenarioBuilder(CornField field) {
			this.field = field;
		}

		public ScenarioBuilder error() {
			steps.add(new HarvestStep() {
				@Override
				public List<Cell> doIt(Cell startCell) {
					throw new RuntimeException();
				}
			});
			return this;
		}

		public ScenarioBuilder goNorthWithMowers(int mowers, int... i) {
			steps.add(new NorthMultiMow(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goEastWithMowers(int mowers, int... i) {
			steps.add(new EastMultiMow(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goSouthWithMowers(int mowers, int... i) {
			steps.add(new SouthMultiMow(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goWestWithMowers(int mowers, int... i) {
			steps.add(new WestMultiMow(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goNorthWithMowersFaky(int mowers, int... i) {
			steps.add(new NorthMultiMowFaky(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goEastWithMowersFaky(int mowers, int... i) {
			steps.add(new EastMultiMowFaky(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goSouthWithMowersFaky(int mowers, int... i) {
			steps.add(new SouthMultiMowFaky(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goWestWithMowersFaky(int mowers, int... i) {
			steps.add(new WestMultiMowFaky(field, mowers, i));
			return this;
		}

		public ScenarioBuilder goNorth(int... i) {
			steps.add(new North(field, i));
			return this;
		}

		public ScenarioBuilder goEast(int... i) {
			steps.add(new East(field, i));
			return this;
		}

		public ScenarioBuilder goSouth(int... i) {
			steps.add(new South(field, i));
			return this;
		}

		public ScenarioBuilder goWest(int... i) {
			steps.add(new West(field, i));
			return this;
		}

		public ScenarioBuilder goNorthWithoutMowing(int... i) {
			steps.add(new NorthWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goEastWithoutMowing(int... i) {
			steps.add(new EastWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goSouthWithoutMowing(int... i) {
			steps.add(new SouthWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goWestWithoutMowing(int... i) {
			steps.add(new WestWithoutMowing(field, i));
			return this;
		}

		public SerpentineScenario getSerpentineScenario() {
			return new SerpentineScenario(steps);
		}

		public CircularScenario getCircularScenario() {
			return new CircularScenario(steps);
		}
	}
}