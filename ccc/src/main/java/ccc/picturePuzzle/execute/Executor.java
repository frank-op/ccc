package ccc.picturePuzzle.execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ccc.picturePuzzle.board.Block;
import ccc.picturePuzzle.board.Board;
import ccc.picturePuzzle.board.Cell;

public class Executor {

	public static List<String> readInAndGetStringList(String fileName) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static String executeLevel1(String input) {

		String[] split = input.split(" ");
		int numberOfColumns = Integer.valueOf(split[0]);
		int blockLength = Integer.valueOf(split[1]);

		// initialized with "?"
		Board board = new Board(1, numberOfColumns, "?");

		if (blockLength * 2 <= numberOfColumns) {
			// do nothing
		} else {

			int unknownFields = numberOfColumns - blockLength;
			IntStream.range(unknownFields, numberOfColumns - unknownFields)
					.forEach(x -> board.getCell(1, x + 1).setCellContent("1"));
		}

		return OutputFormatter.getFormattedContent(board);
	}

	public static String executeLevel2(String input) {

		String[] splitInput = input.split(" ");
		List<String> inputAsList = new ArrayList<>(Arrays.asList(splitInput));

		int numberOfColumns = Integer.valueOf(inputAsList.remove(0));
		int numberOfBlocks = Integer.valueOf(inputAsList.remove(0));

		List<Block> blocks = new ArrayList<>();
		for (String inputString : inputAsList) {
			blocks.add(new Block(Integer.valueOf(inputString), "1"));
		}

		Board board = new Board(1, numberOfColumns, "");

		if (numberOfBlocks == 0) {
			IntStream.range(0, board.getColumns()).forEach(x -> board.getCell(1, x + 1).setCellContent("0"));
		} else {
			for (int currentBlock = 0; currentBlock < blocks.size(); currentBlock++) {

				Block block = blocks.get(currentBlock);

				int rangeStart = getStartPoint(blocks, currentBlock);
				int rangeEnd = getEndPoint(numberOfColumns, blocks, currentBlock);

				for (int blockStart = rangeStart; blockStart + block.getLength() - 1 <= rangeEnd; blockStart++) {

					for (int blockElement = rangeStart; blockElement <= rangeEnd; blockElement++) {

						Cell cell = board.getCell(1, blockElement);
						String cellContent = cell.getCellContent();
						String colorToSet = "0";

						if (blockElement >= blockStart || blockElement <= blockStart + block.getLength() - 1) {
							colorToSet = block.getColor();
						}

						if ("".equals(cellContent)) {
							cell.setCellContent(colorToSet);
						} else if (!cellContent.equals(colorToSet)) {
							cell.setCellContent("?");
						}
					}
				}
			}
		}
		return OutputFormatter.getFormattedContent(board);
	}

	private static int getStartPoint(List<Block> blocks, int currentBlock) {
		int startPoint = 1;

		for (int i = 0; i < currentBlock; i++) {
			startPoint += blocks.get(i).getLength() + 1;
		}
		return startPoint;
	}

	private static int getEndPoint(int numberOfColumns, List<Block> blocks, int currentBlock) {
		int endPoint = numberOfColumns;

		for (int i = currentBlock + 1; i < blocks.size(); i++) {
			endPoint -= blocks.get(i).getLength() + 1;
		}
		return endPoint;
	}
}