package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.Game.Direction;
import game.arrow.Arrow;
import game.map.Map;
import game.map.Map.Cavern;
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

	@Test
	public void hasPlayerLocation() {
		assertTrue(player.getPlayerLocation() != null);
	}

	@Test
	public void shootArrowDecreasesArrowCount() throws Exception {
		int expectedArrowCount = player.getNumberOfAvailableArrows() - 1;
		Game.shootArrow(Direction.values()[0]);
		int arrowCount = player.getNumberOfAvailableArrows();
		assertEquals(arrowCount, expectedArrowCount);
	}

	@Test(expected = Exception.class)
	public void errorThrownWhenNotEnoughArrows() throws Exception {
		Game.shootArrow(Direction.values()[0]);
		Game.shootArrow(Direction.values()[0]);
		Game.shootArrow(Direction.values()[0]);
		Game.shootArrow(Direction.values()[0]);
		Game.shootArrow(Direction.values()[0]);
		Game.shootArrow(Direction.values()[0]);
	}

	@Test
	public void pickingUpArrowIncreasesAvailableArrows() throws Exception {
		Map map = new Map(10, 0, 0, 0);
		Cavern arrowLocation = map.new Cavern(4, 3);
		player.setPlayerLocation(arrowLocation);
		Arrow arrow = new Arrow();
		arrow.setLocation(arrowLocation);
		int arrowShotCount = 4;
		int arrowPickedUpcount = 5;
		Game.shootArrow(Direction.values()[0]);
		int afterShot = player.getNumberOfAvailableArrows();
		assertTrue(afterShot == arrowShotCount);
		Game.pickupArrow(arrow);
		int afterPickup = player.getNumberOfAvailableArrows();
		assertTrue(afterPickup == arrowPickedUpcount);
	}

	@Test
	public void shootingArrowIntoWall() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest3.txt"));
		Game.main(args);
	}

}
