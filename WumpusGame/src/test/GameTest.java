package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Cavern;
import game.map.Map;
import game.player.Player;

public class GameTest {
	private Player player;

	@Before
	public void init() throws Exception {
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
		Game.shootArrow(Commands.up);
		int arrowCount = player.getNumberOfAvailableArrows();
		assertEquals(arrowCount, expectedArrowCount);
	}

	@Test
	public void errorThrownWhenNotEnoughArrows() {
		Game.shootArrow(Commands.up);
		Game.shootArrow(Commands.up);
		Game.shootArrow(Commands.up);
		Game.shootArrow(Commands.up);
		Game.shootArrow(Commands.up);
		Game.shootArrow(Commands.up);
		assertTrue(Game.getEventList().get(Game.getEventList().size() - 1).equals("No Usable Arrows Available :("));
	}

	@Test
	public void pickingUpArrowIncreasesAvailableArrows() throws Exception {
		Map map = new Map(10, 0, 0, 0);
		Cavern arrowLocation = new Cavern(4, 3);
		player.setPlayerLocation(arrowLocation);
		Arrow arrow = new Arrow();
		arrow.setLocation(arrowLocation);
		int arrowShotCount = 2;
		int arrowPickedUpcount = 3;
		Game.shootArrow(Commands.up);
		int afterShot = player.getNumberOfAvailableArrows();
		assertEquals(afterShot, arrowShotCount);
		Game.pickupArrow(arrow);
		int afterPickup = player.getNumberOfAvailableArrows();
		assertTrue(afterPickup == arrowPickedUpcount);
	}
}
