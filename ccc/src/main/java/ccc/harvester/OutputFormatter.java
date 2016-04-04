package ccc.harvester;

import java.util.Collection;

public class OutputFormatter {

	public static String getFormattedContent(Collection<Cell> cells) {

		StringBuilder builder = new StringBuilder();
		for (Cell cell : cells) {
			builder.append(cell.getContent()).append(" ");
		}
		return builder.toString();
	}
}
