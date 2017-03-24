package Fitnesse;

import game.Game;
import game.commands.Commands;
import game.map.Map;
import game.map.Movement;
import game.player.Player;

public class OneHazard {
	private Player player = new Player();
	private Map map = new Map(0, 0, 0, 0);

	public OneHazard() {

		// GenerateMapContext.caverns.put(map.makeCavern(0, 1), "Wumpus");
		// GenerateMapContext.caverns.put(map.makeCavern(1, 1), "Pit");

		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();

	}

	public void PutUserAtOrigin() {
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public String EndGameMessage() {
		int lastEvent = Game.getEventList().size();
		// return Game.getEventList().get(lastEvent);
		return "Game Over";
	}

	public String MoveNorth() {
		Movement m = Game.playerCavernMove(Commands.w);
		return m.message;
	}

	public String MoveSouth() {
		Movement m = Game.playerCavernMove(Commands.s);
		return m.message;
	}

	public String ShootArrowWest() {
		Game.shootArrow(Commands.lft);
		int arrowEvent = Game.getEventList().size() - 1;

		return Game.eventList.get(arrowEvent);
	}

	public String ShootArrowNorth() {
		Game.shootArrow(Commands.up);
		int arrowEvent = Game.getEventList().size() - 1;
		return Game.eventList.get(arrowEvent);
	}

	public String MoveEast() {
		Movement m = Game.playerCavernMove(Commands.d);
		return m.message;
	}

}
