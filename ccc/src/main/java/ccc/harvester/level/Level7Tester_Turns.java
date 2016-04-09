package ccc.harvester.level;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ccc.harvester.HarvestingScenarioExecutor;
import ccc.harvester.HarvestingScenarioExecutor.Style;
import ccc.harvester.HarvestingScenarioExecutor.Direction;
import ccc.harvester.field.CornField;
import ccc.harvester.scenarios.Scenario;

public class Level7Tester_Turns {

	private boolean fixEmptyCells;

	@BeforeClass
	public void setup() {
		fixEmptyCells = Scenario.isFixEmptyCells();
		Scenario.setFixEmptyCells(true);
	}

	@AfterClass
	public void tearDown() {
		Scenario.setFixEmptyCells(fixEmptyCells);
	}

	@Test
	public void test1() {
		// 5 4 1 1 O S 3
		CornField field = new CornField(5, 4);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.EAST,
				1, 1, 3, Style.S);
		System.out.println(harvestWithMultipleMowers);
		Assert.assertEquals(harvestWithMultipleMowers, "1 5 9 2 6 10 3 7 11 4 8 12 0 20 16 0 19 15 0 18 14 0 17 13 ");

		System.out.println();
		System.out.println();
	}

	// @Test
	public void test2() {
		// 5 4 3 1 O Z 3
		CornField field = new CornField(5, 4);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.EAST,
				3, 1, 3, Style.C);
		System.out.println(harvestWithMultipleMowers);
		// Assert.assertEquals("",harvestWithMultipleMowers);
		System.out.println();
		System.out.println();
	}

	@Test
	public void test3() {
		// 10 10 10 10 W S 1
		CornField field = new CornField(10, 10);
		System.out.println(field);
		String harvestSerpentinesStartFromGivenCornerColumns = HarvestingScenarioExecutor
				.harvestSerpentinesStartFromGivenCornerColumns(field, Direction.WEST, 10, 10);
		System.out.println(harvestSerpentinesStartFromGivenCornerColumns);
		Assert.assertEquals(
				"100 99 98 97 96 95 94 93 92 91 81 82 83 84 85 86 87 88 89 90 80 79 "
						+ "78 77 76 75 74 73 72 71 61 62 63 64 65 66 67 68 69 70 60 59 58 57 56 "
						+ "55 54 53 52 51 41 42 43 44 45 46 47 48 49 50 40 39 38 37 36 35 34 33 "
						+ "32 31 21 22 23 24 25 26 27 28 29 30 20 19 18 17 16 15 14 13 12 11 1 2 3 4 5 6 7 8 9 10 ",
				harvestSerpentinesStartFromGivenCornerColumns);

		System.out.println();
		System.out.println();
	}

	@Test
	public void test4() {
		// 10 10 10 10 W S 3
		CornField field = new CornField(10, 10);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.WEST,
				10, 10, 3, Style.S);
		System.out.println(harvestWithMultipleMowers);
		Assert.assertEquals(
				"100 90 80 99 89 79 98 88 78 97 87 77 96 86 76 95 85 75 94 84 74 93 83 73 92 82 72 91 81 71 41 51 61 42 52 62 43 53 63 44 54 64 45 55 65 46 56 66 47 57 67 48 58 68 49 59 69 50 60 70 40 30 20 39 29 19 38 28 18 37 27 17 36 26 16 35 25 15 34 24 14 33 23 13 32 22 12 31 21 11 0 0 1 0 0 2 0 0 3 0 0 4 0 0 5 0 0 6 0 0 7 0 0 8 0 0 9 0 0 10 ",
				harvestWithMultipleMowers);

		System.out.println();
		System.out.println();
	}

	// @Test
	public void test5() {
		// 17 9 17 1 N Z 3
		CornField field = new CornField(17, 9);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.NORTH,
				17, 1, 3, Style.C);
		System.out.println(harvestWithMultipleMowers);
		// Assert.assertEquals("",harvestWithMultipleMowers);
		System.out.println();
		System.out.println();
	}

	@Test
	public void test6() {
		// 97 123 1 123 S S 3
		CornField field = new CornField(97, 123);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.SOUTH,
				1, 123, 3, Style.S);
		System.out.println(harvestWithMultipleMowers);
		// Assert.assertEquals("",harvestWithMultipleMowers);
		System.out.println();
		System.out.println();
	}

	// @Test
	public void test7() {
		// 97 123 8 123 W Z 8
		CornField field = new CornField(97, 123);
		System.out.println(field);
		String harvestWithMultipleMowers = HarvestingScenarioExecutor.harvestWithMultipleMowers(field, Direction.WEST,
				8, 123, 8, Style.C);
		System.out.println(harvestWithMultipleMowers);
		// Assert.assertEquals("",harvestWithMultipleMowers);
		System.out.println();
		System.out.println();
	}
}