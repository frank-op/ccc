package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class SouthWithMowersEasternNeighbours extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public SouthWithMowersEasternNeighbours(int mowers, boolean neighboursFirst, int[] i) {
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

		List<Cell> cellsSouthFromHereMultiMowEastNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsSouthFromHereMultiMowEastNeighbours = field.cellsSouthFromHereMultiMowEastNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsSouthFromHereMultiMowEastNeighbours = field.cellsSouthFromHereMultiMowEastNeighbours(mowers,
					neighboursFirst, i, row, column);
		}

		if (!cellsSouthFromHereMultiMowEastNeighbours.isEmpty()) {
			lastCell = cellsSouthFromHereMultiMowEastNeighbours
					.get(cellsSouthFromHereMultiMowEastNeighbours.size() - 1);
		}
		return cellsSouthFromHereMultiMowEastNeighbours;
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