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

		// GenerateMapContext.caverns.put(map.makeCavern(0, -2), "Bats");
		// GenerateMapContext.caverns.put(map.makeCavern(0, -1), "Empty");

		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();

	}

	public void PutUserAtOrigin() {
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public String MoveNorth() {
		Movement m = Game.playerCavernMove(Commands.w);
		return m.hazardSense;
	}

	public String MoveSouth() {
		Movement m = Game.playerCavernMove(Commands.s);
		m = Game.senseDanger(m, player.getPlayerLocation());
		return m.hazardSense;
	}

	public String MoveWest() {
		Movement m = Game.playerCavernMove(Commands.a);
		return m.hazardSense;
	}

	public String MoveEast() {
		Movement m = Game.playerCavernMove(Commands.d);
		m = Game.senseDanger(m, player.getPlayerLocation());
		return m.hazardSense;
	}

}
