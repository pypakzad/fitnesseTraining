package test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import game.Game;
import game.map.Map;

public class GameTest {
	@Test
	public void hasInstanceOfMap() {
		Game newGame = new Game();
		String[] args = null;
		System.setIn(new ByteArrayInputStream(new String("y").getBytes(StandardCharsets.UTF_8)));
		newGame.main(args);
		Map map = newGame.getMapInstance();
		assertTrue(map != null);
	}

}
