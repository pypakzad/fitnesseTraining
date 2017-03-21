package game.map;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
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
		assertTrue(cavern.getNorthCavern() == caverns.get(0));
	}
	
	@Test
	public void checkMapConnectionsWithFiveCaverns() {
		mapsize = 2;
		map = new Map(mapsize);
		ArrayList<Map.Cavern> caverns = map.getCaverns();
		Map.Cavern cavern = caverns.get(1);
		assertTrue(cavern.getNorthCavern() == caverns.get(0));
	}

}
