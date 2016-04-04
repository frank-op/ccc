package harvester2;

public class Cell {
	private int row, column;

	public Cell(int x, int y) {
		super();
		this.row = x;
		this.column = y;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", column=" + column + "]";
	}
}