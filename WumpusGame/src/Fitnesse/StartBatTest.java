package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import game.Game;
import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class StartBatTest {
	private Map map;
	private Player player;
	private Cavern origin;
	private HashMap<Cavern, String> caverns;

	public StartBatTest() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest.txt"));
		Game.main(args);
		player = Game.getPlayer();
		initializeMap();
		player.setPlayerLocation(origin);
	}

	public void initializeMap() {
		caverns = new HashMap<Cavern, String>();
		origin = map.new Cavern(0, 0);
		caverns.put(origin, "");
	}

	public String checkUserInit() {
		if (player.getPlayerLocation() == origin) {
			return "true";
		} else
			return "false";
	}

}
