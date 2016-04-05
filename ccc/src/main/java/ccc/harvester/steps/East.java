package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class East extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;

	public East(CornField field, int[] i) {
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
			return field.cellsEastFromHere(row, column);
		} else {
			return field.cellsEastFromHere(i, row, column);
		}
	}
}