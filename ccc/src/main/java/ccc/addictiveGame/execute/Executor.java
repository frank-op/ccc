package ccc.addictiveGame.execute;

import java.util.Arrays;
import java.util.Comparator;
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

		List<ValueColorTuple> valueColorTuples = getValueColorTupleForLevel_2_Input(inputArray);

		List<Cell> cellsWithColorsSet = valueColorTuples.stream().map(tuple -> findCellAndSetColor(board, tuple))
				.sorted(new Comparator<Cell>() {

					@Override
					public int compare(Cell cell1, Cell cell2) {

						return cell1.getColor() - cell2.getColor();
					}
				}).collect(Collectors.toList());

		List<Integer> manhattenDistances = IntStream
				.range(0, cellsWithColorsSet.size()).filter(n -> n % 2 == 0).mapToObj(x -> board
						.getManhattenDistanceForCells(cellsWithColorsSet.get(x), cellsWithColorsSet.get(x + 1)))
				.collect(Collectors.toList());

		return OutputFormatter.formatManhattenDistances(manhattenDistances);
	}

	public static Cell findCellAndSetColor(Board board, ValueColorTuple tuple) {
		Cell cell = board.findCellByValue(tuple.value);
		cell.setColor(tuple.color);
		return cell;
	}

	private static List<ValueColorTuple> getValueColorTupleForLevel_2_Input(String[] inputArray) {
		List<String> valuesFromInput = getValuesFromInput(inputArray);

		return IntStream.range(0, valuesFromInput.size()).filter(n -> n % 2 == 0)
				.mapToObj(x -> new ValueColorTuple(Integer.valueOf(valuesFromInput.get(x)),
						Integer.valueOf(valuesFromInput.get(x + 1))))
				.collect(Collectors.toList());
	}

	private static class ValueColorTuple {
		private int value;
		private int color;

		private ValueColorTuple(int value, int color) {
			this.value = value;
			this.color = color;
		}
	}
}