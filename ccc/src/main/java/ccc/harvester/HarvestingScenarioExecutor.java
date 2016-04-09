package ccc.harvester;

import ccc.harvester.field.CornField;
import ccc.harvester.field.CornField.CornerPosition;
import ccc.harvester.scenarios.Scenario;
import ccc.harvester.scenarios.SerpentineScenario;

public class HarvestingScenarioExecutor {

	public static String harvestInSerpentinesStartTopLeft(CornField field, int row, int column) {
		SerpentineScenario scenario = Scenario.build(field)//
				.goEast()//
				.goSouthWithoutMowing(1)//
				.goWest()//
				.goSouthWithoutMowing(1)//
				.getSerpentineScenario();
		return scenario.executeSteps(field, row, column);
	}

	public static String harvestSerpentinesStartFromGivenCorner(CornField field, int row, int column) {
		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario;

		switch (whichCorner) {
		case TOP_LEFT:
			scenario = Scenario.build(field)//
					.goEast()//
					.goSouthWithoutMowing(1)//
					.goWest()//
					.goSouthWithoutMowing(1)//
					.getSerpentineScenario();
			break;
		case TOP_RIGHT:
			scenario = Scenario.build(field)//
					.goWest()//
					.goSouthWithoutMowing(1)//
					.goEast()//
					.goSouthWithoutMowing(1)//
					.getSerpentineScenario();
			break;
		case BOTTOM_LEFT:
			scenario = Scenario.build(field)//
					.goEast()//
					.goNorthWithoutMowing(1)//
					.goWest()//
					.goNorthWithoutMowing(1)//
					.getSerpentineScenario();
			break;
		case BOTTOM_RIGHT:
			scenario = Scenario.build(field)//
					.goWest()//
					.goNorthWithoutMowing(1)//
					.goEast()//
					.goNorthWithoutMowing(1)//
					.getSerpentineScenario();
			break;
		case NOT_A_CORNER:
		default:
			throw new RuntimeException();
		}

		return scenario.executeSteps(field, row, column);
	}

	public static String harvestSerpentinesStartFromGivenCornerColumns(CornField field, Direction direction, int row,
			int column) {

		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario = Scenario.build(null).error().getSerpentineScenario();

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEast()//
						.goSouthWithoutMowing(1)//
						.goWest()//
						.goSouthWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
						.goSouth()//
						.goEastWithoutMowing(1)//
						.goNorth()//
						.goEastWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field)//
						.goWest()//
						.goSouthWithoutMowing(1)//
						.goEast()//
						.goSouthWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
						.goSouth()//
						.goWestWithoutMowing(1)//
						.goNorth()//
						.goWestWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEast()//
						.goNorthWithoutMowing(1)//
						.goWest()//
						.goNorthWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorth()//
						.goEastWithoutMowing(1)//
						.goSouth()//
						.goEastWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field)//
						.goWest()//
						.goNorthWithoutMowing(1)//
						.goEast()//
						.goNorthWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorth()//
						.goWestWithoutMowing(1)//
						.goSouth()//
						.goWestWithoutMowing(1)//
						.getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}

		return scenario.executeSteps(field, row, column);
	}

	public static String harvestCircularStartFromGivenCorner(CornField field, Direction direction, int row,
			int column) {

		Scenario scenario = Scenario.build(null).error().getCircularScenario();
		switch (direction) {
		case EAST:
			scenario = Scenario.build(field)//
					.goEast()//
					.goWest()//
					.getCircularScenario();
			break;
		case SOUTH:
			scenario = Scenario.build(field)//
					.goSouth()//
					.goNorth()//
					.getCircularScenario();
			break;
		case WEST:
			scenario = Scenario.build(field)//
					.goWest()//
					.goEast()//
					.getCircularScenario();
			break;
		case NORTH:
			scenario = Scenario.build(field)//
					.goNorth()//
					.goSouth()//
					.getCircularScenario();
			break;
		}
		return scenario.executeSteps(field, row, column);
	}

	public static String harvestWithMultipleMowers(CornField field, Direction direction, int row, int column,
			int mowers, Style style) {

		if (style == Style.S) {
			return harvestWithMultipleMowersSerpentine(field, direction, row, column, mowers);
		} else if (style == Style.C) {
			return harvestWithMultipleMowersCircular(field, direction, row, column, mowers);
		}
		throw new RuntimeException();
	}

	private static String harvestWithMultipleMowersCircular(CornField field, Direction direction, int row, int column,
			int mowers) {

		Scenario scenario = Scenario.build(null).error().getCircularScenario();

		CornerPosition whichCorner = field.whichCorner(row, column, mowers);

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goWestWithMowersNorthernNeighbours(mowers, true)//
						.getCircularScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
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
				scenario = Scenario.build(field)//
						.goWestWithMowersSouthernNeighbours(mowers)//
						.goEastWithMowersNorthernNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			case SOUTH:
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goWestWithMowersSouthernNeighbours(mowers, true)//
						.getCircularScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
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
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case NOT_A_CORNER:
			throw new RuntimeException();
		}
		return scenario.executeSteps(field, row, column);
	}

	private static String harvestWithMultipleMowersSerpentine(CornField field, Direction direction, int row, int column,
			int mowers) {

		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario = Scenario.build(null).error().getSerpentineScenario();

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goWestWithMowersSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
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
				scenario = Scenario.build(field)//
						.goWestWithMowersSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(1)//
						.goEastWithMowersSouthernNeighbours(mowers, true)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
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
				scenario = Scenario.build(field)//
						.goEastWithMowersNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goWestWithMowersNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorthWithMowersWesternNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goSouthWithMowersEasternNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field)//
						.goWestWithMowersNorthernNeighbours(mowers)//
						.goNorthWithoutMowing(1)//
						.goEastWithMowersNorthernNeighbours(mowers, true)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
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

		return scenario.executeSteps(field, row, column);
	}

	public enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	public enum Style {
		S, C;
	}
}