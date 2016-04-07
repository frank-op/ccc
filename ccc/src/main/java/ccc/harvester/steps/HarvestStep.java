package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.field.Cell;

public abstract class HarvestStep {

	public abstract List<Cell> doIt(Cell startCell);

	public abstract Alignment getAlignment();

	public abstract Cell getLastCell();

	public int getMowers() {
		return 1;
	}

	protected boolean isArrayNullOrEmtpy(int[] i) {
		return i != null && i.length > 0;
	}

	public enum Alignment {
		HORIZONTAL, VERTICAL;
	}
}