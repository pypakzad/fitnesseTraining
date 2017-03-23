package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.Game.Direction;
import game.commands.Commands;
import game.map.Map;
import game.map.Map.Cavern;
import game.map.Movement;
import game.player.Player;

public class GooeyTest {
	private Game game;
	
	@Before
	public void init() throws FileNotFoundException {
		game = new Game();
		game.player = new Player();
		initializeCaverns();
			
		}

		public void initializeCaverns() {
			game.caverns = new HashMap<Cavern, String>();
			game.map = new Map(10, 0, 0, 0);
			Cavern centralCavern = game.map.new Cavern(0, 0);
			Cavern eastCavern = game.map.new Cavern(1, 0);
			Cavern westCavern = game.map.new Cavern(-1, 0);
			Cavern southCavern = game.map.new Cavern(0, -1);
			Cavern northCavern = game.map.new Cavern(0, 1);
			game.caverns.put(centralCavern, "");
			game.caverns.put(eastCavern, "");
			game.caverns.put(westCavern, "");
			game.caverns.put(southCavern, "");
			game.caverns.put(northCavern, "");
			game.player.setPlayerLocation(centralCavern);
		}
	@Test
	public void moveNorth() throws FileNotFoundException {
		Movement m = Game.playerCavernMove(Commands.w);
		assertEquals(m.message,"User moved North.");
	}
	@Test
	public void moveSouth() throws FileNotFoundException {
		Movement m = Game.playerCavernMove(Commands.s);
		assertEquals(m.message,"User moved South.");
	}
	@Test
	public void moveEast() throws FileNotFoundException {
		Movement m = Game.playerCavernMove(Commands.d);
		assertEquals(m.message,"User moved East.");
	}
	@Test
	public void moveWest() throws FileNotFoundException {
		Movement m = Game.playerCavernMove(Commands.a);
		assertEquals(m.message,"User moved West.");
	}
	@Test
	public void cantMoveNorthTwice() throws FileNotFoundException {
		Movement m = Game.playerCavernMove(Commands.w);
		m = Game.playerCavernMove(Commands.w);
		assertEquals(m.message,"User cannot move North.");
	}

}
