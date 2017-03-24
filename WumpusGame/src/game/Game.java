package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import game.arrow.Arrow;
import game.bat.Bat;
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
	private static ArrayList<Bat> bat = new ArrayList<Bat>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String errorInput = "";

		System.out.println(sendWelcome());
		String userStartCommand = scanner.nextLine();
		while (!userStartCommand.equals("y") && !userStartCommand.equals("n")) {
			System.out.println("What did you mean by " + userStartCommand + "?");
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
				while (commandStrings.contains(userInput)) {
					// only exit this loop for incorrect input or end condition
					int commandNumber = commandStrings.indexOf(userInput);
					Commands command = commands[commandNumber];
					if (commandNumber < 4) {
						Movement m = playerCavernMove(command);
						System.out.println(m.message);
						if (m.onHazard == true) {
							playerDeadOrWon = true;
							break;
						}
						if (m.hazardSense != null)
							System.out.println(m.hazardSense);
					}
					if (commandNumber > 3 && (!userInput.equals("testExit"))) {
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
			scanner.close();
		}
	}

	public static String sendWelcome() {
		return "Welcome to Hunt The Wumpus!!\n\nyou wish to continue to the game (y/n)?";
	}

	public static String sendRules() {
		return "Rules for Hunt the Wumpus: \n1. You can move North (w), South (s), East (d), or West (a) using the wasd letter keys.\n"
				+ "\n"
				+ "2. Your goal is to kill the Wumpus with one of your arrows. The Wumpus walks around the caverns randomly.\n"
				+ "If the Wumpus is one cavern away from you, you can smell the Wumpus (he smells really bad...)\n"
				+ "If the Wumpus walks into you or you walk into the Wumpus, then you DIE.\n" + "\n"
				+ "3. You start with 5 arrows. You shoot them North(i), South(k), East(l), or West(j) using the ijkl letter keys.\n"
				+ "An arrow continues in the direction it was shot until it hits a wall or other object.\n"
				+ "If you walk into a cavern with a dropped arrow, you automatically pick it up. You only have 5 arrows after all.\n"
				+ "If you shoot an arrow into a wall directly next to you, you will DIE.\n" + "\n"
				+ "4. There are Bats in these caverns...\n"
				+ "If you walk into a cavern with bats, you will be flown to random location.\n"
				+ "If you shoot an arrow and it lands in a bat cavern, the arrow will kill the bats, and you can then retrieve it.\n"
				+ "If you are one cavern away from bats, you will hear screaching noises.\n" + "\n"
				+ "5. There are Pits in these caverns...\n"
				+ "If you walk into a cavern with pits, you will fall into the pit and DIE.\n"
				+ "If you shoot an arrow and it flies over a pit, the arrow will fall down the pit, and you will be unable to reclaim it.\n"
				+ "If you are one cavern away from a pit, you will hear blustering wind.\n" + "\n" + "GOOD LUCK!!!\n";
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
		m.onHazard = false;
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
		if (endingCavernType.equals("Pit")) {
			m.message = "You have fallen into a pit and died.";
			m.onHazard = true;
		}
		if (endingCavernType.equals("Bats")) {
			m.message = "You have encountered bats! You are being flown to another location...";
			Bat tempBat = getBat();
			eventList.add(m.message);
			return playerTeleport(tempBat.getNewLocation());
		}
		if (endingCavernType.equals("Wumpus")) {
			m.message = "You have been trampled by the Wumpus... Whomp, whomp :(";
			m.onHazard = true;
		}
		if (endingCavernType.length() > 4 && endingCavernType.substring(0, 5).equals("Arrow")) {
			int arrowNumber = Integer.valueOf(endingCavernType.substring(6, 7));
			m.message = "Congrats, you have found " + arrowNumber + " of your arrows.";
			for (; arrowNumber > 0; arrowNumber--) {
				pickupArrow(new Arrow());
			}
		}
		eventList.add(m.message);
		return senseDanger(m, player.getPlayerLocation());
	}

	public static Movement playerTeleport(Cavern endingCavern) {
		Movement m = new Movement();
		m.onHazard = false;
		String endingCavernType = caverns.get(endingCavern);
		player.setPlayerLocation(endingCavern);
		if (endingCavernType.equals("Pit")) {
			m.message = "You have fallen into a pit and died.";
			eventList.add(m.message);
			m.onHazard = true;
		}
		if (endingCavernType.equals("Bats")) {
			m.message = "You have encountered bats! You are being flown to another location...";
			Bat tempBat = getBat();
			eventList.add(m.message);
			return playerTeleport(tempBat.getNewLocation());
		}
		if (endingCavernType.equals("Wumpus")) {
			m.message = "You have been trampled by the Wumpus... Whomp, whomp :(";
			eventList.add(m.message);
			m.onHazard = true;
		}
		if (endingCavernType.length() > 6 && endingCavernType.substring(0, 5).equals("Arrow")) {
			int arrowNumber = Integer.valueOf(endingCavernType.substring(6, 7));
			m.message = "Congrats, you have found " + arrowNumber + " of your arrows.";
			eventList.add(m.message);
			for (; arrowNumber > 0; arrowNumber--) {
				pickupArrow(new Arrow());
			}
		}
		return senseDanger(m, player.getPlayerLocation());
	}

	public static void setBat(Bat testBat) {
		bat.add(testBat);
	}

	private static Bat getBat() {
		if (bat.isEmpty()) {
			return new Bat(player, caverns);
		}
		return bat.remove(0);
	}

	public static Movement senseDanger(Movement m, Cavern endingCavern) {
		ArrayList<Cavern> cavernNeighbors = map.getNeighbors(endingCavern);
		String[] hazard = new String[3];
		for (Cavern neighbor : cavernNeighbors) {
			String neighborType = caverns.get(neighbor);
			if (neighborType != "Empty") {
				if (neighborType == "Pit")
					hazard[0] = "You feel blustering wind.";
				if (neighborType == "Bats")
					hazard[1] = "You hear screeching noises.";
				if (neighborType == "Wumpus")
					hazard[2] = "You smell something really bad.";
			}
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
				System.out.println("Arrow rebounded. User dies");
				eventList.add("Arrow rebounded. User dies.");
				return true;
			}
			String cavernType = caverns.get(startingCavern);
			if (cavernType.equals("Empty")) {
				caverns.replace(startingCavern, "Arrow|1|");
				System.out.println("Dead end, arrow falls. Pick up arrow to reclaim it.");
				eventList.add("Dead end, arrow falls. Pick up arrow to reclaim it.");
				return false;
			}
			if (cavernType.length() > 4 && cavernType.substring(0, 5).equals("Arrow")) {
				String arrows = getArrowsString(startingCavern);
				caverns.replace(startingCavern, arrows);
				System.out.println("Dead end, arrow falls. Pick up arrow to reclaim it.");
				eventList.add("Dead end, arrow falls. Pick up arrow to reclaim it.");
				return false;
			}
		}
		if (endingCavernType.equals("Bats")) {
			caverns.replace(endingCavern, "Arrow|1|");
			System.out.println("User killed bats. Pick-up arrow to reclaim it.");
			eventList.add("User killed bats. Pick-up arrow to reclaim it.");
			return false;
		}
		if (endingCavernType.equals("Pit")) {
			System.out.println("User lost arrow in pit.");
			eventList.add("User lost arrow in pit.");
			return false;
		}
		if (endingCavernType.equals("Wumpus")) {
			System.out.println("User killed the Wumpus.");
			eventList.add("User killed the Wumpus.");
			return true;
		}
		System.out.println("Empty cavern, arrow continues " + direction.getDirection() + ".");
		eventList.add("Empty cavern, arrow continues " + direction.getDirection() + ".");
		shotArrow.setLocation(endingCavern);
		return arrowMove(shotArrow, direction);
	}

	private static String getArrowsString(Cavern cavern) {
		String currentState = caverns.get(cavern);
		int arrowNumber = Integer.valueOf(currentState.substring(6, 7)) + 1;
		return "Arrow|" + String.valueOf(arrowNumber) + "|";
	}

	private static void validateMove(Direction d) throws Exception {
		if (d == null)
			throw new Exception("Input Null Move");
	}

	public static boolean shootArrow(Commands direction) {
		Arrow shotArrow = loseArrow();
		if (shotArrow == null) {
			System.out.println("No Usable Arrows Available :(");
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
			if (!selectedArrow.canUseArrow()) {
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
