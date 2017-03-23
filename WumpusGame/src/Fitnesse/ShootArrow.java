package Fitnesse;

import java.util.ArrayList;

import game.Game;
import game.commands.Commands;
import game.map.Map;
import game.player.Player;

public class ShootArrow {

	Map map = new Map(0, 0, 0, 0);

	public ShootArrow() {
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
	}

	public void shootFromAt(String arrowKey, int x, int y) {
		Player player = new Player();
		player.setPlayerLocation(map.makeCavern(x, y));
		Game.setPlayer(player);
		Commands command = Commands.valueOf(arrowKey);
		Game.shootArrow(command);
	}

	public int arrowCount() {
		Player player = Game.getPlayer();
		return player.getNumberOfAvailableArrows();
	}

	public String event() {
		ArrayList<String> eventList = Game.getEventList();
		String testOutput = eventList.get(eventList.size() - 1);
		eventList.remove(eventList.size() - 1);
		return testOutput;
	}

}
