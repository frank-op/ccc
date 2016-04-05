package ccc.harvester.test;

import org.testng.annotations.Test;

import ccc.harvester.Direction;
import ccc.harvester.HarvestingCalculator;
import ccc.harvester.HarvestingCalculator.Style;
import ccc.harvester.field.CornField;
import junit.framework.Assert;

public class Level5Tester_TwoMowers {

	@Test
	public void test() {

		CornField field = null;

		// 5 4 1 1 O S 2
		field = new CornField(5, 4);
		System.out.println(field);
		String harvestWith2Mowers = HarvestingCalculator.harvestWith2Mowers(field, Direction.EAST, 1, 1, Style.S);
		System.out.println(harvestWith2Mowers);
		Assert.assertEquals("1 5 2 6 3 7 4 8 16 12 15 11 14 10 13 9 17 18 19 20 ", harvestWith2Mowers);

		System.out.println();
		System.out.println();

		// 5 4 4 1 O Z 2
//		field = new CornField(5, 4);
//		System.out.println(field);
//		System.out.println(HarvestingCalculator.harvestWith2Mowers(field, Direction.EAST, 1, 1, Style.C));
//
//		System.out.println();
//		System.out.println();

		// 10 10 10 10 W S 1
		field = new CornField(10, 10);
		System.out.println(field);
		System.out.println(
				HarvestingCalculator.harvestSerpentinesStartFromGivenCornerColumns(field, Direction.WEST, 10, 10));

		System.out.println();
		System.out.println();

		// 10 10 10 10 W S 2
		field = new CornField(10, 10);
		System.out.println(field);
		harvestWith2Mowers = HarvestingCalculator.harvestWith2Mowers(field, Direction.WEST, 10, 10, Style.S);
		System.out.println(harvestWith2Mowers);
		Assert.assertEquals(
				"100 90 99 89 98 88 97 87 96 86 95 85 94 84 93 83 92 82 91 81 61 71 62 72 63 73 64 74 65 75 66 76 67 77 68 78 69 79 70 80 60 50 59 49 58 48 57 47 56 46 55 45 54 44 53 43 52 42 51 41 21 31 22 32 23 33 24 34 25 35 26 36 27 37 28 38 29 39 30 40 20 10 19 9 18 8 17 7 16 6 15 5 14 4 13 3 12 2 11 1 ",
				harvestWith2Mowers);

		System.out.println();
		System.out.println();

		// 17 9 17 1 N Z 2
//		field = new CornField(17, 9);
//		System.out.println(field);
//		System.out.println(HarvestingCalculator.harvestWith2Mowers(field, Direction.NORTH, 17, 1, Style.C));
//
//		System.out.println();
//		System.out.println();
	}
}