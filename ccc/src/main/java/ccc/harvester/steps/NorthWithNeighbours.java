package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.exec.Direction;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class NorthWithNeighbours extends HarvestStep {

	public NorthWithNeighbours(Direction direction, int mowers, boolean neighboursFirst, int... i) {
		super(direction, mowers, neighboursFirst, i);
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.VERTICAL;
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsNorthFromHereWithNeighbours = null;

		switch (getDirection()) {
		case NORTH:
		case SOUTH:
			throw new RuntimeException("Values NORTH and SOUTH are no good for neighbours!");
		case EAST:
			cellsNorthFromHereWithNeighbours = field.cellsNorthFromHereWithEastNeighbours(getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		case WEST:
			cellsNorthFromHereWithNeighbours = field.cellsNorthFromHereWithWestNeighbours(getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		}

		if (!cellsNorthFromHereWithNeighbours.isEmpty()) {
			setLastCell(cellsNorthFromHereWithNeighbours.get(cellsNorthFromHereWithNeighbours.size() - 1));
		}

		return cellsNorthFromHereWithNeighbours;
	}
}