package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ccc.harvester.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.HarvestStep.Alignment;

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
		int iteration = 0;

		HarvestStep firstStep = getSteps().get(0);
		boolean isCurPosLeftOrUp = ((firstStep.getAlignment() == Alignment.VERTICAL && startCell.getColumn() == 1)
				|| (firstStep.getAlignment() == Alignment.HORIZONTAL && startCell.getRow() == 1));

		iterateScenarioCircular(field, cells, startCell, iteration, isCurPosLeftOrUp);
		return new ArrayList<Cell>(cells);
	}

	private void iterateScenarioCircular(CornField field, Collection<Cell> cells, Cell currentCell, int iteration,
			boolean isCurPosLeftOrUp) {

		for (HarvestStep harvestStep : getSteps()) {
			iteration++;
			int currentSpace = Math.abs(iteration / 2);

			List<Cell> resultOfStep = harvestStep.doIt(currentCell);
			currentCell = resultOfStep.get(resultOfStep.size() - 1);

			if (resultOfStep.isEmpty()) {
				return;
			}

			currentCell = figureOutNextCell(field, currentCell, isCurPosLeftOrUp, harvestStep, currentSpace);

			isCurPosLeftOrUp = !isCurPosLeftOrUp;
			cells.addAll(resultOfStep);

			if (cells.size() >= field.size()) {
				return;
			}
		}

		iterateScenarioCircular(field, cells, currentCell, iteration, isCurPosLeftOrUp);
	}

	private Cell figureOutNextCell(CornField field, Cell currentCell, boolean isCurPosLeftOrUp, HarvestStep harvestStep,
			int currentSpace) {

		if (harvestStep.getAlignment() == Alignment.VERTICAL) {

			if (isCurPosLeftOrUp) {
				currentCell = field.getCell(currentCell.getRow(),
						field.getColumns() - currentSpace * harvestStep.getMowers());
			} else {
				currentCell = field.getCell(currentCell.getRow(), currentSpace * harvestStep.getMowers() + 1);
			}
		} else {
			if (isCurPosLeftOrUp) {
				currentCell = field.getCell(field.getRows() - currentSpace * harvestStep.getMowers(),
						currentCell.getColumn());
			} else {
				currentCell = field.getCell(currentSpace * harvestStep.getMowers() + 1, currentCell.getColumn());
			}
		}
		return currentCell;
	}
}