package ccc.harvester.field;

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
			return new Cell(0, 0, 0, this);
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
				builder.append(String.format("%05d", getCell(i, j).getContent())).append(",");
			}
			builder.append("\n");
		}

		return builder.toString();
	}
}