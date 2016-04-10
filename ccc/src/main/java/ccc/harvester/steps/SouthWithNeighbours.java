package ccc.harvester.steps;

import static ccc.harvester.field.CornfieldCalculator.cellsSouthFromHereWithEastNeighbours;
import static ccc.harvester.field.CornfieldCalculator.cellsSouthFromHereWithWestNeighbours;

import java.util.List;

import ccc.harvester.exec.Direction;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class SouthWithNeighbours extends HarvestStep {

	public SouthWithNeighbours(Direction direction, int mowers, boolean neighboursFirst, int... i) {
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

		List<Cell> cellsSouthFromHereWithNeighbours = null;

		switch (getDirection()) {
		case NORTH:
		case SOUTH:
			throw new RuntimeException("Values NORTH and SOUTH are no good for neighbours!");
		case EAST:
			cellsSouthFromHereWithNeighbours = cellsSouthFromHereWithEastNeighbours(field, getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		case WEST:
			cellsSouthFromHereWithNeighbours = cellsSouthFromHereWithWestNeighbours(field, getMowers(),
					isNeighboursFirst(), getCount(), row, column);
			break;
		}

		if (!cellsSouthFromHereWithNeighbours.isEmpty()) {
			setLastCell(cellsSouthFromHereWithNeighbours.get(cellsSouthFromHereWithNeighbours.size() - 1));
		}

		return cellsSouthFromHereWithNeighbours;
	}
}