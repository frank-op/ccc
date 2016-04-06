package ccc.harvester.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CornField {

	private int rows;
	private int columns;
	private Cell[][] matrix;

	public CornField(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		initField(rows, columns);
	}

	private void initField(int x, int y) {
		int cellNumber = 1;

		matrix = new Cell[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = new Cell(i + 1, j + 1, cellNumber++, this);
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Cell getCell(Cell cell) {
		return getCell(cell.getRow(), cell.getColumn());
	}

	public Cell getCell(int row, int column) {
		return matrix[row - 1][column - 1];
	}

	public Cell getCellReturnNull(int row, int column) {
		try {
			return matrix[row - 1][column - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int size() {
		return rows * columns;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				builder.append(String.format("%03d", getCell(i, j).getContent())).append(",");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public String getContentNorthFromHere(int row, int column) {
		StringBuilder builder = new StringBuilder();

		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		for (Cell cell : cellsNorthFromHere) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}

	public List<Cell> cellsNorthFromHereMultiMowEastNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		return getNeighbourinosEast(cellsNorthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsNorthFromHereMultiMowEastNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(count, row, column);
		return getNeighbourinosEast(cellsNorthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsNorthFromHereMultiMowWestNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		return getNeighbourinosWest(cellsNorthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsNorthFromHereMultiMowWestNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(count, row, column);
		return getNeighbourinosWest(cellsNorthFromHere, mowers, neighboursFirst);
	}

	private List<Cell> getNeighbourinosNorth(List<Cell> cellsNorthFromHere, int mowers, boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsNorthFromHere) {
			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsNorthFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
			} else {
				// TODO one must possibly flip the order if mowers > 2
				listWithNeighbours.addAll(cellsNorthFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public List<Cell> cellsNorthFromHere(int count, int row, int column) {

		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		return getCellsCountRange(count, cellsNorthFromHere);
	}

	public List<Cell> cellsNorthFromHere(int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = row; i > 0; i--) {
			Cell cell = getCell(i, column);
			cells.add(cell);
		}
		return cells;
	}

	public String getContentEastFromHere(int row, int column) {
		StringBuilder builder = new StringBuilder();

		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		for (Cell cell : cellsEastFromHere) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}

	public List<Cell> cellsEastFromHereMultiMowSouthNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		return getNeighbourinosSouth(cellsEastFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsEastFromHereMultiMowSouthNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(count, row, column);
		return getNeighbourinosSouth(cellsEastFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsEastFromHereMultiMowNorthNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		return getNeighbourinosNorth(cellsEastFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsEastFromHereMultiMowNorthNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(count, row, column);
		return getNeighbourinosNorth(cellsEastFromHere, mowers, neighboursFirst);
	}

	private List<Cell> getNeighbourinosEast(List<Cell> cellsEastFromHere, int mowers, boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsEastFromHere) {
			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsEastFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
			} else {
				// TODO one must possibly flip the order if mowers > 2
				listWithNeighbours.addAll(cellsEastFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public List<Cell> cellsEastFromHere(int count, int row, int column) {

		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		return getCellsCountRange(count, cellsEastFromHere);
	}

	public List<Cell> cellsEastFromHere(int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = column; i <= columns; i++) {
			Cell cell = getCell(row, i);
			cells.add(cell);
		}
		return cells;
	}

	public String getContentSouthFromHere(int row, int column) {
		StringBuilder builder = new StringBuilder();

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		for (Cell cell : cellsSouthFromHere) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}

	public List<Cell> cellsSouthFromHereMultiMowEastNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getNeighbourinosEast(cellsSouthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsSouthFromHereMultiMowEastNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosEast(cellsSouthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsSouthFromHereMultiMowWestNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getNeighbourinosWest(cellsSouthFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsSouthFromHereMultiMowWestNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosWest(cellsSouthFromHere, mowers, neighboursFirst);
	}

	private List<Cell> getNeighbourinosSouth(List<Cell> cellsSouthFromHere, int mowers, boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsSouthFromHere) {
			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsSouthFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
			} else {
				// TODO one must possibly flip the order if mowers > 2
				listWithNeighbours.addAll(cellsSouthFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public List<Cell> cellsSouthFromHere(int count, int row, int column) {

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getCellsCountRange(count, cellsSouthFromHere);
	}

	public List<Cell> cellsSouthFromHere(int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = row; i <= rows; i++) {
			Cell cell = getCell(i, column);
			cells.add(cell);
		}
		return cells;
	}

	public String getContentWestFromHere(int row, int column) {
		StringBuilder builder = new StringBuilder();

		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		for (Cell cell : cellsWestFromHere) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}

	public List<Cell> cellsWestFromHereMultiMowSouthNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		return getNeighbourinosSouth(cellsWestFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsWestFromHereMultiMowSouthNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsWestFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosSouth(cellsWestFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsWestFromHereMultiMowNorthNeighbours(int mowers, boolean neighboursFirst, int row,
			int column) {
		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		return getNeighbourinosNorth(cellsWestFromHere, mowers, neighboursFirst);
	}

	public List<Cell> cellsWestFromHereMultiMowNorthNeighbours(int mowers, boolean neighboursFirst, int count, int row,
			int column) {
		List<Cell> cellsWestFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosNorth(cellsWestFromHere, mowers, neighboursFirst);
	}

	private List<Cell> getNeighbourinosWest(List<Cell> cellsWestFromHere, int mowers, boolean neighboursFirst) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsWestFromHere) {
			if (!neighboursFirst) {
				listWithNeighbours.add(cell);
				listWithNeighbours.addAll(cellsWestFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
			} else {
				// TODO one must possibly flip the order if mowers > 2
				listWithNeighbours.addAll(cellsWestFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
				listWithNeighbours.add(cell);
			}
		}
		return listWithNeighbours;
	}

	public List<Cell> cellsWestFromHere(int count, int row, int column) {

		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		return getCellsCountRange(count, cellsWestFromHere);
	}

	public List<Cell> cellsWestFromHere(int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = column; i > 0; i--) {
			Cell cell = getCell(row, i);
			cells.add(cell);
		}
		return cells;
	}

	private List<Cell> getCellsCountRange(int count, List<Cell> cells) {
		if (cells.size() >= count + 1) {
			return cells.subList(1, count + 1);
		} else {
			return Collections.<Cell> emptyList();
		}
	}

	public CornerPosition whichCorner(int row, int column) {
		return whichCorner(row, column, 1);
	}

	public CornerPosition whichCorner(int row, int column, int mowers) {

		int spaceBecauseofMower = mowers - 1;

		if (isTopLeft(row, column, spaceBecauseofMower)) {
			return CornerPosition.TOP_LEFT;
		} else if (isBottomLeft(row, column, spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_LEFT;
		} else if (isTopRight(row, column, spaceBecauseofMower)) {
			return CornerPosition.TOP_RIGHT;
		} else if (isBottomRight(row, column, spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_RIGHT;
		}
		return CornerPosition.NOT_A_CORNER;
	}

	private boolean isTopLeft(int row, int column, int spaceBecauseofMower) {

		return (row == 1 && column == 1) //
				|| (row == 1 && column == 1 + spaceBecauseofMower)//
				|| (row == 1 + spaceBecauseofMower && column == 1);
	}

	private boolean isBottomLeft(int row, int column, int spaceBecauseofMower) {

		return (row == rows && column == 1) //
				|| (row == rows && column == 1 + spaceBecauseofMower)//
				|| (row == rows - spaceBecauseofMower && column == 1);
	}

	private boolean isTopRight(int row, int column, int spaceBecauseofMower) {
		return (row == 1 && column == columns) //
				|| (row == 1 && column == columns - spaceBecauseofMower)//
				|| (row == 1 + spaceBecauseofMower && column == columns);
	}

	private boolean isBottomRight(int row, int column, int spaceBecauseofMower) {
		return (row == rows && column == columns) //
				|| (row == rows && column == columns - spaceBecauseofMower)//
				|| (row == rows - spaceBecauseofMower && column == columns);
	}

	public enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}
}