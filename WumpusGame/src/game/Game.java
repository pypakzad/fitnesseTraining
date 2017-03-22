package game;

import game.map.Map;
import game.map.Map.Cavern;
import game.map.MapInter;
import game.player.Player;

public class Game {
	private MapInter map;
	private Player player;

	public enum Direction {
		N, S, E, W
	};

	public void playerMove(Direction d) throws Exception {
		validateMove(d);
		map.checkMap(0, 0);
		throw new Exception("Wall");
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public Game() {
		this.map = new Map(50);
	}

	public Game(MapInter m) {
		this.map = m;
	}

	public MapInter getMap() {
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
