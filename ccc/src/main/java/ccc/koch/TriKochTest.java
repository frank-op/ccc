package ccc.koch;

import org.testng.annotations.Test;

public class TriKochTest {

	@Test
	public void createKochSnowflake() {

		System.out.println(generateKochSnowflakeForTriangle(243 * 3, 0, 3));
		System.out.println(generateKochSnowflakeForTriangle(19683 * 3, 0, 7));
		System.out.println(generateKochSnowflakeForTriangle(531441 * 3, 0, 7));
		System.out.println(generateKochSnowflakeForTriangle(531441 * 3, 0, 9));

		System.out.println();

		System.out.println(generateKochSnowflakeForRectangle(243 * 4, 0, 3));
		System.out.println(generateKochSnowflakeForRectangle(19683 * 4, 0, 7));
		System.out.println(generateKochSnowflakeForRectangle(531441 * 4, 0, 7));
		System.out.println(generateKochSnowflakeForRectangle(531441 * 4, 0, 9));
	}

	public long generateKochSnowflakeForTriangle(long length, int currentIteration, int iterations) {
		if (currentIteration < iterations) {
			length = length * 4 / 3;
			return generateKochSnowflakeForTriangle(length, ++currentIteration, iterations);
		} else {
			return length;
		}
	}

	public long generateKochSnowflakeForRectangle(long length, int currentIteration, int iterations) {
		if (currentIteration < iterations) {
			length = length * 5 / 3;
			return generateKochSnowflakeForRectangle(length, ++currentIteration, iterations);
		} else {
			return length;
		}
	}
}