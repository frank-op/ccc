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
		switch (direction) {
		case EAST:
			scenario = Scenario.build(field).goEastWithMowers(2).goWestWithMowersFaky(2).getCircularScenario();
			break;
		case SOUTH:
			scenario = Scenario.build(field).goSouthWithMowers(2).goNorthWithMowersFaky(2).getCircularScenario();
			break;
		case WEST:
			scenario = Scenario.build(field).goWestWithMowers(2).goEastWithMowersFaky(2).getCircularScenario();
			break;
		case NORTH:
			scenario = Scenario.build(field).goNorthWithMowers(2).goSouthWithMowersFaky(2).getCircularScenario();
			break;
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
				scenario = Scenario.build(field).goEastWithMowers(mowers).goSouthWithoutMowing(mowers)
						.goWestWithMowersFaky(mowers).goSouthWithoutMowing(mowers).getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouthWithMowers(mowers).goEastWithoutMowing(mowers)
						.goNorthWithMowersFaky(mowers).goEastWithoutMowing(mowers).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case TOP_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWestWithMowers(mowers).goSouthWithoutMowing(mowers)
						.goEastWithMowersFaky(mowers).goSouthWithoutMowing(mowers).getSerpentineScenario();
				break;
			case SOUTH:
				scenario = Scenario.build(field).goSouthWithMowers(mowers).goWestWithoutMowing(mowers)
						.goNorthWithMowersFaky(mowers).goWestWithoutMowing(mowers).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_LEFT:
			switch (direction) {
			case EAST:
				scenario = Scenario.build(field).goEastWithMowersFaky(mowers).goNorthWithoutMowing(mowers)
						.goWestWithMowers(mowers).goNorthWithoutMowing(mowers).getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorthWithMowers(mowers).goNorthWithoutMowing(mowers)
						.goSouthWithMowersFaky(mowers).goNorthWithoutMowing(mowers).getSerpentineScenario();
				break;
			default:
				throw new RuntimeException();
			}
			break;
		case BOTTOM_RIGHT:
			switch (direction) {
			case WEST:
				scenario = Scenario.build(field).goWestWithMowersFaky(mowers).goNorthWithoutMowing(mowers)
						.goEastWithMowers(mowers).goNorthWithoutMowing(mowers).getSerpentineScenario();
				break;
			case NORTH:
				scenario = Scenario.build(field).goNorthWithMowersFaky(mowers).goWestWithoutMowing(mowers)
						.goSouthWithMowersFaky(mowers).goWestWithoutMowing(mowers).getSerpentineScenario();
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