package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import game.arrow.Arrow;
import game.map.Map;
import game.map.Map.Cavern;
import game.map.Map.Coordinate;

public class ArrowTest {
	private Arrow newArrow;
	private Map.Coordinate startingCavern;

	@Before
	public void init() {
		newArrow = null;
		Map map = new Map(10);
		HashMap<Coordinate, Cavern> caverns = map.getCaverns();
		// startingCavern = caverns.get(0);
		// newArrow = new Arrow(startingCavern);
	}

	@Test
	public void newArrowIsAvailable() {
		assertTrue(newArrow.canUseArrow());
	}

	@Test
	public void setArrowToUnusable() {
		newArrow.setArrowStatus(false);
		assertFalse(newArrow.canUseArrow());
	}

	@Test
	public void setArrowLocation() {
		newArrow.setLocation(startingCavern);
		assertTrue(startingCavern == newArrow.getLocation());
	}

	@Test
	public void atStartOfGamePlayerHasArrow() {
		assertTrue(newArrow.canUseArrow());
	}

}