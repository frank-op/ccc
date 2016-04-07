package ccc.harvester.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.West;
import ccc.harvester.steps.WestWithMowersNorthernNeighbours;
import ccc.harvester.steps.WestWithMowersSouthernNeighbours;
import ccc.harvester.steps.WestWithoutMowing;

public class HarvestStepsTestWest {

	@Test
	public void testGoWest() {

		CornField field = new CornField(5, 5);

		West west = new West(field, null);
		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 4 3 2 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 1);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("10 9 8 7 6 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 6);
	}

	@Test
	public void testGoWestLimited() {

		CornField field = new CornField(5, 5);

		West west = new West(field, new int[] { 4 });
		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 4 3 2 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 2);
	}

	@Test
	public void testGoWestWithMowersNorthernNeighbours() {

		CornField field = new CornField(5, 5);

		WestWithMowersNorthernNeighbours west = new WestWithMowersNorthernNeighbours(field, 2, false, null);

		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 4 3 2 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 1);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("10 5 9 4 8 3 7 2 6 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 1);

		cells = west.doIt(field.getCell(5, 5));
		Assert.assertEquals("25 20 24 19 23 18 22 17 21 16 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 16);
	}

	@Test
	public void testGoWestWithMowersNorthernNeighboursLimited() {

		CornField field = new CornField(5, 5);

		WestWithMowersNorthernNeighbours west = new WestWithMowersNorthernNeighbours(field, 2, false, new int[] { 3 });

		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 4 3 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 3);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("10 5 9 4 8 3 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 3);

		cells = west.doIt(field.getCell(5, 5));
		Assert.assertEquals("25 20 24 19 23 18 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 18);
	}

	@Test
	public void testGoWestWithMowersSouthernNeighbours() {

		CornField field = new CornField(5, 5);

		WestWithMowersSouthernNeighbours west = new WestWithMowersSouthernNeighbours(field, 2, false, null);

		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 10 4 9 3 8 2 7 1 6 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 6);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("10 15 9 14 8 13 7 12 6 11 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 11);

		cells = west.doIt(field.getCell(5, 5));
		Assert.assertEquals("25 24 23 22 21 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 21);
	}

	@Test
	public void testGoWestWithMowersSouthernNeighboursLimited() {

		CornField field = new CornField(5, 5);

		WestWithMowersSouthernNeighbours west = new WestWithMowersSouthernNeighbours(field, 2, false, new int[] { 2 });

		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("5 10 4 9 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 9);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("10 15 9 14 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 14);

		cells = west.doIt(field.getCell(5, 5));
		Assert.assertEquals("25 24 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 24);
	}

	@Test
	public void testGoWestWithoutMowing() {

		CornField field = new CornField(5, 5);

		WestWithoutMowing west = new WestWithoutMowing(field, new int[] { 3 });

		List<Cell> cells = west.doIt(field.getCell(1, 5));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 2);

		cells = west.doIt(field.getCell(2, 5));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 7);

		cells = west.doIt(field.getCell(5, 5));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(west.getLastCell().getContent(), 22);
	}
}