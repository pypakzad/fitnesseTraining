package Fitnesse;

import java.util.ArrayList;

import game.Game;
import game.commands.Commands;
import game.map.Map;
import game.map.Movement;
import game.player.Player;

public class PitTest {

	private Player player = new Player();
	private Map map = new Map(0, 0, 0, 0);

	public PitTest() {
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public void goFromAt(String direction, int x, int y) {
		player.setPlayerLocation(map.makeCavern(x, y));
		Commands command = Commands.valueOf(direction);
		Movement m = Game.playerCavernMove(command);
		Game.senseDanger(m, player.getPlayerLocation());
	}

	public String event() {
		ArrayList<String> eventList = Game.getEventList();
		String testOutput = eventList.get(eventList.size() - 1);
		eventList.remove(eventList.size() - 1);
		return testOutput;
		// return player.getPlayerLocation().getX() + ", " +
		// player.getPlayerLocation().getY();
	}

}