package ccc.harvester.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.exec.OutputFormatter;
import ccc.harvester.field.Cell;
import ccc.harvester.field.CornField;
import ccc.harvester.steps.SouthWithMowersEasternNeighbours;
import ccc.harvester.steps.SouthWithMowersWesternNeighbours;
import ccc.harvester.steps.SouthWithoutMowing;

public class HarvestStepsTestSouth {

	@Test
	public void testGoSouthWithMowersEasternNeighbours() {

		CornField field = new CornField(5, 5);

		SouthWithMowersEasternNeighbours south = new SouthWithMowersEasternNeighbours(2, false, null);

		List<Cell> cells = south.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 2 6 7 11 12 16 17 21 22 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 22);

		cells = south.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 7 11 12 16 17 21 22 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 22);

		cells = south.doIt(field, field.getCell(1, 4));
		Assert.assertEquals("4 5 9 10 14 15 19 20 24 25 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 25);
	}

	@Test
	public void testGoSouthWithMowersEasternNeighboursLimited() {

		CornField field = new CornField(5, 5);

		SouthWithMowersEasternNeighbours south = new SouthWithMowersEasternNeighbours(2, false, new int[] { 3 });

		List<Cell> cells = south.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 2 6 7 11 12 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 12);

		cells = south.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 7 11 12 16 17 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 17);

		cells = south.doIt(field, field.getCell(1, 4));
		Assert.assertEquals("4 5 9 10 14 15 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 15);
	}

	@Test
	public void testGoSouthWithMowersWesternNeighbours() {

		CornField field = new CornField(5, 5);

		SouthWithMowersWesternNeighbours south = new SouthWithMowersWesternNeighbours(2, false, null);

		List<Cell> cells = south.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 6 11 16 21 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 21);

		cells = south.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 11 16 21 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 21);

		cells = south.doIt(field, field.getCell(1, 5));
		Assert.assertEquals("5 4 10 9 15 14 20 19 25 24 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 24);
	}

	@Test
	public void testGoSouthWithMowersWesternNeighboursLimited() {

		CornField field = new CornField(5, 5);

		SouthWithMowersWesternNeighbours south = new SouthWithMowersWesternNeighbours(2, false, new int[] { 2 });

		List<Cell> cells = south.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("1 6 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 6);

		cells = south.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("6 11 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 11);

		cells = south.doIt(field, field.getCell(1, 5));
		Assert.assertEquals("5 4 10 9 ", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 9);
	}

	@Test
	public void testGoSouthWithoutMowing() {

		CornField field = new CornField(5, 5);

		SouthWithoutMowing south = new SouthWithoutMowing(new int[] { 3 });

		List<Cell> cells = south.doIt(field, field.getCell(1, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 16);

		cells = south.doIt(field, field.getCell(2, 1));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 21);

		cells = south.doIt(field, field.getCell(1, 5));
		Assert.assertEquals("", OutputFormatter.getFormattedContent(cells));
		Assert.assertEquals(south.getLastCell().getContent(), 20);
	}
}