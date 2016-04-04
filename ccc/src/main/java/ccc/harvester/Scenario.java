package ccc.harvester;

import java.util.ArrayList;
import java.util.List;

import ccc.harvester.steps.East;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.North;
import ccc.harvester.steps.South;
import ccc.harvester.steps.West;

public class Scenario {

	List<HarvestStep> steps = new ArrayList<>();

	public String executeStepsCircular(CornField field, int row, int column) {
		return executeStepsCircular(field, field.getCell(row, column));
	}

	public String executeStepsCircular(CornField field, Cell startCell) {

		List<Cell> cells = executeStepsCircularAndGetCells(field, startCell);

		return OutputFormatter.getFormattedContent(cells);
	}

	public List<Cell> executeStepsCircularAndGetCells(CornField field, Cell startCell) {
		List<Cell> cells = new ArrayList<>();
		int iteration = 1;

		Cell currentCell = startCell;
		iterateScenarioCircular(field, cells, currentCell, iteration);
		return cells;
	}

	private void iterateScenarioCircular(CornField field, List<Cell> cells, Cell currentCell, int iteration) {

		for (HarvestStep harvestStep : steps) {
			int currentSpace = Math.abs(iteration / 2);
			cells.add(currentCell);
			iteration++;

			List<Cell> resultOfStep = harvestStep.doIt(currentCell);
			currentCell = resultOfStep.get(resultOfStep.size() - 1);

			if (resultOfStep.isEmpty()) {
				return;
			}

			if (harvestStep instanceof North || harvestStep instanceof South) {

				double halfOfTheColumns = field.getColumns() / 2;
				int row = currentCell.getRow();
				int column = currentCell.getColumn();

				if (column > halfOfTheColumns) {
					currentCell = field.getCell(row, 1 + currentSpace);
				} else {
					currentCell = field.getCell(row, field.getColumns() - currentSpace);
				}

			} else {
				double halfOfTheRows = field.getRows() / 2;

				int row = currentCell.getRow();
				int column = currentCell.getColumn();

				if (row > halfOfTheRows) {
					currentCell = field.getCell(1 + currentSpace, column);
				} else {
					currentCell = field.getCell(field.getColumns() - currentSpace, column);
				}

			}
			cells.addAll(resultOfStep);
		}
		if (cells.size() >= field.size()) {
			return;
		} else {
			iterateScenarioCircular(field, cells, currentCell, iteration);
		}
	}

	public String executeStepsSerpentine(CornField field, int row, int column) {

		return executeStepsSerpentine(field, field.getCell(row, column));
	}

	public String executeStepsSerpentine(CornField field, Cell startCell) {

		List<Cell> cells = executeStepsSerpentineAndGetCells(field, startCell);

		return OutputFormatter.getFormattedContent(cells);
	}

	public List<Cell> executeStepsSerpentineAndGetCells(CornField field, Cell startCell) {
		List<Cell> cells = new ArrayList<>();
		cells.add(startCell);

		Cell currentCell = startCell;
		iterateScenarioSerpentine(field, cells, currentCell);
		return cells;
	}

	private void iterateScenarioSerpentine(CornField field, List<Cell> cells, Cell currentCell) {
		for (HarvestStep harvestStep : steps) {

			List<Cell> resultOfStep = harvestStep.doIt(currentCell);
			if (resultOfStep.isEmpty()) {
				return;
			}
			currentCell = resultOfStep.get(resultOfStep.size() - 1);
			cells.addAll(resultOfStep);
		}
		if (cells.size() >= field.size()) {
			return;
		} else {
			iterateScenarioSerpentine(field, cells, currentCell);
		}
	}

	public static ScenarioBuilder build(CornField field) {
		return new ScenarioBuilder(field);
	}

	static class ScenarioBuilder {

		Scenario scenario = new Scenario();
		private CornField field;

		public ScenarioBuilder(CornField field) {
			this.field = field;
		}

		public ScenarioBuilder error() {
			scenario.steps.add(new HarvestStep() {
				@Override
				public List<Cell> doIt(Cell startCell) {
					throw new RuntimeException();
				}
			});
			return this;
		}

		public ScenarioBuilder goNorth(int... i) {
			scenario.steps.add(new North(field, i));
			return this;
		}

		public ScenarioBuilder goEast(int... i) {
			scenario.steps.add(new East(field, i));
			return this;
		}

		public ScenarioBuilder goSouth(int... i) {
			scenario.steps.add(new South(field, i));
			return this;
		}

		public ScenarioBuilder goWest(int... i) {
			scenario.steps.add(new West(field, i));
			return this;
		}

		public Scenario getScenario() {
			return this.scenario;
		}
	}
}