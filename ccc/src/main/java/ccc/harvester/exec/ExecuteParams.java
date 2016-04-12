package ccc.harvester.exec;

public class ExecuteParams {

	private int fieldRows, fieldCols, startCellRow, startCellCol, mowers;
	private CornerPosition corner;
	private Direction direction;
	private Style style;

	private CornerPosition cornerConsideringMower;
	private int spaceToBorder;

	ExecuteParams(String args) {

		String[] argumentsAsArray = args.split(" ");

		// DON'T CHANGE THE ORDER!
		initFieldMembers(argumentsAsArray);
		initStartCellMembers(argumentsAsArray);
		initStyle(argumentsAsArray);
		initMowers(argumentsAsArray);
		initDirection(argumentsAsArray);
		initCornersAndSpace();
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

	private void initCornersAndSpace() {

		corner = whichCorner(false);
		cornerConsideringMower = whichCorner(true);

		if (corner == CornerPosition.NOT_A_CORNER) {
			switch (cornerConsideringMower) {
			case TOP_LEFT:
				if (isEastOrWest()) {
					spaceToBorder = getStartCellRow() - 1;
				} else {
					spaceToBorder = getStartCellCol() - 1;
				}
				break;
			case TOP_RIGHT:
				if (isEastOrWest()) {
					spaceToBorder = getStartCellRow() - 1;
				} else {
					spaceToBorder = getFieldCols() - getStartCellCol();
				}
				break;
			case BOTTOM_LEFT:
				if (isEastOrWest()) {
					spaceToBorder = getFieldRows() - getStartCellRow();
				} else {
					spaceToBorder = getStartCellCol() - 1;
				}
				break;
			case BOTTOM_RIGHT:
				if (isEastOrWest()) {
					spaceToBorder = getFieldRows() - getStartCellRow();
				} else {
					spaceToBorder = getFieldCols() - getStartCellCol();
				}
				break;
			case NOT_A_CORNER:
			default:
				throw new RuntimeException();
			}
		}
	}

	private boolean isEastOrWest() {
		return (getDirection() == Direction.EAST || getDirection() == Direction.WEST);
	}

	private CornerPosition whichCorner(boolean consideringMowers) {

		int spaceBecauseofMower = 0;

		if (consideringMowers) {
			spaceBecauseofMower = mowers - 1;
		}
		if (isBottomRight(spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_RIGHT;
		} else if (isBottomLeft(spaceBecauseofMower)) {
			return CornerPosition.BOTTOM_LEFT;
		} else if (isTopRight(spaceBecauseofMower)) {
			return CornerPosition.TOP_RIGHT;
		} else if (isTopLeft(spaceBecauseofMower)) {
			return CornerPosition.TOP_LEFT;
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

	public int getSpaceToBorder() {
		return spaceToBorder;
	}

	public void setSpaceToBorder(int spaceToBorder) {
		this.spaceToBorder = spaceToBorder;
	}

	public CornerPosition getCornerConsideringMower() {
		return cornerConsideringMower;
	}

	public void setCornerConsideringMower(CornerPosition cornerConsideringMower) {
		this.cornerConsideringMower = cornerConsideringMower;
	}

	@Override
	public String toString() {
		return "ExecuteParams [fieldRows=" + fieldRows + ", fieldCols=" + fieldCols + ", startCellRow=" + startCellRow
				+ ", startCellCol=" + startCellCol + ", mowers=" + mowers + ", direction=" + direction + ", style="
				+ style + "]";
	}

	public enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}
}