package game;

import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class Game {
	private Map map;
	private Player player;

	public Game() {
		this.map = new Map(50);
	}

	public Map getMap() {
		return this.map;
	}

	public Cavern getStartingLocation() {
		// TODO:implement method to return starting location
		return null;
	}

	public void createPlayer() {
		this.player = new Player();
		Cavern startingLocation = getStartingLocation();
		this.player.setPlayerLocation(startingLocation);
	}

	public void move() {
		// TODO: return
	};

}
