package harvester;

import org.testng.annotations.Test;

import harvester.CornField.CornerPosition;

public class HarvesterTest {

	public void testLevel1() {
		// 3 4
		CornField field = new CornField(3, 4);
		System.out.println(new Harvester().harvestSerpentineStyle(field));
		System.out.println(field.toString());

		// 2 5
		field = new CornField(2, 5);
		System.out.println(new Harvester().harvestSerpentineStyle(field));
		System.out.println(field.toString());

		// 5 2
		field = new CornField(5, 2);
		System.out.println(new Harvester().harvestSerpentineStyle(field));
		System.out.println(field.toString());

		// 23 12
		field = new CornField(23, 12);
		System.out.println(new Harvester().harvestSerpentineStyle(field));
		System.out.println(field.toString());
	}

	@Test
	public void testLevel2() {
		// 3 4 1 1
		CornField field = new CornField(3, 4);
		System.out.println(new Harvester().harvestSerpentineStyle(field, 1, 1));
		System.out.println(field.toString());

		// 2 5 2 1
		field = new CornField(2, 5);
		System.out.println(new Harvester().harvestSerpentineStyle(field, 2, 1));
		System.out.println(field.toString());

		// 5 2 5 2
		field = new CornField(5, 2);
		System.out.println(new Harvester().harvestSerpentineStyle(field, 5, 2));
		System.out.println(field.toString());

		// 23 12 1 12
		field = new CornField(23, 12);
		System.out.println(new Harvester().harvestSerpentineStyle(field, 1, 12));
		System.out.println(field.toString());
	}
}

class Harvester {

	public String harvestSerpentineStyle(CornField field) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < field.getXSize(); i++) {
			if (i % 2 == 0) {
				builder.append(harvestRowAscending(field.getRow(i)));
			} else {
				builder.append(harvestRowDescending(field.getRow(i)));
			}
		}
		return builder.toString();
	}

	private String harvestSerpentineStyleInverted(CornField field) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < field.getXSize(); i++) {
			if (i % 2 == 1) {
				builder.append(harvestRowAscending(field.getRow(i)));
			} else {
				builder.append(harvestRowDescending(field.getRow(i)));
			}
		}
		return builder.toString();
	}

	private String harvestSerpentineStyleBottomUp(CornField field) {

		StringBuilder builder = new StringBuilder();
		for (int i = field.getXSize() - 1; i >= 0; i--) {
			if (i % 2 == 1) {
				builder.append(harvestRowAscending(field.getRow(i)));
			} else {
				builder.append(harvestRowDescending(field.getRow(i)));
			}
		}
		return builder.toString();
	}

	private String harvestSerpentineStyleBottomUpInverted(CornField field) {

		StringBuilder builder = new StringBuilder();
		for (int i = field.getXSize() - 1; i >= 0; i--) {
			if (i % 2 == 1) {
				builder.append(harvestRowAscending(field.getRow(i)));
			} else {
				builder.append(harvestRowDescending(field.getRow(i)));
			}
		}
		return builder.toString();
	}

	private String harvestRowAscending(int[] row) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < row.length; i++) {
			builder.append(row[i]).append(" ");
		}
		return builder.toString();
	}

	private String harvestRowDescending(int[] row) {

		StringBuilder builder = new StringBuilder();
		for (int i = row.length - 1; i >= 0; i--) {
			builder.append(row[i]).append(" ");
		}
		return builder.toString();
	}

	public String harvestSerpentineStyle(CornField field, int startX, int startY) {

		CornerPosition whichCorner = field.whichCorner(startX, startY);
		System.out.println(whichCorner + " " + startX + " " + startY);

		switch (whichCorner) {
		case TOP_LEFT:
			return harvestSerpentineStyle(field);
		case TOP_RIGHT:
			return harvestSerpentineStyleInverted(field);
		case BOTTOM_LEFT:
			return harvestSerpentineStyleBottomUp(field);
		case BOTTOM_RIGHT:
			return harvestSerpentineStyleBottomUpInverted(field);
		case NOT_A_CORNER:
			throw new RuntimeException("NOT A CORNER");
		default:
			throw new RuntimeException("NOT IMPLEMENTED");
		}
	}
}

class CornField {

	enum CornerPosition {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, NOT_A_CORNER;
	}

	private int rows;
	private int columns;

	private int[][] matrix;

	public CornField(int x, int y) {
		this.rows = x;
		this.columns = y;
		initField(x, y);
	}

	private void initField(int x, int y) {
		int cellNumber = 1;

		matrix = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				matrix[i][j] = cellNumber++;
			}
		}
	}

	public int[] getRow(int i) {
		return getMatrix()[i];
	}

	public int getCellContent(int x, int y) {
		return getMatrix()[x][y];
	}

	public CornerPosition whichCorner(int x, int y) {

		if (x == 1 && y == 1) {
			return CornerPosition.TOP_LEFT;
		} else if (x == rows && y == 1) {
			return CornerPosition.BOTTOM_LEFT;
		} else if (x == 1 && y == columns) {
			return CornerPosition.TOP_RIGHT;
		} else if (x == rows && y == columns) {
			return CornerPosition.BOTTOM_RIGHT;
		}
		return CornerPosition.NOT_A_CORNER;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				builder.append(String.format("%03d", getCellContent(i, j))).append(",");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public int getXSize() {
		return rows;
	}

	public void setXSize(int x) {
		this.rows = x;
	}

	public int getYSize() {
		return columns;
	}

	public void setYSize(int y) {
		this.columns = y;
	}
}