package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;

public abstract class HarvestStep {

	public abstract List<Cell> doIt(Cell startCell);

	protected boolean isArrayNullOrEmtpy(int[] i) {
		return i != null && i.length > 0;
	}
}