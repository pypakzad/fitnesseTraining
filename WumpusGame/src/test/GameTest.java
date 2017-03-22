package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Game;
import game.map.Map;

public class GameTest {

	@Test
	public void hasInstanceOfMap() {
		Game newGame = new Game();
		Map map = newGame.getMapInstance();
		assertTrue(map != null);
	}

}
