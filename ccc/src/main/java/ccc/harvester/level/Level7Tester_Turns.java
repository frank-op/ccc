package ccc.harvester.level;

import static ccc.harvester.exec.HarvestingScenarioExecutor.execute;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		String harvestWithMultipleMowers = execute("5 4 1 1 O S 3");
		Assert.assertEquals(harvestWithMultipleMowers, "1 5 9 2 6 10 3 7 11 4 8 12 0 20 16 0 19 15 0 18 14 0 17 13 ");
	}

	@Test
	public void test2() {
		// 5 4 3 1 O Z 3
		String harvestWithMultipleMowers = execute("5 4 3 1 O Z 3");
		Assert.assertEquals("9 13 17 10 14 18 11 15 19 12 16 20 8 4 0 7 3 0 6 2 0 5 1 0 ", harvestWithMultipleMowers);
	}

	@Test
	public void test3() {
		// 10 10 10 10 W S 1
		String harvestWithMultipleMowers = execute("10 10 10 10 W S 1");
		Assert.assertEquals(
				"100 99 98 97 96 95 94 93 92 91 81 82 83 84 85 86 87 88 89 90 80 79 "
						+ "78 77 76 75 74 73 72 71 61 62 63 64 65 66 67 68 69 70 60 59 58 57 56 "
						+ "55 54 53 52 51 41 42 43 44 45 46 47 48 49 50 40 39 38 37 36 35 34 33 "
						+ "32 31 21 22 23 24 25 26 27 28 29 30 20 19 18 17 16 15 14 13 12 11 1 2 3 4 5 6 7 8 9 10 ",
				harvestWithMultipleMowers);
	}

	@Test
	public void test4() {
		// 10 10 10 10 W S 3
		String harvestWithMultipleMowers = execute("10 10 10 10 W S 3");
		Assert.assertEquals(
				"100 90 80 99 89 79 98 88 78 97 87 77 96 86 76 95 85 75 94 84 74 93 83 73 92 82 72 91 81 71 41 51 61 42 52 62 43 53 63 44 54 64 45 55 65 46 56 66 47 57 67 48 58 68 49 59 69 50 60 70 40 30 20 39 29 19 38 28 18 37 27 17 36 26 16 35 25 15 34 24 14 33 23 13 32 22 12 31 21 11 0 0 1 0 0 2 0 0 3 0 0 4 0 0 5 0 0 6 0 0 7 0 0 8 0 0 9 0 0 10 ",
				harvestWithMultipleMowers);
	}

	@Test
	public void test5() {
		// 17 9 17 1 N Z 3
		String harvestWithMultipleMowers = execute("17 9 17 1 N Z 3");
		Assert.assertEquals(
				"145 146 147 136 137 138 127 128 129 118 119 120 109 110 111 100 101 102 91 92 93 82 83 84 73 74 75 64 65 66 55 56 57 46 47 48 37 38 39 28 29 30 19 20 21 10 11 12 1 2 3 9 8 7 18 17 16 27 26 25 36 35 34 45 44 43 54 53 52 63 62 61 72 71 70 81 80 79 90 89 88 99 98 97 108 107 106 117 116 115 126 125 124 135 134 133 144 143 142 153 152 151 148 149 150 139 140 141 130 131 132 121 122 123 112 113 114 103 104 105 94 95 96 85 86 87 76 77 78 67 68 69 58 59 60 49 50 51 40 41 42 31 32 33 22 23 24 13 14 15 4 5 6 ",
				harvestWithMultipleMowers);
	}

	@Test
	public void test6() {
		// 97 123 1 123 S S 3
		String harvestWithMultipleMowers = execute("97 123 1 123 S S 3");
	}

	@Test
	public void test7() {
		// 97 123 8 123 W Z 8
		String harvestWithMultipleMowers = execute("97 123 8 123 W Z 8");
		// Assert.assertEquals("",harvestWithMultipleMowers);
	}
}