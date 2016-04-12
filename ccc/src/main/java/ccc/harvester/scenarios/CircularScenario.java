package ccc.harvester.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import ccc.harvester.exec.ExecuteParams.CornerPosition;
import ccc.harvester.exec.ExecuteParams;
import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.HarvestStep;
import ccc.harvester.steps.HarvestStep.Alignment;

public class CircularScenario extends Scenario {

	private CornField field;
	private int spaceToBorder;

	public CircularScenario(List<HarvestStep> steps) {
		super(steps);
	}

	@Override
	public String executeSteps(ExecuteParams params) {

		field = new CornField(params.getFieldRows(), params.getFieldCols());
		System.out.println(field);
		spaceToBorder = params.getSpaceToBorder();

		Cell startCell = field.getCell(params.getStartCellRow(), params.getStartCellCol());
		CornerPosition corner = params.getCornerConsideringMower();

		List<Cell> cells = executeStepsCircularAndGetCells(startCell, corner);
		String formattedContent = OutputFormatter.getFormattedContent(cells);
		System.out.println(formattedContent + "\n\n");
		return formattedContent;
	}

	private List<Cell> executeStepsCircularAndGetCells(Cell startCell, CornerPosition corner) {

		boolean isCurPosLeftOrUp = isCellIsEitherLeftOrOnTop(startCell, corner);

		LinkedHashSet<Cell> cells = new LinkedHashSet<>();
		iterateScenarioCircular(cells, startCell, 0, isCurPosLeftOrUp);
		return new ArrayList<Cell>(cells);
	}

	private boolean isCellIsEitherLeftOrOnTop(Cell startCell, CornerPosition corner) {

		HarvestStep firstStep = getSteps().get(0);

		boolean isCurPosLeftOrUp;
		if (firstStep.getAlignment() == Alignment.VERTICAL) {
			isCurPosLeftOrUp = (corner == CornerPosition.TOP_LEFT || corner == CornerPosition.BOTTOM_LEFT);
		} else { // HORIZONTAL
			isCurPosLeftOrUp = (corner == CornerPosition.TOP_LEFT || corner == CornerPosition.TOP_RIGHT);
		}
		return isCurPosLeftOrUp;
	}

	private void iterateScenarioCircular(Collection<Cell> cells, Cell currentCell, int iteration,
			boolean isCurPosLeftOrUp) {

		for (HarvestStep harvestStep : getSteps()) {
			iteration++;

			addCellsOfCurrentStep(cells, currentCell, harvestStep);

			if (cells.size() >= field.size()) {
				return;
			}
			Cell lastCell = harvestStep.getLastCell();
			currentCell = findNextCellToStartFrom(iteration, isCurPosLeftOrUp, harvestStep, lastCell);
			isCurPosLeftOrUp = !isCurPosLeftOrUp;
		}
		iterateScenarioCircular(cells, currentCell, iteration, isCurPosLeftOrUp);
	}

	private void addCellsOfCurrentStep(Collection<Cell> cells, Cell currentCell, HarvestStep harvestStep) {
		List<Cell> resultOfStep = harvestStep.doIt(field, currentCell);
		resultOfStep = fixEmptyCells(harvestStep, resultOfStep, cells);
		cells.addAll(resultOfStep);
	}

	private List<Cell> fixEmptyCells(HarvestStep harvestStep, List<Cell> resultOfStep, Collection<Cell> cells) {

		if (Scenario.isFixEmptyCells()) {
			if (harvestStep.getMowers() > 1) {
				if (areCellsOfLastStepAlreadyInCells(resultOfStep, cells)) {
					resultOfStep = fixDoubles(resultOfStep, cells);
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

	private List<Cell> fixDoubles(List<Cell> resultOfStep, Collection<Cell> cells) {
		List<Cell> temp = new ArrayList<>();
		for (Cell cell : resultOfStep) {
			if (cells.contains(cell)) {
				temp.add(new Cell(0, 0, 0, field));
			} else {
				temp.add(cell);
			}
		}
		return temp;
	}

	private Cell findNextCellToStartFrom(int iteration, boolean isCurPosEastOrUp, HarvestStep harvestStep,
			Cell lastCell) {

		int currentSpaceFromFirstOrLastCell = Math.abs(iteration / 2) * harvestStep.getMowers();

		if (harvestStep.getAlignment() == Alignment.VERTICAL) {
			if (isCurPosEastOrUp) {
				return getNextCellFromWesternHalf(harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			} else {
				return getNextCellFromEasternHalf(harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			}
		} else { // is HORIZONTAL
			if (isCurPosEastOrUp) {
				return getNextCellFromLowerHalf(harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			} else {
				return getNextCellFromUpperHalf(harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			}
		}
	}

	private Cell getNextCellFromWesternHalf(HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfLastCell = lastCell.getRow();
		int columnOfNextCell = field.getColumns() - currentSpaceFromFirstOrLastCell - spaceToBorder;

		double halfOfTheField = (double) field.getColumns() / 2;
		if (columnOfNextCell < halfOfTheField) {
			columnOfNextCell = lastCell.getColumn() + harvestStep.getMowers();
		}
		return field.getCell(rowOfLastCell, columnOfNextCell);
	}

	private Cell getNextCellFromEasternHalf(HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfLastCell = lastCell.getRow();
		int columnOfNextCell = 1 + spaceToBorder + currentSpaceFromFirstOrLastCell;

		double halfOfTheField = (double) field.getColumns() / 2;
		if (columnOfNextCell > halfOfTheField) {
			columnOfNextCell = lastCell.getColumn() - harvestStep.getMowers();
		}
		return field.getCell(rowOfLastCell, columnOfNextCell);
	}

	private Cell getNextCellFromLowerHalf(HarvestStep harvestStep, Cell lastCell, int currentSpaceFromFirstOrLastCell) {

		int rowOfNextCell = field.getRows() - currentSpaceFromFirstOrLastCell - spaceToBorder;
		int columnOfLastCell = lastCell.getColumn();

		double halfOfTheField = (double) field.getRows() / 2;
		if (rowOfNextCell < halfOfTheField) {
			rowOfNextCell = lastCell.getRow() + harvestStep.getMowers();
		}
		return field.getCell(rowOfNextCell, columnOfLastCell);
	}

	private Cell getNextCellFromUpperHalf(HarvestStep harvestStep, Cell lastCell, int currentSpaceFromFirstOrLastCell) {

		int rowOfNextCell = 1 + spaceToBorder + currentSpaceFromFirstOrLastCell;
		int columnOfLastCell = lastCell.getColumn();

		double halfOfTheField = (double) field.getRows() / 2;
		if (rowOfNextCell > halfOfTheField) {
			rowOfNextCell = lastCell.getRow() - harvestStep.getMowers();
		}
		return field.getCell(rowOfNextCell, columnOfLastCell);
	}
}