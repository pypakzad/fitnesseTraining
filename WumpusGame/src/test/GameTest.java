package test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.map.Map;
import game.player.Player;

public class GameTest {

	private Game game;
	private Player player;

	@Before
	public void init() {
		game = new Game();
		player = new Player();
		game.assignPlayer(player);
	}

	@Test
	public void hasInstanceOfMap() {
		Game newGame = new Game();
		String[] args = null;
		System.setIn(new ByteArrayInputStream(new String("y").getBytes(StandardCharsets.UTF_8)));
		newGame.main(args);
		Map map = newGame.getMapInstance();
		assertTrue(map != null);
	}

	@Test
	public void shootArrowDecreasesArrowCount() {
		int expectedArrowCount = player.getNumberOfAvailableArrows() - 1;
		game.shootArrow();
		int arrowCount = player.getNumberOfAvailableArrows();
		assertTrue(arrowCount == expectedArrowCount);
	}

}
