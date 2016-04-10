package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.List;

import ccc.harvester.exec.ExecuteParams;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.EastWithNorthernNeighbours;
import ccc.harvester.steps.EastWithSouthernNeighbours;
import ccc.harvester.steps.EastWithoutMowing;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.NorthWithEasternNeighbours;
import ccc.harvester.steps.NorthWithWesternNeighbours;
import ccc.harvester.steps.NorthWithoutMowing;
import ccc.harvester.steps.SouthWithEasternNeighbours;
import ccc.harvester.steps.SouthWithWesternNeighbours;
import ccc.harvester.steps.SouthWithoutMowing;
import ccc.harvester.steps.WestWithNorthernNeighbours;
import ccc.harvester.steps.WestWithSouthernNeighbours;
import ccc.harvester.steps.WestWithoutMowing;

public abstract class Scenario {

	private static boolean isFixEmptyCells;

	private List<HarvestStep> steps;

	public Scenario(List<HarvestStep> steps) {
		this.steps = steps;
	}

	public abstract String executeSteps(ExecuteParams params);

	public List<HarvestStep> getSteps() {
		return steps;
	}

	public void setSteps(List<HarvestStep> steps) {
		this.steps = steps;
	}

	public static ScenarioBuilder build() {
		return new ScenarioBuilder();
	}

	public static class ScenarioBuilder {

		private List<HarvestStep> steps = new ArrayList<>();

		public ScenarioBuilder() {
		}

		public ScenarioBuilder error() {
			steps.add(new HarvestStep() {
				@Override
				public Cell getLastCell() {
					return null;
				}

				@Override
				public Alignment getAlignment() {
					return null;
				}

				@Override
				public List<Cell> doIt(CornField field, Cell startCell) {
					throw new RuntimeException();
				}
			});
			return this;
		}

		public ScenarioBuilder goNorthWithWesternNeighbours(int mowers, int... i) {
			return goNorthWesternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goNorthWesternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new NorthWithWesternNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goNorthWithEasternNeighbours(int mowers, int... i) {
			return goNorthWithEasternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goNorthWithEasternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new NorthWithEasternNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goNorthWithoutMowing(int... i) {
			steps.add(new NorthWithoutMowing(i));
			return this;
		}

		public ScenarioBuilder goEastWithSouthernNeighbours(int mowers, int... i) {
			return goEastWithSouthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goEastWithSouthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new EastWithSouthernNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goEastWithNorthernNeighbours(int mowers, int... i) {
			return goEastWithNorthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goEastWithNorthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new EastWithNorthernNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goEastWithoutMowing(int... i) {
			steps.add(new EastWithoutMowing(i));
			return this;
		}

		public ScenarioBuilder goSouthWithEasternNeighbours(int mowers, int... i) {
			return goSouthEasternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goSouthEasternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new SouthWithEasternNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goSouthWithWesternNeighbours(int mowers, int... i) {
			return goSouthWithWesternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goSouthWithWesternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new SouthWithWesternNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goSouthWithoutMowing(int... i) {
			steps.add(new SouthWithoutMowing(i));
			return this;
		}

		public ScenarioBuilder goWestWithSouthernNeighbours(int mowers, int... i) {
			return goWestWithSouthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goWestWithSouthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new WestWithSouthernNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goWestWithNorthernNeighbours(int mowers, int... i) {
			return goWestWithNorthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goWestWithNorthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new WestWithNorthernNeighbours(mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goWestWithoutMowing(int... i) {
			steps.add(new WestWithoutMowing(i));
			return this;
		}

		public SerpentineScenario getSerpentineScenario() {
			return new SerpentineScenario(steps);
		}

		public CircularScenario getCircularScenario() {
			return new CircularScenario(steps);
		}
	}

	public static boolean isFixEmptyCells() {
		return isFixEmptyCells;
	}

	public static void setFixEmptyCells(boolean isFixEmptyCells) {
		Scenario.isFixEmptyCells = isFixEmptyCells;
	}
}