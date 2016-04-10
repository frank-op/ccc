package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class EastWithNorthernNeighbours extends HarvestStep {

	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public EastWithNorthernNeighbours(int mowers, boolean neighboursFirst, int[] i) {
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
	public List<Cell> doIt(CornField field, Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsEastFromHereMultiMowNorthNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsEastFromHereMultiMowNorthNeighbours = field.cellsEastFromHereMultiMowNorthNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsEastFromHereMultiMowNorthNeighbours = field.cellsEastFromHereMultiMowNorthNeighbours(mowers,
					neighboursFirst, i, row, column);
		}
		if (!cellsEastFromHereMultiMowNorthNeighbours.isEmpty()) {
			lastCell = cellsEastFromHereMultiMowNorthNeighbours
					.get(cellsEastFromHereMultiMowNorthNeighbours.size() - 1);
		}

		return cellsEastFromHereMultiMowNorthNeighbours;
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