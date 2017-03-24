package Fitnesse;

import java.util.ArrayList;

import game.Game;
import game.bat.Bat;
import game.commands.Commands;
import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class BatTest {
	private Player player = new Player();
	private Map map = new Map(0, 0, 0, 0);
	private Bat bat;

	public BatTest() {
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public void setBat(int x, int y) {
		bat = new Bat(map.makeCavern(x, y));
		Game.setBat(bat);
	}

	public void goFromAt(String direction, int x, int y) {
		player.setPlayerLocation(map.makeCavern(x, y));
		Commands command = Commands.valueOf(direction);
		Game.playerCavernMove(command);
	}

	public boolean playerInAt(int x, int y) {
		Cavern cavern = map.makeCavern(x, y);
		return cavern.equals(Game.getPlayer().getPlayerLocation());
	}

	public String event() {
		ArrayList<String> eventList = Game.getEventList();
		String testOutput = eventList.get(eventList.size() - 1);
		eventList.remove(eventList.size() - 1);
		return testOutput;
	}

}
