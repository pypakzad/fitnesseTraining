package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.arrow.Arrow;
import game.map.Cavern;
import game.map.Map;

public class ArrowTest {
	private Arrow newArrow;
	private Cavern startingCavern;
	private Map map;

	@Before
	public void init() {
		newArrow = null;
		newArrow = new Arrow();
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
