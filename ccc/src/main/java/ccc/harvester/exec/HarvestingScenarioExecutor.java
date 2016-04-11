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
			if (executeParams.getCorner() == CornerPosition.NOT_A_CORNER) {
				return executeCircularStyleWithStartCellNotACorner(executeParams);
			} else {
				return executeCircularStyle(executeParams);
			}
		} else {
			throw new RuntimeException("Can't be!");
		}
	}

	private static String executeSerpentineStyle(ExecuteParams executeParams) {

		Scenario scenario = Scenario.build().error().getSerpentineScenario();

		CornerPosition corner = executeParams.getCorner();
		Direction direction = executeParams.getDirection();
		int mowers = executeParams.getMowers();

		String result = "";
		switch (corner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goWestWithSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithEasternNeighbours(mowers)//
						.goEastWithoutMowing(1)//
						.goNorthWesternNeighbours(mowers, true)//
						.goEastWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goEastWithSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithWesternNeighbours(mowers)//
						.goWestWithoutMowing(1)//
						.goNorthWesternNeighbours(mowers, true)//
						.goWestWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goWestWithNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithWesternNeighbours(mowers)//
						.goEastWithoutMowing(1)//
						.goSouthEasternNeighbours(mowers, true)//
						.goEastWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goEastWithNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithEasternNeighbours(mowers)//
						.goWestWithoutMowing(1)//
						.goSouthEasternNeighbours(mowers, true)//
						.goWestWithoutMowing(mowers)//
						.getSerpentineScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		System.out.println(result);
		return result;
	}

	private static String executeCircularStyleWithStartCellNotACorner(ExecuteParams executeParams) {

		Scenario scenario = Scenario.build().error().getCircularScenario();
		Direction direction = executeParams.getDirection();
		int mowers = executeParams.getMowers();
		String result = "";
		CornerPosition cornerWithMowers = executeParams.getCornerConsideringMower();

		switch (cornerWithMowers) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithNorthernNeighbours(mowers)//
						.goWestWithSouthernNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithWesternNeighbours(mowers)//
						.goNorthWithEasternNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithNorthernNeighbours(mowers)//
						.goEastWithSouthernNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithEasternNeighbours(mowers)//
						.goNorthWithWesternNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithSouthernNeighbours(mowers)//
						.goWestWithNorthernNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithWesternNeighbours(mowers)//
						.goSouthWithEasternNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithSouthernNeighbours(mowers)//
						.goEastWithNorthernNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithEasternNeighbours(mowers)//
						.goSouthWithWesternNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		System.out.println(result);
		return result;
	}

	private static String executeCircularStyle(ExecuteParams executeParams) {

		Scenario scenario = Scenario.build().error().getCircularScenario();

		CornerPosition corner = executeParams.getCorner();
		Direction direction = executeParams.getDirection();
		int mowers = executeParams.getMowers();

		String result = "";
		switch (corner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithSouthernNeighbours(mowers)//
						.goWestWithNorthernNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithEasternNeighbours(mowers)//
						.goNorthWithWesternNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithSouthernNeighbours(mowers)//
						.goEastWithNorthernNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case SOUTH:
				scenario = Scenario.build()//
						.goSouthWithWesternNeighbours(mowers)//
						.goNorthWithEasternNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build()//
						.goEastWithNorthernNeighbours(mowers)//
						.goWestWithSouthernNeighbours(mowers)//
						.getCircularScenario();//
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithEasternNeighbours(mowers)//
						.goSouthWithWesternNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build()//
						.goWestWithNorthernNeighbours(mowers)//
						.goEastWithSouthernNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			case NORTH:
				scenario = Scenario.build()//
						.goNorthWithWesternNeighbours(mowers)//
						.goSouthWithEasternNeighbours(mowers)//
						.getCircularScenario();
				result = scenario.executeSteps(executeParams);
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		System.out.println(result);
		return result;
	}
}