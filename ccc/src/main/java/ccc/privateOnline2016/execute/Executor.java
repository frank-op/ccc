package ccc.privateOnline2016.execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ccc.privateOnline2016.board.Board;
import ccc.privateOnline2016.board.Cell;

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
		int id = 0;
		for (String inputString : inputAsList) {
			blocks.add(new Block(id, Integer.valueOf(inputString)));
			id++;
		}

		Board board = new Board(1, numberOfColumns, "0");

		if (numberOfBlocks == 0) {
			IntStream.range(0, board.getColumns()).forEach(x -> board.getCell(1, x + 1).setCellContent("0"));
		} else {

			for (Block block : blocks) {

				int currentBlock = block.getId();
				int startPoint = 1;

				for (int i = 0; i < currentBlock; i++) {
					startPoint += blocks.get(i).getLength() + 1;
				}

				int endPoint = numberOfColumns;

				for (int i = currentBlock + 1; i < blocks.size(); i++) {
					endPoint -= blocks.get(i).getLength() + 1;
				}

				List<List<Content>> possibleScenariosForBlock = getPossibleScenariosForBlock(board, block.getLength(),
						startPoint, endPoint);

				List<Content> scenarioForBlock = getScenarioForBlock(possibleScenariosForBlock);

				for (int i = startPoint; i <= endPoint; i++) {
					Cell cell = board.getCell(1, i);
					cell.setCellContent(scenarioForBlock.get(i - startPoint).getContent());
				}
			}

		}
		return OutputFormatter.getFormattedContent(board);
	}

	private static List<Content> getScenarioForBlock(List<List<Content>> possibleScenariosForBlock) {

		int size = possibleScenariosForBlock.get(0).size();
		List<Content> content = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			content.add(getContentForIndex(i, possibleScenariosForBlock));
		}

		return content;
	}

	private static Content getContentForIndex(int i, List<List<Content>> possibleScenariosForBlock) {

		List<Content> contentsForIndex = new ArrayList<>();

		for (List<Content> list : possibleScenariosForBlock) {
			Content content = list.get(i);
			contentsForIndex.add(content);
		}

		Boolean isBlack = doesContentFitColor(contentsForIndex, "1");

		if (isBlack) {
			return new Content("1");
		}

		Boolean isWhite = doesContentFitColor(contentsForIndex, "0");

		if (isWhite) {
			return new Content("0");
		}

		return new Content("?");
	}

	private static Boolean doesContentFitColor(List<Content> contentsForIndex, String contentText) {

		boolean isBlack = true;
		for (Content content : contentsForIndex) {
			isBlack = isBlack && content.getContent().equals(contentText);
		}
		return isBlack;
	}

	private static List<List<Content>> getPossibleScenariosForBlock(Board board, int currentBlockLength, int startPoint,
			int endPoint) {

		int startIndex = startPoint - 1;

		List<List<Content>> possibleScenarios = new ArrayList<List<Content>>();

		for (int i = startIndex; i + currentBlockLength <= endPoint; i++) {

			List<Content> currentScenario = getEmptyScenario(endPoint - startPoint);

			int scenarioEnd = i + currentBlockLength;
			for (int j = i - startIndex; j < scenarioEnd - startIndex; j++) {
				currentScenario.get(j).setContent("1");
			}

			possibleScenarios.add(currentScenario);
		}

		return possibleScenarios;
	}

	private static ArrayList<Content> getEmptyScenario(int length) {
		ArrayList<Content> emptyScenario = new ArrayList<>();

		for (int i = 0; i <= length; i++) {
			emptyScenario.add(new Content("0"));
		}

		return emptyScenario;
	}

	private static class Block {
		int id, length;

		public Block(int id, int length) {
			this.id = id;
			this.length = length;
		}

		public int getId() {
			return id;
		}

		public int getLength() {
			return length;
		}

		@Override
		public String toString() {
			return String.valueOf(length);
		}
	}

	private static class Content {
		private String content;

		public Content(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}