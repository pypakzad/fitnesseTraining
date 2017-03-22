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
		String[] args = null;
		System.setIn(new ByteArrayInputStream(new String("y").getBytes(StandardCharsets.UTF_8)));
		Game.main(args);
		game = new Game();
		player = new Player();
	}

	@Test
	public void hasInstanceOfMap() {
		Map map = Game.getMap();
		assertTrue(map != null);
	}

	@Test
	public void hasPlayerLocation() {
		Player player = Game.getPlayer();
		assertTrue(player.getPlayerLocation() != null);
	}

	@Test
	public void shootArrowDecreasesArrowCount() throws Exception {
		int expectedArrowCount = player.getNumberOfAvailableArrows() - 1;
		game.shootArrow();
		int arrowCount = player.getNumberOfAvailableArrows();
		assertTrue(arrowCount == expectedArrowCount);
	}

	@Test(expected = Exception.class)
	public void errorThrownWhenNotEnoughArrows() throws Exception {
		game.shootArrow();
		game.shootArrow();
		game.shootArrow();
		game.shootArrow();
		game.shootArrow();
		game.shootArrow();

	}

}
