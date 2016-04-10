package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestWithNorthernNeighbours extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public WestWithNorthernNeighbours(int mowers, boolean neighboursFirst, int[] i) {
		this.mowers = mowers;
		this.neighboursFirst = neighboursFirst;
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public int getMowers() {
		return mowers;
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsWestFromHereMultiMowNorthNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsWestFromHereMultiMowNorthNeighbours = field.cellsWestFromHereMultiMowNorthNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsWestFromHereMultiMowNorthNeighbours = field.cellsWestFromHereMultiMowNorthNeighbours(mowers,
					neighboursFirst, i, row, column);
		}
		if (!cellsWestFromHereMultiMowNorthNeighbours.isEmpty()) {
			lastCell = cellsWestFromHereMultiMowNorthNeighbours
					.get(cellsWestFromHereMultiMowNorthNeighbours.size() - 1);
		}
		return cellsWestFromHereMultiMowNorthNeighbours;
	}

	@Override
	public Cell getLastCell() {
		return lastCell;
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}
}