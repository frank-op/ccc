package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class SouthWithoutMowing extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public SouthWithoutMowing(int[] i) {
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();

		if (i == Integer.MIN_VALUE) {
			lastCell = field.getCell(field.getRows(), column);
		} else {
			lastCell = field.getCellReturnNull(row + i, column);
		}
		return Collections.emptyList();
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