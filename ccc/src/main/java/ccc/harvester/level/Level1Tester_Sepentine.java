package ccc.harvester.level;

import static ccc.harvester.exec.HarvestingScenarioExecutor.execute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Level1Tester_Sepentine {

	@Test
	public void testLevel1() {
		String result = execute("5 5 1 1");

		Assert.assertEquals(result, "1 2 3 4 5 10 9 8 7 6 11 12 13 14 15 20 19 18 17 16 21 22 23 24 25 ");
	}
}