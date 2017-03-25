package Fitnesse;

import game.Game;
import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Cavern;
import game.map.Map;
import game.player.Player;

public class AtGameStart {
	private Player player = new Player();

	private Map map = new Map(0, 0, 0, 0);

	public AtGameStart() {
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.setPlayer(player);
	}

	public void UserShootsArrow() {
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.shootArrow(Commands.up);
	}

	public void UserPicksUpArrow() {
		Map map = new Map(10, 0, 0, 0);
		Cavern arrowLocation = new Cavern(4, 3);
		player.setPlayerLocation(arrowLocation);
		Arrow arrow = new Arrow();
		arrow.setLocation(arrowLocation);
		Game.pickupArrow(arrow);
	}

	public void SetArrowCountToThree() {
		UserShootsArrow();
		UserShootsArrow();
	}

	public int getUserArrowCount() {
		return Game.getPlayer().getNumberOfAvailableArrows();
	}

	public void PlaceArrow() {
		map.getCaverns().replace(map.makeCavern(0, 0), "Arrow|1|");
	}

	public void MoveNorth() {
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.playerCavernMove(Commands.w);
	}

	public void MoveSouth() {
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.playerCavernMove(Commands.s);
	}

	public void MoveEast() {
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.playerCavernMove(Commands.d);
	}

	public void MoveWest() {
		player.setPlayerLocation(map.makeCavern(0, -1));
		Game.playerCavernMove(Commands.a);
	}
}
