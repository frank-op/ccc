package ccc.harvester.steps;

import static ccc.harvester.field.CornfieldCalculator.cellsEastFromHereNorthNeighbours;
import static ccc.harvester.field.CornfieldCalculator.cellsEastFromHereSouthNeighbours;

import java.util.List;

import ccc.harvester.exec.Direction;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class EastWithNeighbours extends HarvestStep {

	public EastWithNeighbours(Direction direction, int mowers, boolean neighboursFirst, int... i) {
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

		List<Cell> cellsEastFromHereWithNeighbours = null;

		switch (getDirection()) {
		case NORTH:
			cellsEastFromHereWithNeighbours = cellsEastFromHereNorthNeighbours(field, getMowers(), isNeighboursFirst(),
					getCount(), row, column);
			break;
		case SOUTH:
			cellsEastFromHereWithNeighbours = cellsEastFromHereSouthNeighbours(field, getMowers(), isNeighboursFirst(),
					getCount(), row, column);
			break;
		case EAST:
		case WEST:
			throw new RuntimeException("Values EAST and WEST are no good for neighbours!");
		}

		if (!cellsEastFromHereWithNeighbours.isEmpty()) {
			setLastCell(cellsEastFromHereWithNeighbours.get(cellsEastFromHereWithNeighbours.size() - 1));
		}

		return cellsEastFromHereWithNeighbours;
	}
}