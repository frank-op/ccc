package ccc.harvester.field;

public class Cell {
	private int row, column;
	private int content;
	private CornField field;

	public Cell(int x, int y, int content, CornField field) {
		this.row = x;
		this.column = y;
		this.setContent(content);
		this.setField(field);
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

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public CornField getField() {
		return field;
	}

	public void setField(CornField field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return String.valueOf(content);
	}
}