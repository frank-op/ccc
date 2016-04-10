package ccc.harvester.test;

import java.util.List;

import org.testng.annotations.Test;

import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class FieldTester {

	@Test
	public void testGetCellsTillTheEnd() {

		CornField cornField = new CornField(5, 5);
		List<Cell> cellsNorthFromHere = cornField.cellsNorthFromHere(5, 1);
		System.out.println("NORTH " + cellsNorthFromHere);
		System.out.println(cornField.getContentNorthFromHere(5, 1));

		List<Cell> cellsEastFromHere = cornField.cellsEastFromHere(1, 1);
		System.out.println("EAST  " + cellsEastFromHere);
		System.out.println(cornField.getContentEastFromHere(1, 1));

		List<Cell> cellsSouthFromHere = cornField.cellsSouthFromHere(1, 5);
		System.out.println("SOUTH " + cellsSouthFromHere);
		System.out.println(cornField.getContentSouthFromHere(1, 5));

		List<Cell> cellsWestFromHere = cornField.cellsWestFromHere(1, 5);
		System.out.println("WEST  " + cellsWestFromHere);
		System.out.println(cornField.getContentWestFromHere(1, 5));
	}

	@Test
	public void testGetCellsLimited() {

		CornField cornField = new CornField(5, 5);
		List<Cell> cellsNorthFromHere = cornField.cellsNorthFromHere(0, 1, 5, 1);
		System.out.println("NORTH " + cellsNorthFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsNorthFromHere));

		List<Cell> cellsEastFromHere = cornField.cellsEastFromHere(0, 1, 1, 1);
		System.out.println("EAST  " + cellsEastFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsEastFromHere));

		List<Cell> cellsSouthFromHere = cornField.cellsSouthFromHere(0, 1, 1, 5);
		System.out.println("SOUTH " + cellsSouthFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsSouthFromHere));

		List<Cell> cellsWestFromHere = cornField.cellsWestFromHere(0, 1, 1, 5);
		System.out.println("WEST  " + cellsWestFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsWestFromHere));
	}
}