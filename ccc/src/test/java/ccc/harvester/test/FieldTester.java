package ccc.harvester.test;

import static ccc.harvester.field.CornfieldCalculator.cellsEastFromHere;
import static ccc.harvester.field.CornfieldCalculator.cellsNorthFromHere;
import static ccc.harvester.field.CornfieldCalculator.cellsSouthFromHere;
import static ccc.harvester.field.CornfieldCalculator.cellsWestFromHere;

import java.util.List;

import org.testng.annotations.Test;

import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;

public class FieldTester {

	@Test
	public void testGetCellsTillTheEnd() {

		CornField cornField = new CornField(5, 5);
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(cornField, 5, 1);
		System.out.println("NORTH " + cellsNorthFromHere);

		List<Cell> cellsEastFromHere = cellsEastFromHere(cornField, 1, 1);
		System.out.println("EAST  " + cellsEastFromHere);

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(cornField, 1, 5);
		System.out.println("SOUTH " + cellsSouthFromHere);

		List<Cell> cellsWestFromHere = cellsWestFromHere(cornField, 1, 5);
		System.out.println("WEST  " + cellsWestFromHere);
	}

	@Test
	public void testGetCellsLimited() {

		CornField cornField = new CornField(5, 5);
		List<Cell> cellsNorthFromHere = cellsNorthFromHere(cornField, 0, 1, 5, 1);
		System.out.println("NORTH " + cellsNorthFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsNorthFromHere));

		List<Cell> cellsEastFromHere = cellsEastFromHere(cornField, 0, 1, 1, 1);
		System.out.println("EAST  " + cellsEastFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsEastFromHere));

		List<Cell> cellsSouthFromHere = cellsSouthFromHere(cornField, 0, 1, 1, 5);
		System.out.println("SOUTH " + cellsSouthFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsSouthFromHere));

		List<Cell> cellsWestFromHere = cellsWestFromHere(cornField, 0, 1, 1, 5);
		System.out.println("WEST  " + cellsWestFromHere);
		System.out.println(OutputFormatter.getFormattedContent(cellsWestFromHere));
	}
}