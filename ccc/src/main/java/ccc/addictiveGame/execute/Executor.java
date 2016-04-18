package ccc.addictiveGame.execute;

import java.util.ArrayList;
import java.util.List;

import ccc.addictiveGame.field.Board;
import ccc.addictiveGame.field.Cell;

public class Executor {

	public static String executeFindPositionsOfValues(String input) {

		String[] inputArray = input.split(" ");
		Board board = createBoard(inputArray[0], inputArray[1]);
		List<Integer> valuesToFind = getValuesToFind(input, inputArray);
		List<Cell> cellsForValues = getCellsForValues(board, valuesToFind);
		return OutputFormatter.getFormattedContent(cellsForValues);
	}

	private static Board createBoard(String rows, String cols) {
		return new Board(Integer.valueOf(rows), Integer.valueOf(cols));
	}

	private static List<Integer> getValuesToFind(String input, String[] inputArray) {
		List<Integer> valuesToFind = new ArrayList<>();

		for (int i = 3; i < inputArray.length; i++) {
			String string = input.split(" ")[i];
			valuesToFind.add(Integer.valueOf(string));
		}
		return valuesToFind;
	}

	private static List<Cell> getCellsForValues(Board board, List<Integer> valuesToFind) {
		List<Cell> cells = new ArrayList<>();
		for (Integer integer : valuesToFind) {
			cells.add(board.findCellByValue(integer));
		}
		return cells;
	}
}