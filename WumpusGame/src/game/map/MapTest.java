package game.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import game.map.Map.Cavern;
import game.map.Map.Coordinate;

public class MapTest {

	Map map;

	int mapsize;

	@Test
	public void checkMapExists() {
		mapsize = 1;
		map = new Map(mapsize);
		HashMap<Coordinate, Cavern> caverns = map.getCaverns();
		assertFalse(caverns.isEmpty());
	}

	@Test
	public void checkMapConnectionsWithTwoCaverns() {
		mapsize = 2;
		map = new Map(mapsize);
		HashMap<Coordinate, Cavern> caverns = map.getCaverns();
		Iterator<Coordinate> coordinates = caverns.keySet().iterator();
		Map.Cavern cavern = caverns.get(coordinates.next());
		Map.Cavern otherCavern = caverns.get(coordinates.next());
		// assertTrue(cavern.getCavern('N') == otherCavern ||
		// cavern.getCavern('W') == otherCavern
		// || cavern.getCavern('S') == otherCavern || cavern.getCavern('E') ==
		// otherCavern);
		assertTrue(cavern.getCavern('N') == otherCavern);
	}

	@Test
	public void checkMapConnectionsWithFiveCaverns() {
		mapsize = 5;
		map = new Map(mapsize);
		HashMap<Coordinate, Cavern> caverns = map.getCaverns();
		for (Map.Cavern cavern : caverns.values()) {
			assertTrue(cavern.getCavern('N') != null || cavern.getCavern('E') != null || cavern.getCavern('S') != null
					|| cavern.getCavern('W') != null);
		}
	}

}
