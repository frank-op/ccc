package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ccc.harvester.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.North;
import ccc.harvester.steps.South;

public class CircularScenario extends Scenario {

	public CircularScenario(List<HarvestStep> steps) {
		super(steps);
	}

	@Override
	public String executeSteps(CornField field, int row, int column) {
		return executeStepsCircular(field, field.getCell(row, column));
	}

	private String executeStepsCircular(CornField field, Cell startCell) {
		List<Cell> cells = executeStepsCircularAndGetCells(field, startCell);
		return OutputFormatter.getFormattedContent(cells);
	}

	private List<Cell> executeStepsCircularAndGetCells(CornField field, Cell startCell) {
		LinkedHashSet<Cell> cells = new LinkedHashSet<>();
		int iteration = 1;

		Cell currentCell = startCell;
		iterateScenarioCircular(field, cells, currentCell, iteration);
		return new ArrayList<Cell>(cells);
	}

	private void iterateScenarioCircular(CornField field, Collection<Cell> cells, Cell currentCell, int iteration) {

		for (HarvestStep harvestStep : getSteps()) {
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
}