package ccc.harvester;

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
		cells.remove(0);
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
		cells.remove(0);
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

	public List<Cell> cellsSouthFromHere(int count, int row, int column) {

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(row, column);
		return getCellsCountRange(count, cellsSouthFromHere);
	}

	private List<Cell> getCellsCountRange(int count, List<Cell> cells) {
		if (cells.size() >= count) {
			return cells.subList(0, count);
		} else {
			return Collections.<Cell> emptyList();
		}
	}

	public List<Cell> cellsSouthFromHere(int row, int column) {
		List<Cell> cells = new ArrayList<>();

		for (int i = row; i <= rows; i++) {
			Cell cell = getCell(i, column);
			cells.add(cell);
		}
		cells.remove(0);
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
		cells.remove(0);
		return cells;
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
				builder.append(String.format("%02d", getCell(i, j).getContent())).append(",");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public int size() {
		return rows * columns;
	}

	enum CornerPosition {
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