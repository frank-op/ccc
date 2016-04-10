package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class NorthWithEasternNeighbours extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public NorthWithEasternNeighbours(int mowers, boolean neighboursFirst, int[] i) {
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

		List<Cell> cellsNorthFromHereMultiMowEastNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsNorthFromHereMultiMowEastNeighbours = field.cellsNorthFromHereMultiMowEastNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsNorthFromHereMultiMowEastNeighbours = field.cellsNorthFromHereMultiMowEastNeighbours(mowers,
					neighboursFirst, i, row, column);
		}

		if (!cellsNorthFromHereMultiMowEastNeighbours.isEmpty()) {
			lastCell = cellsNorthFromHereMultiMowEastNeighbours
					.get(cellsNorthFromHereMultiMowEastNeighbours.size() - 1);
		}
		return cellsNorthFromHereMultiMowEastNeighbours;
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