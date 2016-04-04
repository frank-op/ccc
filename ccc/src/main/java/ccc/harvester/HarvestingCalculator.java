package ccc.harvester;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ccc.harvester.CornField.CornerPosition;

public class HarvestingCalculator {

	public static String harvestInSerpentinesStartTopLeft(CornField field, int row, int column) {
		Scenario scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getScenario();
		return scenario.executeStepsSerpentine(field, row, column);
	}

	public static String harvestSerpentinesStartFromGivenCorner(CornField field, int row, int column) {
		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario;

		switch (whichCorner) {
		case TOP_LEFT:
			scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getScenario();
			break;
		case TOP_RIGHT:
			scenario = Scenario.build(field).goWest().goSouth(1).goEast().goSouth(1).getScenario();
			break;
		case BOTTOM_LEFT:
			scenario = Scenario.build(field).goEast().goNorth(1).goWest().goNorth(1).getScenario();
			break;
		case BOTTOM_RIGHT:
			scenario = Scenario.build(field).goWest().goNorth(1).goEast().goNorth(1).getScenario();
			break;
		case NOT_A_CORNER:
		default:
			throw new RuntimeException();
		}

		return scenario.executeStepsSerpentine(field, row, column);
	}

	public static String harvestSerpentinesStartFromGivenCornerColumns(CornField field, Direction direction, int row,
			int column) {

		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario = Scenario.build(null).error().getScenario();

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goEast(1).goNorth().goEast(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goSouth(1).goEast().goSouth(1).getScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goWest(1).goNorth().goWest(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEast().goNorth(1).goWest().goNorth(1).getScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goEast(1).goSouth().goEast(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goNorth(1).goEast().goNorth(1).getScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goWest(1).goSouth().goWest(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		return scenario.executeStepsSerpentine(field, row, column);
	}

	public static String harvestCircularStartFromGivenCorner(CornField field, Direction direction, int row,
			int column) {

		Scenario scenario = Scenario.build(null).error().getScenario();
		switch (direction) {
		case EAST:
			scenario = Scenario.build(field).goEast().goWest().getScenario();
			break;
		case SOUTH:
			scenario = Scenario.build(field).goSouth().goNorth().getScenario();
			break;
		case WEST:
			scenario = Scenario.build(field).goWest().goEast().getScenario();
			break;
		case NORTH:
			scenario = Scenario.build(field).goNorth().goSouth().getScenario();
			break;
		}
		return scenario.executeStepsCircular(field, row, column);
	}

	public static String harvestWith2Mowers(CornField field, Direction direction, int row, int column, Style style) {

		if (style == Style.C) {
			getCellsCircularStyle(field, direction, row, column);

		} else {

			Set<Cell> mowingSet = new LinkedHashSet<>();
			List<Cell> cells = getCellsSerpentineStyle(field, direction, row, column);

			switch (direction) {
			case WEST:
			case EAST:

				int i = 0;
				boolean switchy = false;

				while (mowingSet.size() < cells.size()) {

					if (i == 100) {
						return OutputFormatter.getFormattedContent(mowingSet);
					}
					Cell cell = cells.get(i);

					int currentCellRow = cell.getRow();
					int currentCellColumn = cell.getColumn();

					int fieldColumns = field.getColumns();

					Cell neighbourCell;
					if (switchy) {
						neighbourCell = field.getCellReturnNull(currentCellRow + 1, currentCellColumn);
					} else {
						neighbourCell = field.getCellReturnNull(currentCellRow - 1, currentCellColumn);
					}
					if (cell != null) {
						mowingSet.add(cell);
					}
					if (neighbourCell != null) {
						mowingSet.add(neighbourCell);
					}

					if ((i + 1) % fieldColumns == 0) {
						switchy = !switchy;
						if ((i + fieldColumns + fieldColumns) < field.getColumns() * field.getRows())
							i = i + fieldColumns + fieldColumns;
					}
					i++;
				}

				return OutputFormatter.getFormattedContent(mowingSet);
			case SOUTH:
				break;
			case NORTH:

				break;
			}

		}
		return null;
	}

	private static List<Cell> getCellsCircularStyle(CornField field, Direction direction, int row, int column) {
		Scenario scenario = Scenario.build(null).error().getScenario();
		switch (direction) {
		case EAST:
			scenario = Scenario.build(field).goEast().goWest().getScenario();
			break;
		case SOUTH:
			scenario = Scenario.build(field).goSouth().goNorth().getScenario();
			break;
		case WEST:
			scenario = Scenario.build(field).goWest().goEast().getScenario();
			break;
		case NORTH:
			scenario = Scenario.build(field).goNorth().goSouth().getScenario();
			break;
		}
		return scenario.executeStepsCircularAndGetCells(field, field.getCell(row, column));
	}

	private static List<Cell> getCellsSerpentineStyle(CornField field, Direction direction, int row, int column) {
		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario = Scenario.build(null).error().getScenario();

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goEast(1).goNorth().goEast(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goSouth(1).goEast().goSouth(1).getScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goWest(1).goNorth().goWest(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEast().goNorth(1).goWest().goNorth(1).getScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goEast(1).goSouth().goEast(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goNorth(1).goEast().goNorth(1).getScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goWest(1).goSouth().goWest(1).getScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		return scenario.executeStepsSerpentineAndGetCells(field, field.getCell(row, column));
	}

	public enum Style {
		S, C;
	}
}