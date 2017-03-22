package test;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.map.Map;
import game.player.Player;

public class GameTest {
	private Player player;

	@Before
	public void init() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest.txt"));
		Game.main(args);
		player = Game.getPlayer();
	}

	@Test
	public void hasInstanceOfMap() {
		Map map = Game.getMap();
		assertTrue(map != null);
	}
	/*
	 * @Test public void hasPlayerLocation() {
	 * assertTrue(player.getPlayerLocation() != null); }
	 * 
	 * @Test public void shootArrowDecreasesArrowCount() throws Exception { int
	 * expectedArrowCount = player.getNumberOfAvailableArrows() - 1;
	 * Game.shootArrow(); int arrowCount = player.getNumberOfAvailableArrows();
	 * assertEquals(arrowCount, expectedArrowCount); }
	 * 
	 * @Test(expected = Exception.class) public void
	 * errorThrownWhenNotEnoughArrows() throws Exception { Game.shootArrow();
	 * Game.shootArrow(); Game.shootArrow(); Game.shootArrow();
	 * Game.shootArrow(); Game.shootArrow(); }
	 */
}
