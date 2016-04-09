package ccc.harvester.level;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.HarvestingScenarioExecutor;
import ccc.harvester.HarvestingScenarioExecutor.Direction;
import ccc.harvester.field.CornField;

public class Level4Tester_Circular {

	@Test
	public void test1() {

		System.out.println(new CornField(3, 4));
		String harvestCircularStartFromGivenCorner = HarvestingScenarioExecutor
				.harvestCircularStartFromGivenCorner(new CornField(3, 4), Direction.SOUTH, 1, 4);
		System.out.println(harvestCircularStartFromGivenCorner);
		Assert.assertEquals(harvestCircularStartFromGivenCorner, "4 8 12 9 5 1 3 7 11 10 6 2 ");

		System.out.println();
		System.out.println();
	}

	@Test
	public void test2() {

		System.out.println(new CornField(5, 2));
		String harvestCircularStartFromGivenCorner = HarvestingScenarioExecutor
				.harvestCircularStartFromGivenCorner(new CornField(5, 2), Direction.NORTH, 5, 2);
		System.out.println(harvestCircularStartFromGivenCorner);
		Assert.assertEquals(harvestCircularStartFromGivenCorner, "10 8 6 4 2 1 3 5 7 9 ");
	}
}