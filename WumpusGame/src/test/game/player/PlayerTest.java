package test.game.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.player.Player;

public class PlayerTest {

	@Test
	public void playerIsNotNullWhenCreated() {
		Player player = new Player();
		assertNotNull(player);
	}

	@Test
	public void playerStartsWithFiveArrows() {
		Player player = new Player();
		int numberOfArrows = player.getNumberOfAvailableArrows();
		assertTrue(numberOfArrows == 5);
	}

}
