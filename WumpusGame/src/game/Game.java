package game;

import java.util.Scanner;

import game.map.Map;
import game.map.Map.Cavern;
import game.map.MapInter;
import game.player.Player;

public class Game {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String welcome = "";
		String errorInput = "";

		System.out.println(welcome);
		while (!scanner.nextLine().equals("y")) {
			System.out.println(errorInput);
		}

		map = new Map(50, 5, 5, 1);
	}

	public MapInter mapInterface;
	private static Map map;
	private Player player;

	public class Movement {
		public int endingChamber;
		public String message;
	}

	public enum Direction {
		N, S, E, W
	};

	public Movement playerMove(int startingChamber, Direction direction) throws Exception {
		validateMove(direction);
		Movement m = new Movement();
		int d = 0;
		switch (direction) {
		case N:
			d = 0;
			m.message = "North.";
			break;
		case E:
			d = 1;
			m.message = "East.";
			break;
		case S:
			d = 2;
			m.message = "South.";
			break;
		case W:
			d = 3;
			m.message = "West.";
			break;
		}

		mapInterface.checkMap(startingChamber, d);
		try {

			m.endingChamber = mapInterface.moveOnMap(startingChamber, d);
			m.message = "User moved " + m.message;
			return m;
		} catch (Exception e) {
			m.endingChamber = startingChamber + 1;
			m.message = "User cannot move " + m.message;
			return m;
		}
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public Game() {
		// this.map = new Map(50);
	}

	public Map getMapInstance() {
		return this.map;
	};

	public Game(MapInter m) {
		this.mapInterface = m;
	}

	public MapInter getMap() {
		return this.mapInterface;
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

	public void playerDies() {
		throw new Error("You are Dead");
	}

	public void move() {
		// TODO: return
	};

}
