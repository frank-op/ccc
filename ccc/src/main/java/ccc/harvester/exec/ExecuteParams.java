package ccc.harvester.exec;

public class ExecuteParams {

	private int fieldRows, fieldCols, startCellRow, startCellCol, mowers;
	private CornerPosition corner;
	private Direction direction;
	private Style style;

	ExecuteParams(String args) {

		String[] argumentsAsArray = args.split(" ");

		// DON'T CHANGE ORDER!
		initFieldMembers(argumentsAsArray);
		initStartCellMembers(argumentsAsArray);
		initStyle(argumentsAsArray);
		initMowers(argumentsAsArray);
		initCorner();
		initDirection(argumentsAsArray);
	}

	private void initFieldMembers(String[] argumentsAsArray) {
		String cornFieldRowsAsString = argumentsAsArray[0];
		String cornFieldColsAsString = argumentsAsArray[1];
		this.fieldRows = Integer.parseInt(cornFieldRowsAsString);
		this.fieldCols = Integer.parseInt(cornFieldColsAsString);
	}

	private void initStartCellMembers(String[] argumentsAsArray) {
		String startCellRow = argumentsAsArray[2];
		String startCellCol = argumentsAsArray[3];

		this.startCellRow = Integer.parseInt(startCellRow);
		this.startCellCol = Integer.parseInt(startCellCol);
	}

	private void initCorner() {
		corner = whichCorner();
	}

	private void initDirection(String[] argumentsAsArray) {

		String directionAsString = "O"; // default

		if (argumentsAsArray.length > 4) {
			directionAsString = argumentsAsArray[4];
		} else {
			if (corner == CornerPosition.BOTTOM_RIGHT || corner == CornerPosition.TOP_RIGHT) {
				directionAsString = "W";
			}
		}

		this.direction = Direction.getDirectionForString(directionAsString);
	}

	private void initStyle(String[] argumentsAsArray) {

		String styleAsString = "S"; // default

		if (argumentsAsArray.length > 5) {
			styleAsString = argumentsAsArray[5];
		}

		this.style = Style.getStyleForString(styleAsString);
	}

	private void initMowers(String[] argumentsAsArray) {

		String mowersAsString = "1"; // default

		if (argumentsAsArray.length > 6) {
			mowersAsString = argumentsAsArray[6];
		}

		this.mowers = Integer.parseInt(mowersAsString);
	}

	public int getFieldRows() {
		return fieldRows;
	}

	public int getFieldCols() {
		return fieldCols;
	}

	public int getStartCellRow() {
		return startCellRow;
	}

	public int getStartCellCol() {
		return startCellCol;
	}

	public int getMowers() {
		return mowers;
	}

	public Direction getDirection() {
		return direction;
	}

	public Style getStyle() {
		return style;
	}

	public CornerPosition getCorner() {
		return corner;
	}

	@Override
	public String toString() {
		return "ExecuteParams [fieldRows=" + fieldRows + ", fieldCols=" + fieldCols + ", startCellRow=" + startCellRow
				+ ", startCellCol=" + startCellCol + ", mowers=" + mowers + ", direction=" + direction + ", style="
				+ style + "]";
	}

	private CornerPosition whichCorner() {

		int spaceBecauseofMower = mowers - 1;

		if (isTopLeft(spaceBecauseofMower)) {
			return CornerPosition.TOP_LEFT;
		} else if (isBottomLeft(spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_LEFT;
		} else if (isTopRight(spaceBecauseofMower)) {
			return CornerPosition.TOP_RIGHT;
		} else if (isBottomRight(spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_RIGHT;
		}
		return CornerPosition.NOT_A_CORNER;
	}

	private boolean isTopLeft(int spaceBecauseofMower) {

		return (getStartCellRow() == 1 && getStartCellCol() == 1) //
				|| (getStartCellRow() == 1 && getStartCellCol() == 1 + spaceBecauseofMower)//
				|| (getStartCellRow() == 1 + spaceBecauseofMower && getStartCellCol() == 1);
	}

	private boolean isBottomLeft(int spaceBecauseofMower) {

		return (getStartCellRow() == getFieldRows() && getStartCellCol() == 1) //
				|| (getStartCellRow() == getFieldRows() && getStartCellCol() == 1 + spaceBecauseofMower)//
				|| (getStartCellRow() == getFieldRows() - spaceBecauseofMower && getStartCellCol() == 1);
	}

	private boolean isTopRight(int spaceBecauseofMower) {
		return (getStartCellRow() == 1 && getStartCellCol() == getFieldCols()) //
				|| (getStartCellRow() == 1 && getStartCellCol() == getFieldCols() - spaceBecauseofMower)//
				|| (getStartCellRow() == 1 + spaceBecauseofMower && getStartCellCol() == getFieldCols());
	}

	private boolean isBottomRight(int spaceBecauseofMower) {
		return (getStartCellRow() == getFieldRows() && getStartCellCol() == getFieldCols()) //
				|| (getStartCellRow() == getFieldRows() && getStartCellCol() == getFieldCols() - spaceBecauseofMower)//
				|| (getStartCellRow() == getFieldRows() - spaceBecauseofMower && getStartCellCol() == getFieldCols());
	}

	public enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}
}