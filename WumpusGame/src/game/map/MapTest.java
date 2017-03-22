package game.map;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;

import org.junit.Test;

import game.map.Map.Cavern;
import game.map.Map.Cavern;

public class MapTest {

	Map map;

	int mapsize;

	@Test
	public void checkMapExists() {
		mapsize = 1;
		map = new Map(mapsize);
		HashMap<Cavern, Cavern> caverns = map.getCaverns();
		assertFalse(caverns.isEmpty());
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
