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
import game.map.Movement;
import game.player.Player;

public class Game {

	public MapInter mapInterface;
	public static Map map;
	public static HashMap<Cavern, String> caverns;
	public static Player player;
	public static ArrayList<String> commands = new ArrayList<String>();
	public static ArrayList<String> eventList = new ArrayList<String>();
	public static boolean testMapAndPlayerLoaded = false;

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
			if (!testMapAndPlayerLoaded) {
				createMap();
				populateCaverns();
				createPlayer();
			}

			boolean playerDeadOrWon = false;
			Commands[] commands = Commands.values();
			ArrayList<String> commandStrings = new ArrayList<String>();
			for (Commands command : commands) {
				commandStrings.add(command.getUserInput());
			}
			System.out.println(sendRules());
			while (!playerDeadOrWon) {
				String userInput = scanner.nextLine();
				System.out.println(userInput + " is the User Input");
				while (commandStrings.contains(userInput)) {
					// only exit this loop for incorrect input or end condition
					int commandNumber = commandStrings.indexOf(userInput);
					Commands command = commands[commandNumber];
					if (commandNumber < 4) {
						Movement m = playerCavernMove(command);
						System.out.println(m.message);
						if (m.hazardSense != null)
							System.out.println(m.hazardSense);
						System.out.print(player.getPlayerLocation().getX() + ",");
						System.out.println(player.getPlayerLocation().getY());
					}
					if (command.toString().equals("up")) {
						playerDeadOrWon = shootArrow(command);
						break;
					}
					if (userInput.equals("testExit")) {
						playerDeadOrWon = true;
						break;
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

	public static void setMap(Map map) {
		Game.map = map;
	}

	public static void setPlayer(Player player) {
		Game.player = player;
	}

	public static ArrayList<String> getEventList() {
		return eventList;
	}

	public static void populateCaverns() {
		caverns = map.getCaverns();
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

	public static Movement playerCavernMove(Commands direction) {
		Movement m = new Movement();
		Cavern startingCavern = player.getPlayerLocation();
		int playerX = startingCavern.getX();
		int playerY = startingCavern.getY();
		switch (direction) {
		case w:
			playerY = playerY + 1;
			m.message = "North.";
			break;
		case s:
			playerY = playerY - 1;
			m.message = "South.";
			break;
		case d:
			playerX = playerX + 1;
			m.message = "East.";
			break;
		case a:
			playerX = playerX - 1;
			m.message = "West.";
			break;
		}
		Cavern endingCavern = map.new Cavern(playerX, playerY);
		String endingCavernType = caverns.get(endingCavern);
		if (endingCavernType == null) {
			player.setPlayerLocation(startingCavern);
			m.message = "User cannot move " + m.message;
			return m;
		}
		player.setPlayerLocation(endingCavern);
		m.message = "User moved " + m.message;

		return senseDanger(m, endingCavern);
	}

	public static Movement senseDanger(Movement m, Cavern endingCavern) {
		ArrayList<Cavern> cavernNeighbors = map.getNeighbors(endingCavern);
		String hazardSense;
		String[] hazard = new String[3];
		for (Cavern neighbor : cavernNeighbors) {
			hazardSense = null;
			String neighborType = caverns.get(neighbor);
			if (neighborType != "Empty") {
				if (neighborType == "Pit")
					hazard[0] = "You can feel a Breeze.";
				if (neighborType == "Bats")
					hazard[1] = "You can hear Chirping.";
				if (neighborType == "Wumpus")
					hazard[2] = "You can smell a Wumpus.";
			}
			// if (m.hazardSense == null)
			// {
			// if (hazardSense != null)
			// m.hazardSense = hazardSense;
			// }
			// else{
			// if (hazardSense != null)
			// m.hazardSense = m.hazardSense + hazardSense;
			// }

		}
		for (int i = 0; i < hazard.length; i++) {
			if (hazard[i] != null) {
				if (m.hazardSense == null) {
					m.hazardSense = hazard[i];
				} else {
					m.hazardSense = m.hazardSense + "\n" + hazard[i];
				}
			}
		}
		return m;
	}

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

	private static boolean arrowMove(Arrow shotArrow, Commands direction) {
		Movement m = new Movement();
		Cavern startingCavern = shotArrow.getLocation();
		int playerX = startingCavern.getX();
		int playerY = startingCavern.getY();
		switch (direction) {
		case up:
			playerY = playerY + 1;
			m.message = "North.";
			break;
		case dwn:
			playerY = playerY - 1;
			m.message = "South.";
			break;
		case rt:
			playerX = playerX + 1;
			m.message = "East.";
			break;
		case lft:
			playerX = playerX - 1;
			m.message = "West.";
			break;
		}
		Cavern endingCavern = map.new Cavern(playerX, playerY);
		String endingCavernType = caverns.get(endingCavern);
		if (endingCavernType == null) {
			shotArrow.setLocation(startingCavern);
			if (shotArrow.getLocation().equals(player.getPlayerLocation())) {
				eventList.add("Arrow rebounded and hit you :(");
				return true;
			}
			String cavernType = caverns.get(startingCavern);
			System.out.println(caverns.size());
			if (cavernType.equals("Empty")) {
				caverns.replace(startingCavern, "Arrow|1|");
				eventList.add("Your arrow ended up in an empty cavern");
				return false;
			}
			if (cavernType.length() > 4 && cavernType.substring(0, 5).equals("Arrow")) {
				String arrows = getArrowsString(startingCavern);
				caverns.replace(startingCavern, arrows);
				eventList.add("Dead end, arrow falls. Pick up arrow to reclaim.");
				return false;
			}
			if (cavernType.equals("Bats")) {
				caverns.replace(startingCavern, "Arrow|1|");
				eventList.add("Your arrow killed some bats! :(");
				return false;
			}
			if (cavernType.equals("Pit")) {
				eventList.add("Your arrow was lost in a pit :(");
				return false;
			}
			if (cavernType.equals("Wumpus")) {
				eventList.add("You killed the Wumpus!");
				return true;
			}
		}
		shotArrow.setLocation(endingCavern);
		return arrowMove(shotArrow, direction);
	}

	private static String getArrowsString(Cavern cavern) {
		String currentState = caverns.get(cavern);
		int arrowNumber = Integer.valueOf(currentState.substring(6, 7));
		return "Arrow|" + String.valueOf(arrowNumber) + "|";
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public static boolean shootArrow(Commands direction) {
		Arrow shotArrow = loseArrow();
		if (shotArrow == null) {
			eventList.add("No Usable Arrows Available :(");
			return false;
		}
		Cavern location = player.getPlayerLocation();
		shotArrow.setLocation(location);
		return arrowMove(shotArrow, direction);
	}

	private static Arrow loseArrow() {
		ArrayList<Arrow> arrowArrayCopy = player.getArrowArray();
		Arrow unusableArrow = new Arrow(false);
		for (int i = 0; i < arrowArrayCopy.size(); i++) {
			if (arrowArrayCopy.get(i).canUseArrow()) {
				Arrow shotArrow = arrowArrayCopy.get(i);
				arrowArrayCopy.set(i, unusableArrow);
				player.updateArrowArray(arrowArrayCopy);
				return shotArrow;
			}
		}
		return null;
	}

	public static String pickupArrow(Arrow foundArrow) {
		String returnMessage = "";
		ArrayList<Arrow> arrowArrayCopy = player.getArrowArray();
		for (int i = 0; i < arrowArrayCopy.size(); i++) {
			Arrow selectedArrow = arrowArrayCopy.get(i);
			if (!selectedArrow.canUseArrow() && (player.getPlayerLocation() == foundArrow.getLocation())) {
				arrowArrayCopy.set(i, foundArrow);
				returnMessage = "Congrats, you found one of your arrows.";
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
