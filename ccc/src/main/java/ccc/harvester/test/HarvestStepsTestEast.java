package ccc.harvester.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.EastWithMowersNorthernNeighbours;
import ccc.harvester.steps.EastWithMowersSouthernNeighbours;
import ccc.harvester.steps.EastWithoutMowing;

public class HarvestStepsTestEast {

	@Test
	public void testGoEastWithMowersNorthernNeighbours() {

		CornField field = new CornField(5, 5);

		EastWithMowersNorthernNeighbours east = new EastWithMowersNorthernNeighbours(2, false, null);

		List<Cell> cells = east.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 2 3 4 5 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 5);

		cells = east.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 1 7 2 8 3 9 4 10 5 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 5);

		cells = east.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 16 22 17 23 18 24 19 25 20 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 20);
	}

	@Test
	public void testGoEastWithMowersNorthernNeighboursLimited() {

		CornField field = new CornField(5, 5);

		EastWithMowersNorthernNeighbours east = new EastWithMowersNorthernNeighbours(2, false, new int[] { 3 });

		List<Cell> cells = east.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 2 3 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 3);

		cells = east.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 1 7 2 8 3 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 3);

		cells = east.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 16 22 17 23 18 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 18);
	}

	@Test
	public void testGoEastWithMowersSouthernNeighbours() {

		CornField field = new CornField(5, 5);

		EastWithMowersSouthernNeighbours east = new EastWithMowersSouthernNeighbours(2, false, null);

		List<Cell> cells = east.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 6 2 7 3 8 4 9 5 10 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 10);

		cells = east.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 11 7 12 8 13 9 14 10 15 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 15);

		cells = east.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 22 23 24 25 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 25);
	}

	@Test
	public void testGoEastWithMowersSouthernNeighboursLimited() {

		CornField field = new CornField(5, 5);

		EastWithMowersSouthernNeighbours east = new EastWithMowersSouthernNeighbours(2, false, new int[] { 2 });

		List<Cell> cells = east.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 6 2 7 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 7);

		cells = east.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 11 7 12 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 12);

		cells = east.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 22 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 22);
	}

	@Test
	public void testGoEastWithoutMowing() {

		CornField field = new CornField(5, 5);

		EastWithoutMowing east = new EastWithoutMowing(new int[] { 3 });

		List<Cell> cells = east.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 4);

		cells = east.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 9);

		cells = east.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(east.getLastCell().getContent(), 24);
	}
}