package test;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import game.Game;
import game.map.Map;
import game.player.Player;

public class ShootArrowTest {

	@Test
	public void shootingArrowIntoWall() throws Exception {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest3.txt"));
		Game.setMap(new Map(1, 0, 0, 0));
		Map map = Game.getMap();
		Game.populateCaverns();
		Player player2 = new Player();
		player2.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player2);
		Game.testMapAndPlayerLoaded = true;
		Game.main(args);
		assertTrue(Game.getEventList().get(0).equals("Arrow rebounded. User dies."));
	}

}
