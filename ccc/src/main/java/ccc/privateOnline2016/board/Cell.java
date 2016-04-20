package ccc.privateOnline2016.board;

public class Cell {
	private int row, column;
	private int cellNumber;
	private Board board;
	private String cellContent;

	public Cell(int x, int y, int cellNumber, Board board, String cellContent) {
		this.row = x;
		this.column = y;
		this.board = board;
		this.cellNumber = cellNumber;
		this.cellContent = cellContent;
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

	public int getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(int content) {
		this.cellNumber = content;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setCellContent(String cellContent) {
		this.cellContent = cellContent;
	}

	public String getCellContent() {
		return cellContent;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", column=" + column + ", cellNumber=" + cellNumber + ", cellContent=" + cellContent
				+ "]";
	}
}