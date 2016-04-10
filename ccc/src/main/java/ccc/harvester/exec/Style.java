package ccc.harvester.exec;

public enum Style {
	SERPENTINE("S"), CIRCULAR("Z");

	private String styleAsString;

	private Style(String styleAsString) {
		this.styleAsString = styleAsString;

	}

	static Style getStyleForString(String thatStyleAsString) {
		for (Style style : values()) {
			if (style.styleAsString.equals(thatStyleAsString)) {
				return style;
			}
		}
		throw new RuntimeException("Can't recognize Direction " + thatStyleAsString);
	}
}