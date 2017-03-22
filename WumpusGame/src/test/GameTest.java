package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.Game;
import game.map.Map;
import game.map.MapInter;

public class GameTest {

	@Test
	public void hasInstanceOfMap() {
		Game newGame = new Game();
		MapInter map = newGame.getMap();
		assertTrue(map != null);
	}

}
