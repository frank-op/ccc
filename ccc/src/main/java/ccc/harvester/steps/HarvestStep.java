package ccc.harvester.steps;

import java.util.List;

import ccc.harvester.exec.Direction;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public abstract class HarvestStep {

	public final static int GET_UNTIL_THE_END = -1;

	private Direction direction;
	private int mowers = 1;
	private boolean neighboursFirst;
	private int count = GET_UNTIL_THE_END;

	private Cell lastCell;

	public HarvestStep(int[] i) {
		if (!isArrayNullOrEmtpy(i)) {
			this.count = i[0];
		}
	}

	public HarvestStep(Direction direction, int mowers, boolean neighboursFirst, int... i) {
		this.direction = direction;
		this.mowers = mowers;
		this.neighboursFirst = neighboursFirst;

		if (!isArrayNullOrEmtpy(i)) {
			this.count = i[0];
		}
	}

	public abstract List<Cell> doIt(CornField field, Cell startCell);

	public abstract Alignment getAlignment();

	protected boolean isArrayNullOrEmtpy(int[] i) {
		return i == null || i.length == 0;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isNeighboursFirst() {
		return neighboursFirst;
	}

	public void setNeighboursFirst(boolean neighboursFirst) {
		this.neighboursFirst = neighboursFirst;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int i) {
		this.count = i;
	}

	public int getMowers() {
		return mowers;
	}

	public void setMowers(int mowers) {
		this.mowers = mowers;
	}

	public Cell getLastCell() {
		if (lastCell == null) {
			throw new RuntimeException("Only call that after #doIt");
		}
		return lastCell;
	}

	public void setLastCell(Cell lastCell) {
		this.lastCell = lastCell;
	}

	public enum Alignment {
		HORIZONTAL, VERTICAL;
	}
}