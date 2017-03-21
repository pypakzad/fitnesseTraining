package test.game.arrow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.arrow.Arrow;
import game.map.Map;
import game.map.Map.Cavern;

public class ArrowTest {

	@Test
	public void newArrowIsAvailable() {
		Arrow newArrow = new Arrow();
		assertTrue(newArrow.canUseArrow());
	}

	@Test
	public void setArrowToUnusable() {
		Arrow newArrow = new Arrow();
		newArrow.setArrowStatus(false);
		assertFalse(newArrow.canUseArrow());
	}

	@Test
	public void setArrowLocation() {
		Arrow newArrow = new Arrow();
		Map map = new Map(10);
		ArrayList<Cavern> caverns = map.getCaverns();
		Map.Cavern startingCavern = caverns.get(0);
		newArrow.setLocation(startingCavern);
		assertTrue(startingCavern == newArrow.getLocation());
	}

	@Test
	public void atStartOfGamePlayerHasArrow() {
		Arrow newArrow = new Arrow();
		assertTrue(newArrow.canUseArrow());
	}

}
