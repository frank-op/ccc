package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.Cell;
import ccc.harvester.CornField;

public class South extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;

	public South(CornField field, int[] i) {
		this.field = field;
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		if (i == Integer.MIN_VALUE) {
			return field.cellsSouthFromHere(row, column);
		} else {
			return field.cellsSouthFromHere(i, row, column);
		}
	}
}