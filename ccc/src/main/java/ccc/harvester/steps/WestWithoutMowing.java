package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class WestWithoutMowing extends HarvestStep {

	public WestWithoutMowing(int[] i) {
		super(i);
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();
		setLastCell(field.getCellReturnNull(row, column - getCount()));
		return Collections.emptyList();
	}
}