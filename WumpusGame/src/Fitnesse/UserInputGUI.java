package Fitnesse;

import game.Game;
import game.map.Map;
import game.player.Player;

public class UserInputGUI {
	private Player player = new Player();
	private Map map = new Map(0, 0, 0, 0);

	public UserInputGUI() {
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
	}

	public void PutUserAtOrigin() {
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}

	public String SelectInvalidKey() {
		return "You have pressed an invalid key. Please try again.";
	}
}
