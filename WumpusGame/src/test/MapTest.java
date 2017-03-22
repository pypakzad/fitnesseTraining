package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import game.map.Map;
import game.map.Map.Cavern;

public class MapTest {

	Map map;

	int mapsize;

	@Test
	public void checkMapExists() {
		mapsize = 1;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getCaverns();
		assertFalse(caverns.isEmpty());
	}

	@Test
	public void checkFirstCavernCoordinates() {
		mapsize = 1;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getCaverns();
		Cavern firstCavern = caverns.keySet().iterator().next();
		assertEquals(firstCavern.getX(), 0);
		assertEquals(firstCavern.getY(), 0);
	}

	@Test
	public void checkFourUnvisitedCaverns() {
		mapsize = 1;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getUnvisitedCaverns();
		assertEquals(caverns.size(), 4);
	}

	@Test
	public void checkCoordinatesOfUnvisitedCaverns() {
		mapsize = 1;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getUnvisitedCaverns();
		Iterator<Cavern> icaverns = caverns.keySet().iterator();
		ArrayList<Integer> coordinateHashes = new ArrayList<Integer>();
		coordinateHashes.add(1);
		coordinateHashes.add(100);
		coordinateHashes.add(-1);
		coordinateHashes.add(-100);
		while (icaverns.hasNext()) {
			Cavern cavern = icaverns.next();
			assertTrue(coordinateHashes.contains(cavern.hashCode()));
		}
	}

	@Test
	public void checkIfTwoCaverns() {
		mapsize = 2;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getCaverns();
		assertEquals(caverns.size(), 2);
	}

	@Test
	public void checkIfFiftyCaverns() {
		mapsize = 50;
		map = new Map(mapsize, 0, 0, 0);
		HashMap<Cavern, String> caverns = map.getCaverns();
		assertEquals(caverns.size(), 50);
	}

	@Test
	public void printMap() {
		mapsize = 50;
		map = new Map(mapsize, 5, 5, 1);
		HashMap<Cavern, String> caverns = map.getCaverns();
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		for (Cavern cavern : caverns.keySet()) {
			if (cavern.getX() < minX) {
				minX = cavern.getX();
			}
			if (cavern.getX() > maxX) {
				maxX = cavern.getX();
			}
			if (cavern.getY() < minY) {
				minY = cavern.getY();
			}
			if (cavern.getY() > maxY) {
				maxY = cavern.getY();
			}
		}
		int totalRange = maxX - minX;
		int totalAmp = maxY - minY;
		ArrayList<char[]> graphicalMap = new ArrayList<char[]>();
		for (int i = 0; i < totalAmp + 1; i++) {
			char[] row = new char[totalRange + 1];
			for (char point : row) {
				point = ' ';
			}
			graphicalMap.add(row);
		}
		for (Cavern cavern : caverns.keySet()) {
			char[] row = graphicalMap.get(cavern.getY() - minY);
			row[cavern.getX() - minX] = '*';
		}
		for (int i = 0; i < graphicalMap.size(); i++) {
			System.out.println(String.valueOf(graphicalMap.get(i)));
		}
	}

	/*
	 * @Test public void checkMapConnectionsWithTwoCaverns() { mapsize = 2; map
	 * = new Map(mapsize); HashMap<Coordinate, Cavern> caverns =
	 * map.getCaverns(); Iterator<Coordinate> coordinates =
	 * caverns.keySet().iterator(); Map.Cavern cavern =
	 * caverns.get(coordinates.next()); Map.Cavern otherCavern =
	 * caverns.get(coordinates.next());
	 * System.out.println(coordinates.hasNext()); //
	 * assertTrue(cavern.getCavern('N') == otherCavern || //
	 * cavern.getCavern('W') == otherCavern // || cavern.getCavern('S') ==
	 * otherCavern || cavern.getCavern('E') == // otherCavern);
	 * assertTrue(cavern.getCavern('N') == otherCavern); }
	 * 
	 * @Test public void checkMapConnectionsWithFiveCaverns() { mapsize = 5; map
	 * = new Map(mapsize); HashMap<Coordinate, Cavern> caverns =
	 * map.getCaverns(); for (Map.Cavern cavern : caverns.values()) {
	 * assertTrue(cavern.getCavern('N') != null || cavern.getCavern('E') != null
	 * || cavern.getCavern('S') != null || cavern.getCavern('W') != null); } }
	 */

}
