package harvester2;

public class HarvesterSMControllerSerpentine extends HarvesterSMController {

	public HarvesterSMControllerSerpentine(CornField field, Direction direction) {
		super(field, direction);
		getHarvesterSM().setUpSM(direction, field.whichCorner(field.getCurrentCell()), Style.SERPENTINE);
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
			} catch (CantGoThereException e) {
				try {
					handleCantGoThereException(builder);
					counter++;
				} catch (CantGoThereException | ArrayIndexOutOfBoundsException e1) {
					// This means the field is done :)
					return builder.append(" COUNTER: ").append(counter).toString();
				}
			}
			counter++;
		}

		return builder.toString();
	}

	private void handleCantGoThereException(StringBuilder builder) throws CantGoThereException {
		getHarvesterSM().nextState();
		State currentState = getHarvesterSM().getState();
		Cell newCurrentCell = currentState.moveToNewPositionAndGetIt(getField());
		int cellContent = getField().getCellContent(newCurrentCell);
		builder.append(cellContent + " ");

		getHarvesterSM().nextState();
		currentState = getHarvesterSM().getState();
		newCurrentCell = currentState.moveToNewPositionAndGetIt(getField());
		cellContent = getField().getCellContent(newCurrentCell);
		builder.append(cellContent + " ");
	}
}