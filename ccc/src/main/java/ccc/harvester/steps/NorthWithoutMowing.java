package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class NorthWithoutMowing extends HarvestStep {

	public NorthWithoutMowing(int[] i) {
		super(i);
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.VERTICAL;
	}

	@Override
	public List<Cell> doIt(CornField field, Cell startCell) {

		int row = startCell.getRow();
		int column = startCell.getColumn();
		setLastCell(field.getCellReturnNull(row - getCount(), column));
		return Collections.emptyList();
	}
}