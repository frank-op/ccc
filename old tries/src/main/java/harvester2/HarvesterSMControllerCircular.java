package harvester2;

public class HarvesterSMControllerCircular extends HarvesterSMController {

	int space = 0;

	public HarvesterSMControllerCircular(CornField field, Direction direction) {
		super(field, direction);
		getHarvesterSM().setUpSM(direction, field.whichCorner(field.getCurrentCell()), Style.CIRCULAR);
	}

	public String harvest() {

		int counter = 2;
		int fieldSize = getField().getSize();

		String firstCellContent = getField().getCellContent(getField().getCurrentCell()) + " ";
		StringBuilder builder = new StringBuilder(firstCellContent);

		while (counter <= fieldSize) {
			Cell newCurrentCell;

			try {
				newCurrentCell = getHarvesterSM().getState().moveToNewPositionAndGetIt(getField());
				builder.append(getField().getCellContent(newCurrentCell) + " ");
				counter++;
			} catch (CantGoThereException e) {
				try {
					handleCantGoThereException(builder);
					counter++;
				} catch (CantGoThereException | ArrayIndexOutOfBoundsException e1) {
					// This means the field is done :)
					return builder.append(" COUNTER: ").append(counter).toString();
				}
			}
		}

		return builder.toString();
	}

	private void handleCantGoThereException(StringBuilder builder) throws CantGoThereException {
		space++;

		int currentSpace = Math.abs(space / 2);

		getHarvesterSM().nextState();
		Cell currentCell = getField().getCurrentCell();

		Direction direction = getDirection();
		if (direction == Direction.NORTH || direction == Direction.SOUTH) {
			handleUpDown(currentSpace, currentCell, builder);
		} else {
			handleLeftRight(currentSpace, currentCell, builder);
		}
	}

	private void handleLeftRight(int currentSpace, Cell currentCell, StringBuilder builder)
			throws CantGoThereException {

		double halfOfTheRows = getField().getRows() / 2;

		int row = currentCell.getRow();

		if (row > halfOfTheRows) {
			currentCell.setRow(1 + currentSpace);
			getField().setCurrentCell(currentCell);
		} else {
			currentCell.setRow(getField().getRows() - currentSpace);
			getField().setCurrentCell(currentCell);
		}
		int cellContent = getField().getCellContent(currentCell);
		builder.append(cellContent + " ");
	}

	private void handleUpDown(int currentSpace, Cell currentCell, StringBuilder builder) throws CantGoThereException {
		double halfOfTheColumns = getField().getColumns() / 2;

		int column = currentCell.getColumn();

		if (column > halfOfTheColumns) {
			currentCell.setColumn(1 + currentSpace);
			getField().setCurrentCell(currentCell);
		} else {
			currentCell.setColumn(getField().getColumns() - currentSpace);
			getField().setCurrentCell(currentCell);
		}
		int cellContent = getField().getCellContent(currentCell);
		builder.append(cellContent + " ");
	}
}