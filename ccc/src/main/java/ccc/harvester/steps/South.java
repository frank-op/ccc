package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class South extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public South(int[] i) {
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsSouthFromHere;
		if (i == Integer.MIN_VALUE) {
			cellsSouthFromHere = field.cellsSouthFromHere(row, column);
		} else {
			cellsSouthFromHere = field.cellsSouthFromHere(0, i, row, column);
		}
		if (!cellsSouthFromHere.isEmpty()) {
			lastCell = cellsSouthFromHere.get(cellsSouthFromHere.size() - 1);
		}
		return cellsSouthFromHere;
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