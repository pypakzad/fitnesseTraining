package Fitnesse;

import game.Game;
import game.map.Map;
import game.map.Movement;
import game.player.Player;

public class AllTheDanger {
	private Player player = new Player();
	private Map map = new Map(0, 0, 0, 0);

	public AllTheDanger() {

		// GenerateMapContext.caverns.put(map.makeCavern(0, 1), "Wumpus");
		// GenerateMapContext.caverns.put(map.makeCavern(1, 0), "Pit");

		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();

	}

	public void PutUserAtOrigin() {
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public String SenseDanger() {
		Movement newMove = new Movement();

		Movement m = Game.senseDanger(newMove, map.makeCavern(0, 0));
		return m.hazardSense;
	}

}
