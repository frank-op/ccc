package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class EastWithMowersSouthernNeighbours extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;
	private int mowers;
	private boolean neighboursFirst;
	private Cell lastCell;

	public EastWithMowersSouthernNeighbours(CornField field, int mowers, boolean neighboursFirst, int[] i) {
		this.field = field;
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
	public List<Cell> doIt(Cell startCell) {
		int row = startCell.getRow();
		int column = startCell.getColumn();

		List<Cell> cellsEastFromHereMultiMowSouthNeighbours;
		if (i == Integer.MIN_VALUE) {
			cellsEastFromHereMultiMowSouthNeighbours = field.cellsEastFromHereMultiMowSouthNeighbours(mowers,
					neighboursFirst, row, column);
		} else {
			cellsEastFromHereMultiMowSouthNeighbours = field.cellsEastFromHereMultiMowSouthNeighbours(mowers,
					neighboursFirst, i, row, column);
		}

		if (!cellsEastFromHereMultiMowSouthNeighbours.isEmpty()) {
			lastCell = cellsEastFromHereMultiMowSouthNeighbours
					.get(cellsEastFromHereMultiMowSouthNeighbours.size() - 1);
		}

		return cellsEastFromHereMultiMowSouthNeighbours;
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