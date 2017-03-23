package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class GooeyTest {
	private Player player;
	private Map map;
	private HashMap<Cavern, String> caverns;
//	private Cavern northCavern;
//	private Cavern centralCavern;
//	private Cavern southCavern;
//	private Cavern westCavern;
//	private Cavern eastCavern;
	
	@Before
	public void init() throws FileNotFoundException {
//		String[] args = null;
//		System.setIn(new FileInputStream("GameNorthTwice.txt"));
//		Game.main(args);
			player = new Player();//Game.getPlayer();
			initializeCaverns();
			//player.setPlayerLocation(centralCavern);
		}

		public void initializeCaverns() {
			caverns = new HashMap<Cavern, String>();
			map = new Map(10, 0, 0, 0);
			Cavern centralCavern = map.new Cavern(0, 0);
			Cavern eastCavern = map.new Cavern(1, 0);
			Cavern westCavern = map.new Cavern(-1, 0);
			Cavern southCavern = map.new Cavern(0, -1);
			Cavern northCavern = map.new Cavern(0, 1);
			caverns.put(centralCavern, "");
			caverns.put(eastCavern, "");
			caverns.put(westCavern, "");
			caverns.put(southCavern, "");
			caverns.put(northCavern, "");
			player.setPlayerLocation(centralCavern);
		}
	@Test
	public void moveNorth() throws FileNotFoundException {
		player
	}

}
