package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.exec.Direction;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestWithNeighbours extends HarvestStep {

	public WestWithNeighbours(Direction direction, int mowers, boolean neighboursFirst, int... i) {
		super(direction, mowers, neighboursFirst, i);
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsWestFromHereWithNeighbours = null;

		switch (getDirection()) {
		case NORTH:
			cellsWestFromHereWithNeighbours = field.cellsWestFromHereWithNorthNeighbours(getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		case SOUTH:
			cellsWestFromHereWithNeighbours = field.cellsWestFromHereWithSouthNeighbours(getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		case EAST:
		case WEST:
			throw new RuntimeException("Values EAST and WEST are no good for neighbours!");
		}

		if (!cellsWestFromHereWithNeighbours.isEmpty()) {
			setLastCell(cellsWestFromHereWithNeighbours.get(cellsWestFromHereWithNeighbours.size() - 1));
		}

		return cellsWestFromHereWithNeighbours;
	}
}