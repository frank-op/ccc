package ccc.harvester.level;

import static ccc.harvester.exec.HarvestingScenarioExecutor.execute;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ccc.harvester.scenarios.Scenario;

public class Level6Tester_Turns {

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
		String result = execute("5 4 1 1 O S 2");
		Assert.assertEquals(result, "1 5 2 6 3 7 4 8 16 12 15 11 14 10 13 9 17 0 18 0 19 0 20 0 ");
	}

	@Test
	public void test2() {
		String result = execute("5 4 4 1 O Z 2");
		Assert.assertEquals(result, "13 17 14 18 15 19 16 20 8 4 7 3 6 2 5 1 9 0 10 0 11 0 12 0 ");

		System.out.println();
		System.out.println();
	}

	@Test
	public void test3() {
		String result = execute("10 10 10 10 W S 1");
		Assert.assertEquals(
				"100 99 98 97 96 95 94 93 92 91 81 82 83 84 85 86 87 88 89 90 80 79 78 77 76 75 74 73 72 71 61 62 63 64 65 66 67 68 69 70 60 59 58 57 56 55 54 53 52 51 41 42 43 44 45 46 47 48 49 50 40 39 38 37 36 35 34 33 32 31 21 22 23 24 25 26 27 28 29 30 20 19 18 17 16 15 14 13 12 11 1 2 3 4 5 6 7 8 9 10 ",
				result);

		System.out.println();
		System.out.println();
	}

	@Test
	public void test4() {
		String result = execute("10 10 10 10 W S 2");
		Assert.assertEquals(
				"100 90 99 89 98 88 97 87 96 86 95 85 94 84 93 83 92 82 91 81 61 71 62 72 63 73 64 74 65 75 66 76 67 77 68 78 69 79 70 80 60 50 59 49 58 48 57 47 56 46 55 45 54 44 53 43 52 42 51 41 21 31 22 32 23 33 24 34 25 35 26 36 27 37 28 38 29 39 30 40 20 10 19 9 18 8 17 7 16 6 15 5 14 4 13 3 12 2 11 1 ",
				result);

		System.out.println();
		System.out.println();
	}

	@Test
	public void test5() {
		String result = execute("17 9 17 1 N Z 2");
		Assert.assertEquals(
				"145 146 136 137 127 128 118 119 109 110 100 101 91 92 82 83 73 74 64 65 55 56 46 47 37 38 28 29 19 20 10 11 1 2 9 8 18 17 27 26 36 35 45 44 54 53 63 62 72 71 81 80 90 89 99 98 108 107 117 116 126 125 135 134 144 143 153 152 147 148 138 139 129 130 120 121 111 112 102 103 93 94 84 85 75 76 66 67 57 58 48 49 39 40 30 31 21 22 12 13 3 4 7 6 16 15 25 24 34 33 43 42 52 51 61 60 70 69 79 78 88 87 97 96 106 105 115 114 124 123 133 132 142 141 151 150 0 149 0 140 0 131 0 122 0 113 0 104 0 95 0 86 0 77 0 68 0 59 0 50 0 41 0 32 0 23 0 14 0 5 ",
				result);
		System.out.println();
		System.out.println();
	}

}