package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestWithMowersNorthernNeighbours extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;

	public WestWithMowersNorthernNeighbours(CornField field, int mowers, boolean neighboursFirst, int[] i) {
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

		if (i == Integer.MIN_VALUE) {
			return field.cellsWestFromHereMultiMowNorthNeighbours(mowers,neighboursFirst, row, column);
		} else {
			return field.cellsWestFromHereMultiMowNorthNeighbours(mowers,neighboursFirst, i, row, column);
		}
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}
}