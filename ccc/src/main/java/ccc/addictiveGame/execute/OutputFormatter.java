package ccc.addictiveGame.execute;

import java.util.Collection;

import ccc.addictiveGame.field.Cell;

public class OutputFormatter {

	public static String getFormattedContent(Collection<Cell> cells) {

		StringBuilder builder = new StringBuilder();
		for (Cell cell : cells) {
			builder.append(cell.getRow()).append(" ").append(cell.getColumn()).append(" ");
		}
		return builder.toString();
	}
}
