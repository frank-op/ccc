package ccc.privateOnline2016.execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ccc.privateOnline2016.board.Board;

public class Executor {

	public static String executeLevel1(String input) {

		String[] split = input.split(" ");
		int numberOfColumns = Integer.valueOf(split[0]);
		int blockLength = Integer.valueOf(split[1]);

		// initialized with "?"
		Board board = new Board(1, numberOfColumns);

		if (blockLength * 2 <= numberOfColumns) {
			// do nothing
		} else {

			int unknownFields = numberOfColumns - blockLength;
			IntStream.range(unknownFields, numberOfColumns - unknownFields)
					.forEach(x -> board.getCell(1, x + 1).setCellContent("1"));
		}
		
		return OutputFormatter.getFormattedContent(board);
	}

	public static List<String> readInAndGetStringList(String fileName) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}