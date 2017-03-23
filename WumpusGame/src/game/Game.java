package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Map;
import game.map.Map.Cavern;
import game.map.MapInter;
import game.player.Player;

public class Game {

	public MapInter mapInterface;
	private static Map map;
	private static HashMap<Cavern, String> caverns;
	private static Player player;
	private static ArrayList<String> commands = new ArrayList<String>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String welcome = "";
		String errorInput = "";
		String rules = "";

		System.out.println(sendWelcome());
		String userStartCommand = scanner.nextLine();
		while (!userStartCommand.equals("y") && !userStartCommand.equals("n")) {
			System.out.println(errorInput);
			System.out.println(sendWelcome());
			userStartCommand = scanner.nextLine();
		}
		if (userStartCommand.equals("y")) {
			// initialization
			createMap();
			createPlayer();
			boolean playerDeadOrWon = false;
			Commands[] commands = Commands.values();
			ArrayList<String> commandStrings = new ArrayList<String>();
			for (Commands command : commands) {
				commandStrings.add(command.getUserInput());
			}
			while (!playerDeadOrWon) {
				System.out.println(sendRules());
				String userInput = scanner.nextLine();
				while (commandStrings.contains(userInput)) {
					// only exit this loop for incorrect input or end condition
					Commands command = commands[commandStrings.indexOf(userInput)];
					if (userInput.equals("testExit")) {
						playerDeadOrWon = true;
					}
					userInput = scanner.nextLine();
				}
				if (!playerDeadOrWon) {
					System.out.println(errorInput);
				}
			}
			// if you're here game has ended

		}
	}

	public static String sendWelcome() {
		return "welcome";
	}

	public static String sendRules() {
		return "rules";
	}

	public static Player getPlayer() {
		return player;
	}

	public class Movement {
		public int endingChamber;
		public String message;
	}

	public enum Direction {
		N("North.", 0), E("East.", 1), S("South.", 2), W("West", 3);
		String direction;
		int directionTest;

		Direction(String cardinality, int testValue) {
			direction = cardinality;
			directionTest = testValue;
		}

		public String getDirection() {
			return direction;
		}

		public int getDirectionTest() {
			return directionTest;
		}
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
			System.out.println(e.getLocalizedMessage());
			// if(e.getMessage()!="Wall")
			// playerDies(e.getMessage());
			m.endingChamber = startingChamber + 1;
			m.message = "User cannot move " + m.message;
			return m;
		}
	}

	public int wumpusMove(int startingChamber, Direction direction) throws Exception {
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

			int endingChamber = mapInterface.moveOnMap(startingChamber, d);
			return endingChamber;
		} catch (Exception e) {
			int endingChamber = startingChamber + 1;
			return endingChamber;
		}
	}

	public void arrowMove(int startingChamber, Direction direction) throws Exception {
		validateMove(direction);
		Movement m = new Movement();
		int d = direction.getDirectionTest();
		m.message = direction.getDirection();

		mapInterface.checkMap(startingChamber, d);
		try {

			m.endingChamber = mapInterface.moveOnMap(startingChamber, d);
			m.message = "User moved " + m.message;
			// return m;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			// if(e.getMessage()!="Wall")
			// playerDies(e.getMessage());
			m.endingChamber = startingChamber + 1;
			m.message = "User cannot move " + m.message;
			// return m;
		}
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public static void shootArrow(Direction direction) throws Exception {
		boolean noUsableArrows = loseArrow();
		if (noUsableArrows)
			throw new Exception("No Usable Arrows Available");
		Cavern location = player.getPlayerLocation();

	}

	private static boolean loseArrow() {
		ArrayList<Arrow> arrowArrayCopy = player.getArrowArray();
		Arrow unusableArrow = new Arrow(false);
		for (int i = 0; i < arrowArrayCopy.size(); i++) {
			if (arrowArrayCopy.get(i).canUseArrow()) {
				arrowArrayCopy.set(i, unusableArrow);
				player.updateArrowArray(arrowArrayCopy);
				return false;
			}
		}
		return true;
	}

	public static String pickupArrow(Arrow foundArrow) {
		String returnMessage = "";
		ArrayList<Arrow> arrowArrayCopy = player.getArrowArray();
		for (int i = 0; i < arrowArrayCopy.size(); i++) {
			Arrow selectedArrow = arrowArrayCopy.get(i);
			if (!selectedArrow.canUseArrow() && (player.getPlayerLocation() == foundArrow.getLocation())) {
				arrowArrayCopy.set(i, foundArrow);
				returnMessage = "You have found an arrow!";
				break;
			}
		}

		player.updateArrowArray(arrowArrayCopy);
		return returnMessage;
	}

	public Game() {
		Game.map = new Map(50, 1, 1, 1);
	}

	public void assignPlayer(Player player) {
		this.player = player;
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

	public void playerDies(String message) {
		if (message != "Bats")
			throw new Error("You Died From " + message);
	}

	public void wumpusDies(String message) {
		throw new Error("You Killed Our Wumpus :(");
	}

	public void move() {
		// TODO: return
	};

}
