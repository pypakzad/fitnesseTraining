package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
		String userInput = scanner.nextLine();
		while (!userInput.equals("y") && !userInput.equals("n")) {
			System.out.println(errorInput);
			System.out.println(welcome);
			userInput = scanner.nextLine();
		}
		if (userInput.equals("y")) {
			createMap();
			createPlayer();
		}
	}

	public MapInter mapInterface;
	private static Map map;
	private static HashMap<Cavern, String> caverns;
	private static Player player;

	public static Player getPlayer() {
		return player;
	}

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

	public static Map getMap() {
		return map;
	};

	public Game(MapInter m) {
		this.mapInterface = m;
	}

	public MapInter getMapInterface() {
		return this.mapInterface;
	}

	private static Cavern getStartingLocation() {
		ArrayList<Cavern> emptyCaverns = map.getEmptyCaverns();
		ArrayList<Cavern> possibleStartingPositions = new ArrayList<Cavern>();
		for (Cavern cavern : emptyCaverns) {
			ArrayList<Cavern> neighbors = map.getNeighbors(cavern);
			for (Cavern neighbor : neighbors) {
				if (emptyCaverns.contains(neighbor)
						|| (!emptyCaverns.contains(neighbor) && !caverns.containsKey(neighbor))) {
					possibleStartingPositions.add(cavern);
				}
			}
		}
		Random picker = new Random(System.currentTimeMillis());
		return possibleStartingPositions.get(picker.nextInt(possibleStartingPositions.size()));
	}

	private static void createMap() {
		map = new Map(50, 5, 5, 1);
		caverns = map.getCaverns();
	}

	private static void createPlayer() {
		player = new Player();
		Cavern startingLocation = getStartingLocation();
		player.setPlayerLocation(startingLocation);
	}

	public void playerDies() {
		throw new Error("You are Dead");
	}

	public void move() {
		// TODO: return
	};

}
