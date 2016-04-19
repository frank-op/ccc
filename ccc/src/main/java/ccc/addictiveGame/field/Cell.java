package ccc.addictiveGame.field;

public class Cell {
	private int row, column;
	private int content;
	private Board board;
	private int color;

	public Cell(int x, int y, int content, Board board) {
		this.row = x;
		this.column = y;
		this.board = board;
		this.content = content;
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", column=" + column + ", content=" + content + ", color=" + color + "]";
	}

}