package ccc.addictiveGame;

import static ccc.addictiveGame.execute.Executor.execute;
import org.testng.annotations.Test;

public class Level1_GetFamiliar {

	@Test
	public void test1() {
		execute("6 4 3 1 11 24");
	}

	@Test
	public void test2() {
		execute("6 4 24 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24");
	}

	@Test
	public void test3() {
		execute("");
	}

	@Test
	public void test4() {
		execute("");
	}
}