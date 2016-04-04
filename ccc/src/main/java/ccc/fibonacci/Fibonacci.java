package ccc.fibonacci;

import org.testng.annotations.Test;

public class Fibonacci {

	@Test
	public void test() {
		System.out.println(createFibonacci(0l, 1l, 1, 6));
		System.out.println(createFibonacci(0l, 1l, 1, 19));
		System.out.println(createFibonacci(0l, 1l, 1, 28));
		System.out.println(createFibonacci(0l, 1l, 1, 36));
		System.out.println(createFibonacci(0l, 1l, 1, 38));
	}

	private long createFibonacci(long previousResult, long currentResult, int currentIteration, int iterations) {
		if (currentIteration < iterations) {
			long nextprevious = currentResult;
			long nextCurrentResult = currentResult + previousResult;

			return createFibonacci(nextprevious, nextCurrentResult, ++currentIteration, iterations);
		} else {
			return currentResult;
		}
	}
}