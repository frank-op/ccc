package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class NorthWithMowersWesternNeighbours extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public NorthWithMowersWesternNeighbours(CornField field, int mowers, boolean neighboursFirst, int[] i) {
		this.field = field;
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
	public List<Cell> doIt(Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsNorthFromHereMultiMowWestNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsNorthFromHereMultiMowWestNeighbours = field.cellsNorthFromHereMultiMowWestNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsNorthFromHereMultiMowWestNeighbours = field.cellsNorthFromHereMultiMowWestNeighbours(mowers,
					neighboursFirst, i, row, column);
		}

		if (!cellsNorthFromHereMultiMowWestNeighbours.isEmpty()) {
			lastCell = cellsNorthFromHereMultiMowWestNeighbours
					.get(cellsNorthFromHereMultiMowWestNeighbours.size() - 1);
		}
		return cellsNorthFromHereMultiMowWestNeighbours;
	}

	@Override
	public Cell getLastCell() {
		return lastCell;
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.VERTICAL;
	}
}