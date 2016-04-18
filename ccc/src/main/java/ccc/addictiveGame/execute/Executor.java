package ccc.addictiveGame.execute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ccc.addictiveGame.field.Board;
import ccc.addictiveGame.field.Cell;

public class Executor {

	public static String executeFindPositionsOfValues(String input) {

		String[] inputArray = input.split(" ");
		Board board = createBoard(inputArray[0], inputArray[1]);
		List<Integer> valuesToFind = getValuesToFind(inputArray);
		List<Cell> cellsForValues = getCellsForValues(board, valuesToFind);
		return OutputFormatter.getFormattedContent(cellsForValues);
	}

	private static Board createBoard(String rows, String cols) {
		return new Board(Integer.valueOf(rows), Integer.valueOf(cols));
	}

	private static List<Integer> getValuesToFind(String[] inputArray) {
		return Arrays.asList(inputArray).subList(3, inputArray.length).stream().map(x -> Integer.valueOf(x))
				.collect(Collectors.toList());
	}

	private static List<Cell> getCellsForValues(Board board, List<Integer> valuesToFind) {
		return valuesToFind.stream().map(x -> board.findCellByValue(x)).collect(Collectors.toList());
	}
}