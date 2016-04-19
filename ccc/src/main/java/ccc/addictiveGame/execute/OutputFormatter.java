package ccc.addictiveGame.execute;

import java.util.Collection;
import java.util.List;

import ccc.addictiveGame.field.Cell;

public class OutputFormatter {

	public static String getFormattedContent(Collection<Cell> cells) {

		StringBuilder builder = new StringBuilder();
		for (Cell cell : cells) {
			builder.append(cell.getRow()).append(" ").append(cell.getColumn()).append(" ");
		}
		return builder.toString();
	}

	public static String formatManhattenDistances(List<Integer> manhattenDistances) {

		StringBuilder builder = new StringBuilder();
		for (Integer distance : manhattenDistances) {
			builder.append(distance).append(" ");
		}
		return builder.toString();
	}
}
