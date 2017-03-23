package Fitnesse;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import game.Game;
import game.arrow.Arrow;
import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class AtGameStart {
	private Player player;
	private Map map;
	private Cavern northCavern;
	private Cavern centralCavern;
	private Cavern southCavern;
	private Cavern westCavern;
	private Cavern eastCavern;

	private HashMap<Cavern, String> caverns;

	public AtGameStart() {
		String[] args = null;
		System.setIn(new ByteArrayInputStream(new String("y").getBytes(StandardCharsets.UTF_8)));
		Game.main(args);
		player = Game.getPlayer();
		initializeCaverns();
		player.setPlayerLocation(centralCavern);
	}

	public void initializeCaverns() {
		caverns = new HashMap<Cavern, String>();
		map = new Map(10, 0, 0, 0);
		Cavern centralCavern = map.new Cavern(0, 0);
		Cavern eastCavern = map.new Cavern(1, 0);
		Cavern westCavern = map.new Cavern(-1, 0);
		Cavern southCavern = map.new Cavern(0, -1);
		Cavern northCavern = map.new Cavern(0, 1);
		caverns.put(centralCavern, "");
		caverns.put(eastCavern, "");
		caverns.put(westCavern, "");
		caverns.put(southCavern, "");
		caverns.put(northCavern, "");

	}

	public void UserShootsArrow() {
		try {
			// Game.shootArrow();
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

	public void UserPicksUpArrow() {
		Map map = new Map(10, 0, 0, 0);
		Cavern arrowLocation = map.new Cavern(4, 3);
		player.setPlayerLocation(arrowLocation);
		Arrow arrow = new Arrow();
		arrow.setLocation(arrowLocation);
		Game.pickupArrow(arrow);
	}

	public int getUserArrowCount() {
		return player.getNumberOfAvailableArrows();
	}

	public void MoveNorth() {
		player.setPlayerLocation(northCavern);
	}

	public void MoveSouth() {
		player.setPlayerLocation(southCavern);
	}

	public void MoveEast() {
		player.setPlayerLocation(eastCavern);
	}

	public void MoveWest() {
		player.setPlayerLocation(westCavern);
	}
}
