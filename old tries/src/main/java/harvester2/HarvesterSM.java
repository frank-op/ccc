package harvester2;

import harvester2.CornField.CornerPosition;

public class HarvesterSM {

	private final North north = new North();
	private final East east = new East();
	private final South south = new South();
	private final West west = new West();

	private final State[] transitionsSerpNorthStartBottomRight = new State[] { north, west, south, west };
	private final State[] transitionsSerpNorthStartBottomLeft = new State[] { north, east, south, east };

	private final State[] transitionsSerpSouthStartTopRight = new State[] { south, west, north, west };
	private final State[] transitionsSerpSouthStartTopLeft = new State[] { south, east, north, east };

	private final State[] transitionsSerpWestStartTopRight = new State[] { west, south, east, south };
	private final State[] transitionsSerpWestStartBottomRight = new State[] { west, north, east, north };

	private final State[] transitionsSerpEastStartTopLeft = new State[] { east, south, west, south };
	private final State[] transitionsSerpEastStartBottomLeft = new State[] { east, north, west, north };

	private final State[] transitionsCircNorth = new State[] { north, south, north, south };

	private final State[] transitionsCircSouth = new State[] { south, north, south, north };

	private final State[] transitionsCircWest = new State[] { west, east, west, east };

	private final State[] transitionsCircEast = new State[] { east, west, east, west };

	private int currentState = 0;
	private State[] currentTransition;

	public void nextState() {
		currentState = (currentState + 1) % 4;
	}

	public State getState() {
		return currentTransition[currentState];
	}

	public void setUpSM(Direction direction, CornerPosition cornerPosition, Style style) {

		if (style == Style.CIRCULAR) {
			handleCircular(direction);
		} else if (style == Style.SERPENTINE) {
			handleSerpentine(direction, cornerPosition);
		}

	}

	private void handleCircular(Direction direction) {
		switch (direction) {
		case NORTH:
			setCurrentTransition(transitionsCircNorth);
			return;
		case EAST:
			setCurrentTransition(transitionsCircEast);
			return;
		case SOUTH:
			setCurrentTransition(transitionsCircSouth);
			return;
		case WEST:
			setCurrentTransition(transitionsCircWest);
			return;
		}
	}

	private void handleSerpentine(Direction direction, CornerPosition cornerPosition) {
		switch (cornerPosition) {
		case TOP_LEFT:
			switch (direction) {
			case EAST:
				setCurrentTransition(transitionsSerpEastStartTopLeft);
				return;
			case SOUTH:
				setCurrentTransition(transitionsSerpSouthStartTopLeft);
				return;
			default:
				throw new RuntimeException();
			}
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				setCurrentTransition(transitionsSerpWestStartTopRight);
				return;
			case SOUTH:
				setCurrentTransition(transitionsSerpSouthStartTopRight);
				return;
			default:
				throw new RuntimeException();
			}

		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				setCurrentTransition(transitionsSerpEastStartBottomLeft);
				return;
			case NORTH:
				setCurrentTransition(transitionsSerpNorthStartBottomLeft);
				return;
			default:
				throw new RuntimeException();
			}

		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				setCurrentTransition(transitionsSerpWestStartBottomRight);
				return;
			case NORTH:
				setCurrentTransition(transitionsSerpNorthStartBottomRight);
				return;
			default:
				throw new RuntimeException();
			}

		case NOT_A_CORNER:
			throw new RuntimeException();

		}
	}

	public State[] getCurrentTransition() {
		if (currentTransition == null) {
			throw new RuntimeException("No transition set!");
		}
		return currentTransition;
	}

	public void setCurrentTransition(State[] currentTransition) {
		this.currentTransition = currentTransition;
	}

	class North implements State {

		public Cell moveToNewPositionAndGetIt(CornField field) throws CantGoThereException {

			Cell oldCell = field.getCurrentCell();
			int row = oldCell.getRow();
			int column = oldCell.getColumn();

			Cell newCell = new Cell(row - 1, column);
			field.setCurrentCell(newCell);

			return newCell;
		}

		@Override
		public String toString() {
			return "North";
		}
	}

	class East implements State {

		public Cell moveToNewPositionAndGetIt(CornField field) throws CantGoThereException {
			Cell oldCell = field.getCurrentCell();
			int row = oldCell.getRow();
			int column = oldCell.getColumn();

			Cell newCell = new Cell(row, column + 1);
			field.setCurrentCell(newCell);

			return newCell;
		}

		@Override
		public String toString() {
			return "East";
		}
	}

	class South implements State {

		public Cell moveToNewPositionAndGetIt(CornField field) throws CantGoThereException {
			Cell oldCell = field.getCurrentCell();
			int row = oldCell.getRow();
			int column = oldCell.getColumn();

			Cell newCell = new Cell(row + 1, column);
			field.setCurrentCell(newCell);

			return newCell;
		}

		@Override
		public String toString() {
			return "South";
		}
	}

	class West implements State {

		public Cell moveToNewPositionAndGetIt(CornField field) throws CantGoThereException {
			Cell oldCell = field.getCurrentCell();
			int row = oldCell.getRow();
			int column = oldCell.getColumn();

			Cell newCell = new Cell(row, column - 1);
			field.setCurrentCell(newCell);

			return newCell;
		}

		@Override
		public String toString() {
			return "West";
		}
	}
}