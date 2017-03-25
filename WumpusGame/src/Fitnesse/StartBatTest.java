package Fitnesse;

import java.io.FileInputStream;
import java.util.HashMap;

import game.Game;
import game.map.Cavern;
import game.map.Map;
import game.player.Player;

public class StartBatTest {
	private Map map;
	private Player player;
	private Cavern origin;
	private HashMap<Cavern, String> caverns;

	public StartBatTest() throws Exception {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest.txt"));
		Game.main(args);
		player = Game.getPlayer();
		initializeMap();
		player.setPlayerLocation(origin);
	}

	public void initializeMap() {
		caverns = new HashMap<Cavern, String>();
		origin = new Cavern(0, 0);
		caverns.put(origin, "");
	}

	public String checkUserInit() {
		if (player.getPlayerLocation() == origin) {
			return "true";
		} else
			return "false";
	}

}
