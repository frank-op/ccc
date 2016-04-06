package ccc.harvester;

import ccc.harvester.field.CornField;
import ccc.harvester.field.CornField.CornerPosition;
import ccc.harvester.scenarios.Scenario;
import ccc.harvester.scenarios.SerpentineScenario;

public class HarvestingCalculator {

	public static String harvestInSerpentinesStartTopLeft(CornField field, int row, int column) {
		SerpentineScenario scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1)
				.getSerpentineScenario();
		return scenario.executeSteps(field, row, column);
	}

	public static String harvestSerpentinesStartFromGivenCorner(CornField field, int row, int column) {
		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario;

		switch (whichCorner) {
		case TOP_LEFT:
			scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getSerpentineScenario();
			break;
		case TOP_RIGHT:
			scenario = Scenario.build(field).goWest().goSouth(1).goEast().goSouth(1).getSerpentineScenario();
			break;
		case BOTTOM_LEFT:
			scenario = Scenario.build(field).goEast().goNorth(1).goWest().goNorth(1).getSerpentineScenario();
			break;
		case BOTTOM_RIGHT:
			scenario = Scenario.build(field).goWest().goNorth(1).goEast().goNorth(1).getSerpentineScenario();
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
				scenario = Scenario.build(field).goEast().goSouth(1).goWest().goSouth(1).getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goEast(1).goNorth().goEast(1).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goSouth(1).goEast().goSouth(1).getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouth().goWest(1).goNorth().goWest(1).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEast().goNorth(1).goWest().goNorth(1).getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goEast(1).goSouth().goEast(1).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWest().goNorth(1).goEast().goNorth(1).getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorth().goWest(1).goSouth().goWest(1).getSerpentineScenario();
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
			scenario = Scenario.build(field).goEast().goWest().getCircularScenario();
			break;
		case SOUTH:
			scenario = Scenario.build(field).goSouth().goNorth().getCircularScenario();
			break;
		case WEST:
			scenario = Scenario.build(field).goWest().goEast().getCircularScenario();
			break;
		case NORTH:
			scenario = Scenario.build(field).goNorth().goSouth().getCircularScenario();
			break;
		}
		return scenario.executeSteps(field, row, column);
	}

	public static String harvestWith2Mowers(CornField field, Direction direction, int row, int column, Style style) {

		if (style == Style.S) {
			return harvestWith2MowersSerpentine(field, direction, row, column);
		} else if (style == Style.C) {
			return harvestWith2MowersCircular(field, direction, row, column);
		}
		throw new RuntimeException();
	}

	private static String harvestWith2MowersCircular(CornField field, Direction direction, int row, int column) {

		Scenario scenario = Scenario.build(null).error().getCircularScenario();

		CornerPosition whichCorner = field.whichCorner(row, column, 2);

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
						.goSouthWithMowersEasternNeighbours(2)//
						.goNorthWithMowersWesternNeighbours(2)//
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
						.goWestWithMowersSouthernNeighbours(2)//
						.goEastWithMowersNorthernNeighbours(2)//
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
						.goEastWithMowersSouthernNeighbours(2)//
						.goWestWithMowersSouthernNeighbours(2, true)//
						.getCircularScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorthWithMowersEasternNeighbours(2)//
						.goSouthWithMowersWesternNeighbours(2)//
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

	private static String harvestWith2MowersSerpentine(CornField field, Direction direction, int row, int column) {

		CornerPosition whichCorner = field.whichCorner(row, column);

		Scenario scenario = Scenario.build(null).error().getSerpentineScenario();

		int mowers = 2;

		switch (whichCorner) {

		case TOP_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field)//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goSouthWithoutMowing(mowers)//
						.goWestWithMowersNorthernNeighbours(mowers)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.goEastWithoutMowing(mowers)//
						.goNorthWithMowersEasternNeighbours(mowers)//
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
						.goSouthWithoutMowing(mowers)//
						.goEastWithMowersNorthernNeighbours(mowers)//
						.goSouthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case SOUTH:
				scenario = Scenario.build(field)//
						.goSouthWithMowersEasternNeighbours(mowers)//
						.goWestWithoutMowing(mowers)//
						.goNorthWithMowersEasternNeighbours(mowers)//
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
						.goNorthWithoutMowing(mowers)//
						.goWestWithMowersSouthernNeighbours(mowers)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorthWithMowersWesternNeighbours(mowers)//
						.goNorthWithoutMowing(mowers)//
						.goSouthWithMowersWesternNeighbours(mowers)//
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
						.goNorthWithoutMowing(mowers)//
						.goEastWithMowersSouthernNeighbours(mowers)//
						.goNorthWithoutMowing(mowers)//
						.getSerpentineScenario();//
				break;
			case NORTH:
				scenario = Scenario.build(field)//
						.goNorthWithMowersEasternNeighbours(mowers)//
						.goWestWithoutMowing(mowers)//
						.goSouthWithMowersWesternNeighbours(mowers)//
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

	public enum Style {
		S, C;
	}
}