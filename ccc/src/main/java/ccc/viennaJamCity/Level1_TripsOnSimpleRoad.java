package ccc.viennaJamCity;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ccc.viennaJamCity.execute.OutputFormatter;

public class Level1_TripsOnSimpleRoad {

	@AfterMethod
	public void afterMethod() {
		System.out.println();
	}

	@Test
	public void test0() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_0.in";
		String result = doTest(fileName);
		Assert.assertEquals(result, "98,37,81,50,101");
	}

	@Test
	public void test1() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_1.in";
		String result = doTest(fileName);
		Assert.assertEquals(result, "8,5,9,19,4,29,11,6,14,31");
	}

	@Test
	public void test2() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_2.in";
		String result = doTest(fileName);
		// Assert.assertEquals(result, "");
	}

	@Test
	public void test3() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_3.in";
		String result = doTest(fileName);
		// Assert.assertEquals(result, "");
	}

	@Test
	public void test4() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_4.in";
		String result = doTest(fileName);
		// Assert.assertEquals(result, "");
	}

	@Test
	public void test5() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_5.in";
		String result = doTest(fileName);
		// Assert.assertEquals(result, "");
	}

	@Test
	public void test6() throws IOException {
		String fileName = "/Users/Frank/Documents/git-repos/ccc/ccc/src/main/resources/ccc/viennaJamCity/level1/level1_6.in";
		String result = doTest(fileName);
		// Assert.assertEquals(result, "");
	}

	private String doTest(String fileName) {

		List<String> list = readInAndGetStringList(fileName);
		int numberOfRoadSegments = Integer.valueOf(list.remove(0));
		System.out.println(numberOfRoadSegments);
		int numberOfCars = Integer.valueOf(list.remove(0));
		System.out.println(numberOfCars);

		List<Route> routes = list.stream()
				.map(x -> new Route(Integer.valueOf(x.split(",")[0]), Integer.valueOf(x.split(",")[1])))
				.collect(Collectors.toList());

		routes.forEach(System.out::println);

		List<Integer> toursDuration = calculateTourDurations(routes);

		String result = OutputFormatter.formatRoutes_Level1(toursDuration);
		System.out.println(result);
		return result;
	}

	// TODO das geht so gar nicht: ein auto kann erst auf die spur fahren, wenn
	// vor ihm eins leer ist.
	
	// das heißt, wie kann man das lösen? man braucht einen schritt geber denke ich.
	// das auto oder vielleicht auch ein fahrer müssen prüfen vor dem losfahren, ob der nächste schritt möglich ist, sonst warten. 
	// nachdem sie alle gemeinsam auf die straße gehen, kann nur der nicht, der vor einem wen hat
	// das heißt, dass das losfahren einen eigenen test braucht, denn da sind ja eventuell noch nicht die autos auf der straße, die den weg versperren würden
	
	/*
	 * also: 
	 * #1 baue eine straße
	 * #2 auf der sind 1-n felder eine linkedlist
	 * #3 ein auto hat eine position auf der straße und man kann ihm sagen next
	 * #4 das auto kennt sein ziel und fährt raus, wenn es fertig ist
	 * ... so etwas in der Art
	 * 
	 */
	
	
	private List<Integer> calculateTourDurations(List<Route> routes) {
		return routes.stream().map(x -> (x.end - x.start + 2)).collect(Collectors.toList());
	}

	private List<String> readInAndGetStringList(String fileName) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static class Route {
		int start, end;

		public Route(int startField, int endField) {
			this.start = startField;
			this.end = endField;
		}

		@Override
		public String toString() {
			return "Route [start=" + start + ", end=" + end + "]";
		}
	}
}