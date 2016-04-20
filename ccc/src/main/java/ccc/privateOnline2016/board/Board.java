package ccc.privateOnline2016.board;

public class Board {

	private int rows;
	private int columns;
	private Cell[][] matrix;
	private String initContent;

	public Board(int rows, int columns, String initContent) {
		this.rows = rows;
		this.columns = columns;
		this.initContent = initContent;
		initField(rows, columns);
	}

	private void initField(int x, int y) {
		int cellNumber = 1;

		matrix = new Cell[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = new Cell(i + 1, j + 1, cellNumber++, this, initContent);
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

	public Cell findCellByValue(int value) {

		int row;
		int column;
		if (value < getColumns()) {
			row = 1;
			column = value;
		} else {
			row = value / getColumns() + 1;
			column = value % getColumns();

			if (column == 0) {
				row--;
				column = getColumns();
			}

		}
		return getCell(row, column);
	}

	public Cell getCellReturnNull(int row, int column) {
		try {
			return matrix[row - 1][column - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Cell(0, 0, 0, this, "?");
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
				builder.append(getCell(i, j).getCellContent());
			}
			builder.append("\n");
		}

		return builder.substring(0, builder.length() - 1).toString();
	}
}