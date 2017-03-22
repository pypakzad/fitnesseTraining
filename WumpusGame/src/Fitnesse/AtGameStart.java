package Fitnesse;

import java.util.HashMap;

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
		player = new Player();
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
