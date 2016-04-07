package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class North extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public North(CornField field, int[] i) {
		this.field = field;
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsNorthFromHere;
		if (i == Integer.MIN_VALUE) {
			cellsNorthFromHere = field.cellsNorthFromHere(row, column);
		} else {
			cellsNorthFromHere = field.cellsNorthFromHere(0, i, row, column);
		}

		if (!cellsNorthFromHere.isEmpty()) {
			lastCell = cellsNorthFromHere.get(cellsNorthFromHere.size() - 1);
		}
		return cellsNorthFromHere;
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