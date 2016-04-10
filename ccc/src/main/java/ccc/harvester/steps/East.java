package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class East extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public East(int[] i) {
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsEastFromHere;
		if (i == Integer.MIN_VALUE) {
			cellsEastFromHere = field.cellsEastFromHere(row, column);
		} else {
			cellsEastFromHere = field.cellsEastFromHere(0, i, row, column);
		}

		if (!cellsEastFromHere.isEmpty()) {
			lastCell = cellsEastFromHere.get(cellsEastFromHere.size() - 1);
		}

		return cellsEastFromHere;
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}

	@Override
	public Cell getLastCell() {
		return lastCell;
	}
}