package ccc.harvester.test;

import org.testng.annotations.Test;

import ccc.harvester.CornField;
import ccc.harvester.Direction;
import ccc.harvester.HarvestingCalculator;

public class Level4Tester_Circular {

	@Test
	public void test() {

		System.out.println(new CornField(3, 4));
		System.out.println(
				HarvestingCalculator.harvestCircularStartFromGivenCorner(new CornField(3, 4), Direction.SOUTH, 1, 4));

		System.out.println();
		System.out.println();
		
		System.out.println(new CornField(5, 2));
		System.out.println(
				HarvestingCalculator.harvestCircularStartFromGivenCorner(new CornField(5, 2), Direction.NORTH, 5, 2));
	}
}