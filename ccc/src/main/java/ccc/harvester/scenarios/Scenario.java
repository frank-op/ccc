package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.East;
import ccc.harvester.steps.EastWithMowersSouthernNeighbours;
import ccc.harvester.steps.EastWithMowersNorthernNeighbours;
import ccc.harvester.steps.EastWithoutMowing;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.North;
import ccc.harvester.steps.NorthWithMowersEasternNeighbours;
import ccc.harvester.steps.NorthWithMowersWesternNeighbours;
import ccc.harvester.steps.NorthWithoutMowing;
import ccc.harvester.steps.South;
import ccc.harvester.steps.SouthWithMowersEasternNeighbours;
import ccc.harvester.steps.SouthWithMowersWesternNeighbours;
import ccc.harvester.steps.SouthWithoutMowing;
import ccc.harvester.steps.West;
import ccc.harvester.steps.WestWithMowersSouthernNeighbours;
import ccc.harvester.steps.WestWithMowersNorthernNeighbours;
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

				@Override
				public Alignment getAlignment() {
					return null;
				}
			});
			return this;
		}

		public ScenarioBuilder goNorth(int... i) {
			steps.add(new North(field, i));
			return this;
		}

		public ScenarioBuilder goNorthWithMowersWesternNeighbours(int mowers, int... i) {
			return goNorthWithMowersWesternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goNorthWithMowersWesternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new NorthWithMowersWesternNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goNorthWithMowersEasternNeighbours(int mowers, int... i) {
			return goNorthWithMowersEasternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goNorthWithMowersEasternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new NorthWithMowersEasternNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goNorthWithoutMowing(int... i) {
			steps.add(new NorthWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goEast(int... i) {
			steps.add(new East(field, i));
			return this;
		}

		public ScenarioBuilder goEastWithMowersSouthernNeighbours(int mowers, int... i) {
			return goEastWithMowersSouthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goEastWithMowersSouthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new EastWithMowersSouthernNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goEastWithMowersNorthernNeighbours(int mowers, int... i) {
			return goEastWithMowersNorthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goEastWithMowersNorthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new EastWithMowersNorthernNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goEastWithoutMowing(int... i) {
			steps.add(new EastWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goSouth(int... i) {
			steps.add(new South(field, i));
			return this;
		}

		public ScenarioBuilder goSouthWithMowersEasternNeighbours(int mowers, int... i) {
			return goSouthWithMowersEasternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goSouthWithMowersEasternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new SouthWithMowersEasternNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goSouthWithMowersWesternNeighbours(int mowers, int... i) {
			return goSouthWithMowersWesternNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goSouthWithMowersWesternNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new SouthWithMowersWesternNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goSouthWithoutMowing(int... i) {
			steps.add(new SouthWithoutMowing(field, i));
			return this;
		}

		public ScenarioBuilder goWest(int... i) {
			steps.add(new West(field, i));
			return this;
		}

		public ScenarioBuilder goWestWithMowersSouthernNeighbours(int mowers, int... i) {
			return goWestWithMowersSouthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goWestWithMowersSouthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new WestWithMowersSouthernNeighbours(field, mowers, neighboursFirst, i));
			return this;
		}

		public ScenarioBuilder goWestWithMowersNorthernNeighbours(int mowers, int... i) {
			return goWestWithMowersNorthernNeighbours(mowers, false, i);
		}

		public ScenarioBuilder goWestWithMowersNorthernNeighbours(int mowers, boolean neighboursFirst, int... i) {
			steps.add(new WestWithMowersNorthernNeighbours(field, mowers, neighboursFirst, i));
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