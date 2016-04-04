package ccc.harvester.test;

import org.testng.annotations.Test;

import ccc.harvester.CornField;
import ccc.harvester.HarvestingCalculator;

public class Level2Tester_Serpentine_Corner {

	@Test
	public void test() {

		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCorner(new CornField(2, 5), 2, 1));
		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCorner(new CornField(5, 2), 5, 2));
		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCorner(new CornField(23, 12), 1, 12));
	}
}