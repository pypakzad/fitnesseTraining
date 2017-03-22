package Fitnesse;

import game.player.Player;

public class AtGameStart {
	private Player player;

	public AtGameStart() {
		player = new Player();
	}

	public int getUserArrowCount() {
		return player.getNumberOfAvailableArrows();
	}
}
