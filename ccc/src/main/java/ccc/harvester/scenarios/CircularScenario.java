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

	public CircularScenario(List<HarvestStep> steps) {
		super(steps);
	}

	@Override
	public String executeSteps(ExecuteParams params) {

		CornField field = new CornField(params.getFieldRows(), params.getFieldCols());
		System.out.println(field);

		Cell startCell = field.getCell(params.getStartCellRow(), params.getStartCellCol());
		CornerPosition corner = params.getCorner();

		List<Cell> cells = executeStepsCircularAndGetCells(field, startCell, corner);
		return OutputFormatter.getFormattedContent(cells);
	}

	private List<Cell> executeStepsCircularAndGetCells(CornField field, Cell startCell, CornerPosition corner) {

		boolean isCurPosLeftOrUp = isCellIsEitherLeftOrOnTop(startCell, field, corner);

		LinkedHashSet<Cell> cells = new LinkedHashSet<>();
		iterateScenarioCircular(field, cells, startCell, 0, isCurPosLeftOrUp);
		return new ArrayList<Cell>(cells);
	}

	private boolean isCellIsEitherLeftOrOnTop(Cell startCell, CornField field, CornerPosition corner) {

		HarvestStep firstStep = getSteps().get(0);

		boolean isCurPosLeftOrUp;
		if (firstStep.getAlignment() == Alignment.VERTICAL) {
			isCurPosLeftOrUp = (corner == CornerPosition.TOP_LEFT || corner == CornerPosition.BOTTOM_LEFT);
		} else { // HORIZONTAL
			isCurPosLeftOrUp = (corner == CornerPosition.TOP_LEFT || corner == CornerPosition.TOP_RIGHT);
		}
		return isCurPosLeftOrUp;
	}

	private void iterateScenarioCircular(CornField field, Collection<Cell> cells, Cell currentCell, int iteration,
			boolean isCurPosLeftOrUp) {

		for (HarvestStep harvestStep : getSteps()) {
			iteration++;

			addCellsOfCurrentStep(field, cells, currentCell, harvestStep);

			if (cells.size() >= field.size()) {
				return;
			}
			Cell lastCell = harvestStep.getLastCell();
			currentCell = findNextCellToStartFrom(field, iteration, isCurPosLeftOrUp, harvestStep, lastCell);
			isCurPosLeftOrUp = !isCurPosLeftOrUp;
		}
		iterateScenarioCircular(field, cells, currentCell, iteration, isCurPosLeftOrUp);
	}

	private void addCellsOfCurrentStep(CornField field, Collection<Cell> cells, Cell currentCell,
			HarvestStep harvestStep) {
		List<Cell> resultOfStep = harvestStep.doIt(field, currentCell);
		resultOfStep = fixEmptyCells(field, harvestStep, resultOfStep, cells);
		cells.addAll(resultOfStep);
	}

	private List<Cell> fixEmptyCells(CornField field, HarvestStep harvestStep, List<Cell> resultOfStep,
			Collection<Cell> cells) {

		if (Scenario.isFixEmptyCells()) {
			if (harvestStep.getMowers() > 1) {
				if (areCellsOfLastStepAlreadyInCells(resultOfStep, cells)) {
					resultOfStep = fixDoubles(field, resultOfStep, cells);
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

	private List<Cell> fixDoubles(CornField field, List<Cell> resultOfStep, Collection<Cell> cells) {
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

	private Cell findNextCellToStartFrom(CornField field, int iteration, boolean isCurPosEastOrUp,
			HarvestStep harvestStep, Cell lastCell) {

		int currentSpaceFromFirstOrLastCell = Math.abs(iteration / 2) * harvestStep.getMowers();

		if (harvestStep.getAlignment() == Alignment.VERTICAL) {
			if (isCurPosEastOrUp) {
				return getNextCellFromWesternHalf(field, harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			} else {
				return getNextCellFromEasternHalf(field, harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			}
		} else { // is HORIZONTAL
			if (isCurPosEastOrUp) {
				return getNextCellFromLowerHalf(field, harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			} else {
				return getNextCellFromUpperHalf(field, harvestStep, lastCell, currentSpaceFromFirstOrLastCell);
			}
		}
	}

	// TODO: vielleicht braucht es das nicht mir dem extra zeug:
	// man muss eventuell einfach wenn das nicht in einem eck startet einfach
	// den Abstand schon
	// höher stellen... so ist das eh wieder ein gemurkse
	private Cell getNextCellFromWesternHalf(CornField field, HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfLastCell = lastCell.getRow();
		int columnOfNextCell = field.getColumns() - currentSpaceFromFirstOrLastCell;

		if (columnOfNextCell < field.getColumns() / 2) {
			columnOfNextCell = lastCell.getColumn() + harvestStep.getMowers();
		}
		return field.getCell(rowOfLastCell, columnOfNextCell);
	}

	private Cell getNextCellFromEasternHalf(CornField field, HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfLastCell = lastCell.getRow();
		int columnOfNextCell = 1 + currentSpaceFromFirstOrLastCell;

		if (columnOfNextCell > field.getColumns() / 2) {
			columnOfNextCell = lastCell.getColumn() - harvestStep.getMowers();
		}
		return field.getCell(rowOfLastCell, columnOfNextCell);
	}

	private Cell getNextCellFromLowerHalf(CornField field, HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfNextCell = field.getRows() - currentSpaceFromFirstOrLastCell;
		int columnOfLastCell = lastCell.getColumn();

		if (rowOfNextCell < field.getRows() / 2) {
			rowOfNextCell = lastCell.getRow() - harvestStep.getMowers();
		}
		return field.getCell(rowOfNextCell, columnOfLastCell);
	}

	private Cell getNextCellFromUpperHalf(CornField field, HarvestStep harvestStep, Cell lastCell,
			int currentSpaceFromFirstOrLastCell) {

		int rowOfNextCell = 1 + currentSpaceFromFirstOrLastCell;
		int columnOfLastCell = lastCell.getColumn();

		if (rowOfNextCell > field.getRows() / 2) {
			rowOfNextCell = lastCell.getRow() + harvestStep.getMowers();
		}
		return field.getCell(rowOfNextCell, columnOfLastCell);
	}
}