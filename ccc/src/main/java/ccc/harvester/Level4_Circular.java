package ccc.harvester;

import static ccc.harvester.exec.HarvestingScenarioExecutor.execute;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Level4_Circular {

	@Test
	public void test1() {
		String result = execute("3 4 1 4 S Z");
		Assert.assertEquals(result, "4 8 12 9 5 1 3 7 11 10 6 2 ");
	}

	@Test
	public void test2() {
		String result = execute("5 2 5 2 N Z");
		Assert.assertEquals(result, "10 8 6 4 2 1 3 5 7 9 ");
	}
}