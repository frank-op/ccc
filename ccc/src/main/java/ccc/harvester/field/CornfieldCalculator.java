package ccc.harvester.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ccc.harvester.scenarios.Scenario;
import ccc.harvester.steps.HarvestStep;

public class CornfieldCalculator {

	public static List<Cell> cellsNorthFromHere(CornField field, int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = row; i > 0; i--) {
			Cell cell = field.getCell(i, column);
			cells.add(cell);
		}
		return cells;
	}

	public static List<Cell> cellsNorthFromHere(CornField field, int startIndex, int count, int row, int column) {

		List<Cell> cellsNorthFromHere = cellsNorthFromHere(field, row, column);
		return getCellsCountRange(field, startIndex, count, cellsNorthFromHere);
	}

	public static List<Cell> cellsNorthFromHereWithEastNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {

		List<Cell> cellsNorthFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsNorthFromHere = cellsNorthFromHere(field, row, column);
		} else {
			cellsNorthFromHere = cellsNorthFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosEast(field, cellsNorthFromHere, mowers, neighboursFirst);
	}

	public static List<Cell> cellsNorthFromHereWithWestNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {
		List<Cell> cellsNorthFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsNorthFromHere = cellsNorthFromHere(field, row, column);
		} else {
			cellsNorthFromHere = cellsNorthFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosWest(field, cellsNorthFromHere, mowers, neighboursFirst);
	}

	private static List<Cell> getNeighbourinosNorth(CornField field, List<Cell> cells, int mowers,
			boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cells) {
			List<Cell> cellsNorthFromHere = cellsNorthFromHere(field, 1, mowers, cell.getRow(), cell.getColumn());
			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsNorthFromHere);
			} else {
				Collections.reverse(cellsNorthFromHere);
				listWithNeighbours.addAll(cellsNorthFromHere);
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public static List<Cell> cellsEastFromHere(CornField field, int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = column; i <= field.getColumns(); i++) {
			Cell cell = field.getCell(row, i);
			cells.add(cell);
		}
		return cells;
	}

	public static List<Cell> cellsEastFromHere(CornField field, int startIndex, int count, int row, int column) {

		List<Cell> cellsEastFromHere = cellsEastFromHere(field, row, column);
		return getCellsCountRange(field, startIndex, count, cellsEastFromHere);
	}

	public static List<Cell> cellsEastFromHereSouthNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {
		List<Cell> cellsEastFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsEastFromHere = cellsEastFromHere(field, row, column);
		} else {
			cellsEastFromHere = cellsEastFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosSouth(field, cellsEastFromHere, mowers, neighboursFirst);
	}

	public static List<Cell> cellsEastFromHereNorthNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {

		List<Cell> cellsEastFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsEastFromHere = cellsEastFromHere(field, row, column);
		} else {
			cellsEastFromHere = cellsEastFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosNorth(field, cellsEastFromHere, mowers, neighboursFirst);
	}

	private static List<Cell> getNeighbourinosEast(CornField field, List<Cell> cells, int mowers,
			boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cells) {

			List<Cell> cellsEastFromHere = cellsEastFromHere(field, 1, mowers, cell.getRow(), cell.getColumn());

			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsEastFromHere);
			} else {
				Collections.reverse(cellsEastFromHere);
				listWithNeighbours.addAll(cellsEastFromHere);
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public static List<Cell> cellsSouthFromHere(CornField field, int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = row; i <= field.getRows(); i++) {
			Cell cell = field.getCell(i, column);
			cells.add(cell);
		}
		return cells;
	}

	public static List<Cell> cellsSouthFromHere(CornField field, int startIndex, int count, int row, int column) {

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(field, row, column);
		return getCellsCountRange(field, startIndex, count, cellsSouthFromHere);
	}

	public static List<Cell> cellsSouthFromHereWithEastNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {

		List<Cell> cellsSouthFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsSouthFromHere = cellsSouthFromHere(field, row, column);
		} else {
			cellsSouthFromHere = cellsSouthFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosEast(field, cellsSouthFromHere, mowers, neighboursFirst);
	}

	public static List<Cell> cellsSouthFromHereWithWestNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {
		List<Cell> cellsSouthFromHere;

		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsSouthFromHere = cellsSouthFromHere(field, row, column);
		} else {
			cellsSouthFromHere = cellsSouthFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosWest(field, cellsSouthFromHere, mowers, neighboursFirst);
	}

	private static List<Cell> getNeighbourinosSouth(CornField field, List<Cell> cells, int mowers,
			boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cells) {

			List<Cell> cellsSouthFromHere = cellsSouthFromHere(field, 1, mowers, cell.getRow(), cell.getColumn());

			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsSouthFromHere);
			} else {
				Collections.reverse(cellsSouthFromHere);
				listWithNeighbours.addAll(cellsSouthFromHere);
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public static List<Cell> cellsWestFromHere(CornField field, int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = column; i > 0; i--) {
			Cell cell = field.getCell(row, i);
			cells.add(cell);
		}
		return cells;
	}

	public static List<Cell> cellsWestFromHere(CornField field, int startIndex, int count, int row, int column) {

		List<Cell> cellsWestFromHere = cellsWestFromHere(field, row, column);
		return getCellsCountRange(field, startIndex, count, cellsWestFromHere);
	}

	public static List<Cell> cellsWestFromHereWithSouthNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {

		List<Cell> cellsWestFromHere;
		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsWestFromHere = cellsWestFromHere(field, row, column);
		} else {
			cellsWestFromHere = cellsWestFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosSouth(field, cellsWestFromHere, mowers, neighboursFirst);
	}

	public static List<Cell> cellsWestFromHereWithNorthNeighbours(CornField field, int mowers, boolean neighboursFirst,
			int count, int row, int column) {

		List<Cell> cellsWestFromHere;
		if (count == HarvestStep.GET_UNTIL_THE_END) {
			cellsWestFromHere = cellsWestFromHere(field, row, column);
		} else {
			cellsWestFromHere = cellsWestFromHere(field, 0, count, row, column);
		}
		return getNeighbourinosNorth(field, cellsWestFromHere, mowers, neighboursFirst);
	}

	private static List<Cell> getNeighbourinosWest(CornField field, List<Cell> cells, int mowers,
			boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cells) {

			List<Cell> cellsWestFromHere = cellsWestFromHere(field, 1, mowers, cell.getRow(), cell.getColumn());

			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsWestFromHere);
			} else {
				Collections.reverse(cellsWestFromHere);
				listWithNeighbours.addAll(cellsWestFromHere);
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	private static List<Cell> getCellsCountRange(CornField field, int startIndex, int count, List<Cell> cells) {
		if (cells.size() >= count + 1) {
			return cells.subList(startIndex, count);
		} else {
			List<Cell> cellsInRange = new ArrayList<>();
			int i;
			for (i = startIndex; i < cells.size(); i++) {
				cellsInRange.add(cells.get(i));
			}

			if (Scenario.isFixEmptyCells()) {
				for (int j = i; j < count; j++) {
					cellsInRange.add(new Cell(0, 0, 0, field));
				}
			}
			return cellsInRange;
		}
	}
}