package game;

import game.map.Map;
import game.map.Map.Cavern;
import game.map.MapInter;
import game.player.Player;

public class Game {
	public MapInter map;
	private Player player;

	public enum Direction {
		N, S, E, W
	};

	public int playerMove(int startingChamber, Direction direction) throws Exception {
		validateMove(direction);
		int d = 0;
		switch(direction){
		case N:d = 0;
		break;
		case E:d=1;
		break;
		case S: d=2;
		break;
		case W: d=3;
		break;
		}
		try{
			map.checkMap(startingChamber, d);
		return map.moveOnMap(startingChamber, d);
		}catch(Exception e){
			return startingChamber;
		}
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public Game() {
//		this.map = new Map(50);
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
