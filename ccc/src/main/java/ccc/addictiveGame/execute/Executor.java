package ccc.addictiveGame.execute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ccc.addictiveGame.field.Board;
import ccc.addictiveGame.field.Cell;

public class Executor {

	public static String executeFindPositionsOfValues(String input) {

		String[] inputArray = input.split(" ");
		Board board = createBoard(inputArray[0], inputArray[1]);
		List<Integer> valuesToFind = getValuesToFindForLevel_1_Input(inputArray);
		List<Cell> cellsForValues = getCellsForValues(board, valuesToFind);
		return OutputFormatter.getFormattedContent(cellsForValues);
	}

	private static Board createBoard(String rows, String cols) {
		return new Board(Integer.valueOf(rows), Integer.valueOf(cols));
	}

	private static List<Integer> getValuesToFindForLevel_1_Input(String[] inputArray) {
		List<String> valuesFromInput = getValuesFromInput(inputArray);
		return valuesFromInput.stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());
	}

	private static List<String> getValuesFromInput(String[] inputArray) {
		return Arrays.asList(inputArray).subList(3, inputArray.length);
	}

	private static List<Cell> getCellsForValues(Board board, List<Integer> valuesToFind) {
		return valuesToFind.stream().map(x -> board.findCellByValue(x)).collect(Collectors.toList());
	}

	public static String executeFindManhattenDistance(String input) {

		String[] inputArray = input.split(" ");
		Board board = createBoard(inputArray[0], inputArray[1]);

		return null;
	}

	private static List<Integer> getValuesToFindForLevel_2_Input(String[] inputArray) {
		List<String> valuesFromInput = getValuesFromInput(inputArray);

		IntStream.range(0, valuesFromInput.size()).filter(n -> n % 2 == 0).mapToObj(valuesFromInput::get)
				.collect(Collectors.toList());
		return null;
	}
}