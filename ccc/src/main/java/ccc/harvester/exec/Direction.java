package ccc.harvester.exec;

public enum Direction {
	NORTH("N"), EAST("O"), SOUTH("S"), WEST("W");

	private String directionAsString;

	private Direction(String directionAsString) {
		this.directionAsString = directionAsString;
	}

	static Direction getDirectionForString(String thatDirectionAsString) {
		for (Direction direction : values()) {
			if (direction.directionAsString.equals(thatDirectionAsString)) {
				return direction;
			}
		}
		throw new RuntimeException("Can't recognize Direction " + thatDirectionAsString);
	}
}