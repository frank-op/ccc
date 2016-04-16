package ccc.bowling;

import java.util.LinkedList;

import org.testng.annotations.Test;

import ccc.bowling.Attempt.AttemptResult;
import ccc.bowling.Attempt.AttemptState;

public class BowlingLevels {

	// 3:1,2,6,4,5,2
	// 2:1,2,6,4,5
	// 1:2,8,5
	// 3:0,0,9,1,0,0

	public void testBowlingLevel1() {
		Game bowlingGame = new Game();
		bowlingGame.addThrows(1, 2, 6, 4, 5, 2);
		System.out.println(bowlingGame.cccToString(3));

		bowlingGame = new Game();
		bowlingGame.addThrows(1, 2, 6, 4, 5);
		System.out.println(bowlingGame.cccToString(2));

		bowlingGame = new Game();
		bowlingGame.addThrows(2, 8, 5);
		System.out.println(bowlingGame.cccToString(1));

		bowlingGame = new Game();
		bowlingGame.addThrows(0, 0, 9, 1, 0, 0);
		System.out.println(bowlingGame.cccToString(3));
	}

	public void testBowlingLevel2() {

		// 4:1,5,5,5,4,6,8,1
		Game bowlingGame = new Game();
		bowlingGame.addThrows(1, 5, 5, 5, 4, 6, 8, 1);
		System.out.println(bowlingGame.cccToString(4));

		// 3:1,5,5,5,4,6,8
		bowlingGame = new Game();
		bowlingGame.addThrows(1, 5, 5, 5, 4, 6, 8);
		System.out.println(bowlingGame.cccToString(3));
	}

	public void testBowlingLevel3() {

		// 3:3,4,10,1,2
		Game bowlingGame = new Game();
		bowlingGame.addThrows(3, 4, 10, 1, 2);
		System.out.println(bowlingGame.cccToString(3));

		// 2:3,4,10,1,2
		bowlingGame = new Game();
		bowlingGame.addThrows(3, 4, 10, 1, 2);
		System.out.println(bowlingGame.cccToString(2));
	}

	public void testBowlingLevel4() {

		// 4:1,5,10,10,1,7
		Game bowlingGame = new Game();
		bowlingGame.addThrows(1, 5, 10, 10, 1, 7);
		System.out.println(bowlingGame.cccToString(4));

		// 3:1,5,10,10,1,7
		bowlingGame = new Game();
		bowlingGame.addThrows(1, 5, 10, 10, 1, 7);
		System.out.println(bowlingGame.cccToString(3));
	}

	public void testBowlingLevel5() {

		// 4:2,7,10,4,6,4,5
		Game bowlingGame = new Game();
		bowlingGame.addThrows(2, 7, 10, 4, 6, 4, 5);
		System.out.println(bowlingGame.cccToString(4));

		// 4:2,7,4,6,10,4,5
		bowlingGame = new Game();
		bowlingGame.addThrows(2, 7, 4, 6, 10, 4, 5);
		System.out.println(bowlingGame.cccToString(4));

		// 3:2,7,4,6,10,4,5
		bowlingGame = new Game();
		bowlingGame.addThrows(2, 7, 4, 6, 10, 4, 5);
		System.out.println(bowlingGame.cccToString(3));
	}

	@Test
	public void testBowlingLevel6() {

		// 10:1,4,4,5,6,4,5,5,10,0,1,7,3,6,4,10,2,8,6
		Game bowlingGame = new Game();
		bowlingGame.addThrows(1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6);
		System.out.println(bowlingGame.cccToString(10));

		// 10:0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
		bowlingGame = new Game();
		bowlingGame.addThrows(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		System.out.println(bowlingGame.cccToString(10));

		// 10:10,10,10,10,10,10,10,10,10,10,10,10
		bowlingGame = new Game();
		bowlingGame.addThrows(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		System.out.println(bowlingGame.cccToString(10));

		// 10:7,2,1,9,6,4,5,5,10,3,7,7,3,6,4,10,2,8,6
		bowlingGame = new Game();
		bowlingGame.addThrows(7, 2, 1, 9, 6, 4, 5, 5, 10, 3, 7, 7, 3, 6, 4, 10, 2, 8, 6);
		System.out.println(bowlingGame.cccToString(10));
	}
}

class Game {
	LinkedList<Attempt> attempts = new LinkedList<Attempt>();

	public void addThrows(int... pins) {
		for (int i : pins) {
			addThrow(i);
		}
	}

	public void addThrow(int pins) {
		if (attempts.isEmpty() || attempts.getLast().state == AttemptState.DONE) {
			handleNewAttempt(pins);
		} else {
			handleSecondThrow(pins);
		}
	}

	public String cccToString(int howManyAttemptsToWrite) {
		StringBuilder builder = new StringBuilder();
		Calculator calc = new Calculator();

		for (int i = 0; i < attempts.size() - (attempts.size() - howManyAttemptsToWrite); i++) {
			builder.append(calc.calculateResult(this, i + 1)).append(",");
		}
		return builder.toString().substring(0, builder.toString().length() - 1);
	}

	private void handleNewAttempt(int pins) {
		Attempt attempt = new Attempt();
		attempt.throwOne = pins;
		if (attempt.throwOne == 10) {
			attempt.state = AttemptState.DONE;
			attempt.result = AttemptResult.STRIKE;
		}
		attempts.add(attempt);
	}

	private void handleSecondThrow(int pins) {
		Attempt lastAttempt = attempts.getLast();
		lastAttempt.throwTwo = pins;
		lastAttempt.state = AttemptState.DONE;

		if (lastAttempt.throwOne == 10) {
			lastAttempt.result = AttemptResult.STRIKE;
		} else if (lastAttempt.throwOne + lastAttempt.throwTwo == 10) {
			lastAttempt.result = AttemptResult.SPARE;
		}
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		for (Attempt attempt : attempts) {
			builder.append(attempt).append("\n");
		}
		builder.append("Result: ").append(new Calculator().calculateResult(this, this.attempts.size()));

		return builder.toString();
	}
}

class Attempt {

	enum AttemptState {
		OPEN, DONE;
	}

	enum AttemptResult {
		NORMAL, SPARE, STRIKE;
	}

	int throwOne, throwTwo;
	AttemptState state = AttemptState.OPEN;
	AttemptResult result = AttemptResult.NORMAL;

	@Override
	public String toString() {
		return "Attempt [throwOne=" + throwOne + ", throwTwo=" + throwTwo + ", state=" + state + ", result=" + result
				+ "]";
	}
}

class Calculator {

	public int calculateResult(Game game, int attemptCount) {
		LinkedList<Attempt> attempts = game.attempts;

		int sum = 0;
		for (int i = 0; i < attempts.size() - (attempts.size() - attemptCount); i++) {
			Attempt attempt = attempts.get(i);
			sum += attempt.throwOne + attempt.throwTwo;
		}

		for (int i = 0; i < attempts.size() - (attempts.size() - attemptCount); i++) {
			Attempt attempt = attempts.get(i);
			if (attempt.result == AttemptResult.SPARE) {
				Attempt nextAttempt;
				try {
					nextAttempt = attempts.get(i + 1);
					sum += nextAttempt.throwOne;
				} catch (IndexOutOfBoundsException e) {

				}
			} else if (attempt.result == AttemptResult.STRIKE) {
				Attempt nextAttempt;
				try {
					nextAttempt = attempts.get(i + 1);
					if (nextAttempt.result == AttemptResult.STRIKE) {
						sum += nextAttempt.throwOne + attempts.get(i + 2).throwOne;
					} else {
						sum += nextAttempt.throwOne + nextAttempt.throwTwo;
					}
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
		return sum;
	}
}