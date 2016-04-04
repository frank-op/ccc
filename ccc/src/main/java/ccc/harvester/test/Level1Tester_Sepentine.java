package ccc.harvester.test;

import static ccc.harvester.HarvestingCalculator.*;

import org.testng.annotations.Test;

import ccc.harvester.CornField;

public class Level1Tester_Sepentine {

	@Test
	public void testLevel1() {
		System.out.println(harvestInSerpentinesStartTopLeft(new CornField(5, 5), 1, 1));
	}
}