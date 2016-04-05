package ccc.harvester;

import java.util.Collection;

import ccc.harvester.field.Cell;

public class OutputFormatter {

	public static String getFormattedContent(Collection<Cell> cells) {

		StringBuilder builder = new StringBuilder();
		for (Cell cell : cells) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}
}
