package ccc.viennaJamCity.execute;

import java.util.List;

public class OutputFormatter {

	public static String formatRoutes_Level1(List<Integer> routes) {

		StringBuilder builder = new StringBuilder();
		routes.stream().forEach(x -> builder.append(x).append(","));
		return builder.substring(0, builder.length() - 1).toString();
	}
}