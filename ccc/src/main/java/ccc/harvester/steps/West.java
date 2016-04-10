package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class West extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private Cell lastCell;

	public West(int[] i) {
		if (isArrayNullOrEmtpy(i)) {
			this.i = i[0];
		}
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsWestFromHere;
		if (i == Integer.MIN_VALUE) {
			cellsWestFromHere = field.cellsWestFromHere(row, column);
		} else {
			cellsWestFromHere = field.cellsWestFromHere(0, i, row, column);
		}
		if (!cellsWestFromHere.isEmpty()) {
			lastCell = cellsWestFromHere.get(cellsWestFromHere.size() - 1);
		}
		return cellsWestFromHere;
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