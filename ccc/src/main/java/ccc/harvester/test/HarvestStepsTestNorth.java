package ccc.harvester.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.NorthWithMowersEasternNeighbours;
import ccc.harvester.steps.NorthWithMowersWesternNeighbours;
import ccc.harvester.steps.NorthWithoutMowing;

public class HarvestStepsTestNorth {

	@Test
	public void testGoNorthWithMowersEasternNeighbours() {

		CornField field = new CornField(5, 5);

		NorthWithMowersEasternNeighbours north = new NorthWithMowersEasternNeighbours(2, false, null);

		List<Cell> cells = north.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 22 16 17 11 12 6 7 1 2 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 2);

		cells = north.doIt(field, field.getCell(4, 1));
		Assert.assertEquals("16 17 11 12 6 7 1 2 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 2);

		cells = north.doIt(field, field.getCell(5, 4));
		Assert.assertEquals("24 25 19 20 14 15 9 10 4 5 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 5);
	}

	@Test
	public void testGoNorthWithMowersEasternNeighboursLimited() {

		CornField field = new CornField(5, 5);

		NorthWithMowersEasternNeighbours north = new NorthWithMowersEasternNeighbours(2, false, new int[] { 3 });

		List<Cell> cells = north.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 22 16 17 11 12 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 12);

		cells = north.doIt(field, field.getCell(4, 1));
		Assert.assertEquals("16 17 11 12 6 7 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 7);

		cells = north.doIt(field, field.getCell(5, 4));
		Assert.assertEquals("24 25 19 20 14 15 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 15);
	}

	@Test
	public void testGoNorthWithMowersWesternNeighbours() {

		CornField field = new CornField(5, 5);

		NorthWithMowersWesternNeighbours north = new NorthWithMowersWesternNeighbours(2, false, null);

		List<Cell> cells = north.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 16 11 6 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 1);

		cells = north.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 1);

		cells = north.doIt(field, field.getCell(5, 5));
		Assert.assertEquals("25 24 20 19 15 14 10 9 5 4 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 4);
	}

	@Test
	public void testGoNorthWithMowersWesternNeighboursLimited() {

		CornField field = new CornField(5, 5);

		NorthWithMowersWesternNeighbours north = new NorthWithMowersWesternNeighbours(2, false, new int[] { 2 });

		List<Cell> cells = north.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("21 16 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 16);

		cells = north.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 1 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 1);

		cells = north.doIt(field, field.getCell(5, 5));
		Assert.assertEquals("25 24 20 19 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 19);
	}

	@Test
	public void testGoNorthWithoutMowing() {

		CornField field = new CornField(5, 5);

		NorthWithoutMowing north = new NorthWithoutMowing(new int[] { 3 });

		List<Cell> cells = north.doIt(field, field.getCell(5, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 6);

		cells = north.doIt(field, field.getCell(4, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 1);

		cells = north.doIt(field, field.getCell(5, 5));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(north.getLastCell().getContent(), 10);
	}
}