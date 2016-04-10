package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestWithoutMowing extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public WestWithoutMowing(int[] i) {
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();

		if (i == Integer.MIN_VALUE) {
			lastCell = field.getCell(row, 1);
		} else {
			lastCell = field.getCellReturnNull(row, column - i);
		}
		return Collections.emptyList();
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