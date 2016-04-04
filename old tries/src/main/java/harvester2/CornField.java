package harvester2;

public class CornField {

	enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}

	private int rows;
	private int columns;

	private int[][] matrix;

	private Cell currentCell;

	public CornField(int rows, int columns, Cell startPosition) {
		this.rows = rows;
		this.columns = columns;
		currentCell = startPosition;
		initField(rows, columns);
	}

	public CornerPosition whichCorner(Cell start) {
		return whichCorner(start.getRow(), start.getColumn());
	}

	private void initField(int x, int y) {
		int cellNumber = 1;

		matrix = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = cellNumber++;
			}
		}
	}

	public int[] getRow(int i) {
		return getMatrix()[i - 1];
	}

	public int getCellContent(Cell cell) {
		return getCellContent(cell.getRow(), cell.getColumn());
	}

	public int getCellContent(int row, int column) {
		return getMatrix()[row - 1][column - 1];
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				builder.append(String.format("%03d", getCellContent(i, j))).append(",");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int getXSize() {
		return rows;
	}

	public void setXSize(int x) {
		this.rows = x;
	}

	public int getYSize() {
		return columns;
	}

	public void setYSize(int y) {
		this.columns = y;
	}

	public Cell getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(Cell currentCell) throws CantGoThereException {
		if (!isCellValid(currentCell)) {
			throw new CantGoThereException();
		} else {
			this.currentCell = currentCell;
		}
	}

	private boolean isCellValid(Cell cell) {

		int thatRow = cell.getRow();
		int thatColumn = cell.getColumn();

		return thatRow > 0 && thatColumn > 0 && !(thatRow > this.rows) && !(thatColumn > this.columns);
	}

	public int getSize() {
		return rows * columns;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}