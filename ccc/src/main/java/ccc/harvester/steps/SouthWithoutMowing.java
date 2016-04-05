package ccc.harvester.steps;

import java.util.Collections;
import java.util.List;

import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class SouthWithoutMowing extends HarvestStep {

	private CornField field;
	private int i = Integer.MIN_VALUE;

	public SouthWithoutMowing(CornField field, int[] i) {
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
			return Collections.singletonList(field.getCell(field.getRows(), column));
		} else {
			Cell cellReturnNull = field.getCellReturnNull(row + i, column);
			if (cellReturnNull == null)
				return Collections.emptyList();
			else {
				return Collections.singletonList(cellReturnNull);
			}
		}
	}

	@Override
	public Alignment getAlignment() {
		return Alignment.VERTICAL;
	}
}