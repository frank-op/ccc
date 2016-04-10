package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class EastWithoutMowing extends HarvestStep {

	public EastWithoutMowing(int[] i) {
		super(i);
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();
		setLastCell(field.getCellReturnNull(row, column + getCount()));
		return Collections.emptyList();
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.HORIZONTAL;
	}
}