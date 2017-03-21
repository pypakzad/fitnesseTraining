package game.map;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MapTest {

	Map map;
	
	int mapsize;
	
	@Test
	public void checkMapExists() {
		mapsize = 1;
		map = new Map(mapsize);
		ArrayList<Map.Cavern> caverns = map.getCaverns();
		assertFalse(caverns.isEmpty());
	}
	
	@Test
	public void checkMapConnectionsWithTwoCaverns() {
		mapsize = 2;
		map = new Map(mapsize);
		ArrayList<Map.Cavern> caverns = map.getCaverns();
		Map.Cavern cavern = caverns.get(1);
		assertTrue(cavern.getCavern('N') == caverns.get(0) || cavern.getCavern('W') == caverns.get(0) || cavern.getCavern('S') == caverns.get(0) || cavern.getCavern('E') == caverns.get(0));
	}
	
	@Test
	public void checkMapConnectionsWithFiveCaverns() {
		mapsize = 5;
		map = new Map(mapsize);
		ArrayList<Map.Cavern> caverns = map.getCaverns();
		for (Map.Cavern cavern : caverns) {
			assertTrue(cavern.getCavern('N') != null || cavern.getCavern('E') != null || cavern.getCavern('S') != null || cavern.getCavern('W') != null);
		}
	}

}
