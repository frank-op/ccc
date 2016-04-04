package ccc.harvester.test;

import org.testng.annotations.Test;

import ccc.harvester.CornField;
import ccc.harvester.Direction;
import ccc.harvester.HarvestingCalculator;

public class Level3Columns {

	@Test
	public void test() {
		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCornerColumns(new CornField(3, 4),
				Direction.SOUTH, 1, 1));
		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCornerColumns(new CornField(5, 2),
				Direction.NORTH, 5, 2));
		System.out.println(HarvestingCalculator.harvestSerpentinesStartFromGivenCornerColumns(new CornField(23, 12),
				Direction.NORTH, 23, 1));
	}
}