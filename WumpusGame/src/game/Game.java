package game;

import game.map.Map;

public class Game {
	private Map map;

	public Game() {
		this.map = new Map();
	}

	public int movePlayer() {
		return 0;
	}

	public Map getMap() {
		return this.map;
	}

}
