package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestMultiMowFaky extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;
	private int mowers;

	public WestMultiMowFaky(CornField field, int mowers, int[] i) {
		this.field = field;
		this.mowers = mowers;
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
			return field.cellsWestFromHereMultiMowFaky(mowers, row, column);
		} else {
			return field.cellsWestFromHereMultiMowFaky(mowers, i, row, column);
		}
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}
}