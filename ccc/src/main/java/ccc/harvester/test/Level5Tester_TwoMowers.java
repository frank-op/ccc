package ccc.harvester.test;

import org.testng.annotations.Test;

import ccc.harvester.CornField;
import ccc.harvester.Direction;
import ccc.harvester.HarvestingCalculator;
import ccc.harvester.HarvestingCalculator.Style;

public class Level5Tester_TwoMowers {

	@Test
	public void test() {

		// // 5 4 1 1 O S 2
		// CornField field = new CornField(5, 4);
		// System.out.println(field);
		// System.out.println(HarvestingCalculator.harvestWith2Mowers(field,
		// Direction.EAST, 1, 1, Style.S));
		//
		// System.out.println();
		// System.out.println();
		//
		// // 5 4 4 1 O Z 2
		// field = new CornField(5, 4);
		// System.out.println(field);
		// System.out.println(HarvestingCalculator.harvestWith2Mowers(field,
		// Direction.EAST, 1, 1, Style.C));
		//
		// System.out.println();
		// System.out.println();
		//
		// // 10 10 10 10 W S 1
		// field = new CornField(10, 10);
		// System.out.println(field);
		// System.out.println(
		// HarvestingCalculator.harvestSerpentinesStartFromGivenCornerColumns(field,
		// Direction.WEST, 10, 10));
		//
		// System.out.println();
		// System.out.println();

		// 10 10 10 10 W S 2
		CornField field = new CornField(10, 10);
		System.out.println(field);
		System.out.println(HarvestingCalculator.harvestWith2Mowers(field, Direction.WEST, 10, 10, Style.S));

		System.out.println();
		System.out.println();

		// 17 9 17 1 N Z 2
		field = new CornField(17, 9);
		System.out.println(field);
		System.out.println(HarvestingCalculator.harvestWith2Mowers(field, Direction.NORTH, 17, 1, Style.C));

		System.out.println();
		System.out.println();

	}
}