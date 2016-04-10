package ccc.harvester.exec;

import ccc.harvester.exec.ExecuteParams.CornerPosition;
import ccc.harvester.scenarios.Scenario;

public class HarvestingScenarioExecutor {

	public static String execute(String args) {

		ExecuteParams executeParams = new ExecuteParams(args);
		return execute(executeParams);
	}

	private static String execute(ExecuteParams executeParams) {
		if (executeParams.getStyle() == Style.SERPENTINE) {
			return executeSerpentineStyle(executeParams);
		} else if (executeParams.getStyle() == Style.CIRCULAR) {
			return executeCircularStyle(executeParams);
		} else {
			throw new RuntimeException("Can't be!");
		}
	}

	private static String executeSerpentineStyle(ExecuteParams executeParams) {

		Scenario scenario = Scenario.build().error().getSerpentineScenario();

		CornerPosition corner = executeParams.getCorner();
		Direction direction = executeParams.getDirection();
		int mowers = executeParams.getMowers();

		switch (corner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goWestWithMowersSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.goEastWithoutMowing(1)//
						.goNorthWithMowersWesternNeighbours(mowers, true)//
						.goEastWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithMowersSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goEastWithMowersSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithMowersWesternNeighbours(mowers)//
						.goWestWithoutMowing(1)//
						.goNorthWithMowersWesternNeighbours(mowers, true)//
						.goWestWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithMowersNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goWestWithMowersNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithMowersWesternNeighbours(mowers)//
						.goEastWithoutMowing(1)//
						.goSouthWithMowersEasternNeighbours(mowers, true)//
						.goEastWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithMowersNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goEastWithMowersNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithMowersEasternNeighbours(mowers)//
						.goWestWithoutMowing(1)//
						.goSouthWithMowersEasternNeighbours(mowers, true)//
						.goWestWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		String result = scenario.executeSteps(executeParams);
		System.out.println(result);
		return result;
	}

	private static String executeCircularStyle(ExecuteParams executeParams) {

		Scenario scenario = Scenario.build().error().getCircularScenario();

		CornerPosition corner = executeParams.getCorner();
		Direction direction = executeParams.getDirection();
		int mowers = executeParams.getMowers();

		switch (corner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goWestWithMowersSouthernNeighbours(mowers)//
						.getCircularScenario();
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.goNorthWithMowersWesternNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithMowersNorthernNeighbours(mowers)//
						.goEastWithMowersNorthernNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.goNorthWithMowersWesternNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goWestWithMowersSouthernNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithMowersEasternNeighbours(mowers)//
						.goSouthWithMowersWesternNeighbours(mowers)//
						.getCircularScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithMowersWesternNeighbours(mowers)//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.getCircularScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		String result = scenario.executeSteps(executeParams);
		System.out.println(result);
		return result;
	}
}