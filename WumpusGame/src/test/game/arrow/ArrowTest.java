package test.game.arrow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.arrow.Arrow;

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

}
