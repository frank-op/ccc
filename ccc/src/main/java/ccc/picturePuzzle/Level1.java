package ccc.picturePuzzle;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ccc.picturePuzzle.execute.Executor;

public class Level1 {

	private List<String> list;

	@BeforeClass
	public void setUp() {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/picturePuzzle/input-level1.txt";
		list = Executor.readInAndGetStringList(fileName);
	}

	@Test
	public void test1() {
		String result = doTest(0);
		System.out.println(result);
		Assert.assertEquals(result, "??1??");
	}

	@Test
	public void test2() {
		String result = doTest(1);
		System.out.println(result);
		Assert.assertEquals(result, "111111111111111111");
	}

	@Test
	public void test3() {
		String result = doTest(2);
		System.out.println(result);
		Assert.assertEquals(result, "??????????");
	}

	@Test
	public void test4() {
		String result = doTest(3);
		System.out.println(result);
		Assert.assertEquals(result, "??????????");
	}

	@Test
	public void test5() {
		String result = doTest(4);
		System.out.println(result);
		Assert.assertEquals(result, "???111???");
	}

	@Test
	public void test6() {
		String result = doTest(5);
		System.out.println(result);
		Assert.assertEquals(result,
				"??????????????????????????????????????????????????????111111111111111??????????????????????????????????????????????????????");
	}

	private String doTest(int lineIndex) {
		return Executor.executeLevel1(list.get(lineIndex));
	}
}