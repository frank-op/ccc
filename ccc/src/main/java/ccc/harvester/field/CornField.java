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

	public String getContentNorthFromHere(int row, int column) {
		StringBuilder builder = new StringBuilder();

		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		for (Cell cell : cellsNorthFromHere) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}

	public List<Cell> cellsNorthFromHereMultiMow(int mowers, int row, int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		return getNeighbourinosEast(cellsNorthFromHere, mowers);
	}

	public List<Cell> cellsNorthFromHereMultiMow(int mowers, int count, int row, int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(count, row, column);
		return getNeighbourinosEast(cellsNorthFromHere, mowers);
	}

	public List<Cell> cellsNorthFromHereMultiMowFaky(int mowers, int row, int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(row, column);
		return getNeighbourinosWest(cellsNorthFromHere, mowers);
	}

	public List<Cell> cellsNorthFromHereMultiMowFaky(int mowers, int count, int row, int column) {
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(count, row, column);
		return getNeighbourinosWest(cellsNorthFromHere, mowers);
	}

	private List<Cell> getNeighbourinosNorth(List<Cell> cellsNorthFromHere, int mowers) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsNorthFromHere) {
			listWithNeighbours.add(cell);
			List<Cell> neighbours = cellsNorthFromHere(mowers - 1, cell.getRow(), cell.getColumn());
			listWithNeighbours.addAll(neighbours);
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

	public List<Cell> cellsEastFromHereMultiMow(int mowers, int row, int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		return getNeighbourinosSouth(cellsEastFromHere, mowers);
	}

	public List<Cell> cellsEastFromHereMultiMow(int mowers, int count, int row, int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(count, row, column);
		return getNeighbourinosSouth(cellsEastFromHere, mowers);
	}

	public List<Cell> cellsEastFromHereMultiMowFaky(int mowers, int row, int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(row, column);
		return getNeighbourinosNorth(cellsEastFromHere, mowers);
	}

	public List<Cell> cellsEastFromHereMultiMowFaky(int mowers, int count, int row, int column) {
		List<Cell> cellsEastFromHere = cellsEastFromHere(count, row, column);
		return getNeighbourinosNorth(cellsEastFromHere, mowers);
	}

	private List<Cell> getNeighbourinosEast(List<Cell> cellsEastFromHere, int mowers) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsEastFromHere) {
			listWithNeighbours.add(cell);
			List<Cell> neighbours = cellsEastFromHere(mowers - 1, cell.getRow(), cell.getColumn());
			listWithNeighbours.addAll(neighbours);
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

	public List<Cell> cellsSouthFromHereMultiMow(int mowers, int row, int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getNeighbourinosEast(cellsSouthFromHere, mowers);
	}

	public List<Cell> cellsSouthFromHereMultiMow(int mowers, int count, int row, int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosEast(cellsSouthFromHere, mowers);
	}

	public List<Cell> cellsSouthFromHereMultiMowFaky(int mowers, int row, int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getNeighbourinosWest(cellsSouthFromHere, mowers);
	}

	public List<Cell> cellsSouthFromHereMultiMowFaky(int mowers, int count, int row, int column) {
		List<Cell> cellsSouthFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosWest(cellsSouthFromHere, mowers);
	}

	private List<Cell> getNeighbourinosSouth(List<Cell> cellsSouthFromHere, int mowers) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsSouthFromHere) {
			listWithNeighbours.add(cell);
			listWithNeighbours.addAll(cellsSouthFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
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

	public List<Cell> cellsWestFromHereMultiMow(int mowers, int row, int column) {
		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		return getNeighbourinosSouth(cellsWestFromHere, mowers);
	}

	public List<Cell> cellsWestFromHereMultiMow(int mowers, int count, int row, int column) {
		List<Cell> cellsWestFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosSouth(cellsWestFromHere, mowers);
	}

	public List<Cell> cellsWestFromHereMultiMowFaky(int mowers, int row, int column) {
		List<Cell> cellsWestFromHere = cellsWestFromHere(row, column);
		return getNeighbourinosNorth(cellsWestFromHere, mowers);
	}

	public List<Cell> cellsWestFromHereMultiMowFaky(int mowers, int count, int row, int column) {
		List<Cell> cellsWestFromHere = cellsSouthFromHere(count, row, column);
		return getNeighbourinosNorth(cellsWestFromHere, mowers);
	}

	private List<Cell> getNeighbourinosWest(List<Cell> cellsWestFromHere, int mowers) {

		List<Cell> listWithNeighbours = new ArrayList<>();

		for (Cell cell : cellsWestFromHere) {
			listWithNeighbours.add(cell);
			listWithNeighbours.addAll(cellsWestFromHere(mowers - 1, cell.getRow(), cell.getColumn()));
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

	public int size() {
		return rows * columns;
	}

	public enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}

	public CornerPosition whichCorner(int row, int column) {

		if (row == 1 && column == 1) {
			return CornerPosition.TOP_LEFT;
		} else if (row == rows && column == 1) {
			return CornerPosition.BOTTOM_LEFT;
		} else if (row == 1 && column == columns) {
			return CornerPosition.TOP_RIGHT;
		} else if (row == rows && column == columns) {
			return CornerPosition.BOTTOM_RIGHT;
		}
		return CornerPosition.NOT_A_CORNER;
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
}