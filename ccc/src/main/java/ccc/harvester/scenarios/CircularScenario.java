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
		boolean isCurPosLeftOrUp = isCellIsEitherLeftOrOnTop(startCell);

		LinkedHashSet<Cell> cells = new LinkedHashSet<>();
		iterateScenarioCircular(field, cells, startCell, 0, isCurPosLeftOrUp);
		return new ArrayList<Cell>(cells);
	}

	private boolean isCellIsEitherLeftOrOnTop(Cell startCell) {
		HarvestStep firstStep = getSteps().get(0);
		boolean isCurPosLeftOrUp = ((firstStep.getAlignment() == Alignment.VERTICAL && startCell.getColumn() == 1)
				|| (firstStep.getAlignment() == Alignment.HORIZONTAL && startCell.getRow() == 1));
		return isCurPosLeftOrUp;
	}

	private void iterateScenarioCircular(CornField field, Collection<Cell> cells, Cell currentCell, int iteration,
			boolean postionFlipSwitch) {

		for (HarvestStep harvestStep : getSteps()) {
			iteration++;

			List<Cell> resultOfStep = addCellsOfCurrentStep(field, cells, currentCell, harvestStep);

			if (cells.size() >= field.size()) {
				return;
			}

			currentCell = findNextCellToStartFrom(field, iteration, postionFlipSwitch, harvestStep, resultOfStep);
			postionFlipSwitch = !postionFlipSwitch;
		}
		iterateScenarioCircular(field, cells, currentCell, iteration, postionFlipSwitch);
	}

	private List<Cell> addCellsOfCurrentStep(CornField field, Collection<Cell> cells, Cell currentCell,
			HarvestStep harvestStep) {
		List<Cell> resultOfStep = harvestStep.doIt(currentCell);
		resultOfStep = fixEmptyCells(field, harvestStep, resultOfStep, cells);
		cells.addAll(resultOfStep);
		return resultOfStep;
	}

	private List<Cell> fixEmptyCells(CornField field, HarvestStep harvestStep, List<Cell> resultOfStep,
			Collection<Cell> cells) {

		if (Scenario.isFixEmptyCells()) {
			if (harvestStep.getMowers() > 1) {
				if (harvestStep.getAlignment() == Alignment.VERTICAL && resultOfStep.size() == field.getRows()
						|| harvestStep.getAlignment() == Alignment.HORIZONTAL
								&& resultOfStep.size() == field.getColumns()) {

					List<Cell> temp = new ArrayList<>();
					for (Cell cell : resultOfStep) {
						temp.add(cell);
						temp.add(new Cell(0, 0, 0, field));
					}
					resultOfStep = temp;
				} else if (areCellsOfLastStepAlreadyInCells(resultOfStep, cells)) {
					List<Cell> temp = new ArrayList<>();
					for (Cell cell : resultOfStep) {
						if (cells.contains(cell)) {
							temp.add(new Cell(0, 0, 0, field));
						} else {
							temp.add(cell);
						}
					}
					resultOfStep = temp;
				}
			}
		}
		return resultOfStep;
	}

	private boolean areCellsOfLastStepAlreadyInCells(List<Cell> resultOfStep, Collection<Cell> cells) {
		boolean isContained = false;
		for (Cell cell : cells) {
			if (resultOfStep.contains(cell)) {
				return true;
			}
		}

		return isContained;
	}

	private Cell findNextCellToStartFrom(CornField field, int iteration, boolean postionFlipSwitch,
			HarvestStep harvestStep, List<Cell> resultOfStep) {

		int currentSpaceFromFirstOfLastCell = Math.abs(iteration / 2) * harvestStep.getMowers();
		Cell lastCell = resultOfStep.get(resultOfStep.size() - 1);

		if (harvestStep.getAlignment() == Alignment.VERTICAL) {
			if (postionFlipSwitch) {
				return field.getCell(lastCell.getRow(), field.getColumns() - currentSpaceFromFirstOfLastCell);
			} else {
				return field.getCell(lastCell.getRow(), 1 + currentSpaceFromFirstOfLastCell);
			}
		} else {
			if (postionFlipSwitch) {
				return field.getCell(field.getRows() - currentSpaceFromFirstOfLastCell, lastCell.getColumn());
			} else {
				return field.getCell(1 + currentSpaceFromFirstOfLastCell, lastCell.getColumn());
			}
		}
	}
}