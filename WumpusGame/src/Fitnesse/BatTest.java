package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import game.Game;
import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Map;
import game.map.Map.Cavern;
import game.map.Movement;
import game.player.Player;

public class BatTest {
	private Map map;
	private Player player;
	private Cavern origin;
	private Cavern E;
	private Cavern N;
	private Cavern W;
	private Cavern S;
	private Cavern NE;
	private Cavern NW;
	private Cavern SE;
	private Cavern SW;
	private HashMap<Cavern, String> caverns;

	public BatTest() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest.txt"));
		Game.main(args);
		player = Game.getPlayer();
		initializeMap();
		player.setPlayerLocation(origin);
	}

	public void initializeMap() {
		caverns = new HashMap<Cavern, String>();
		map = new Map(10, 0, 0, 0);
		origin = map.new Cavern(0, 0);
		N = map.new Cavern(0, 1);
		E = map.new Cavern(1, 0);
		S = map.new Cavern(0, -1);
		W = map.new Cavern(-1, 0);
		NE = map.new Cavern(1, 1);
		SE = map.new Cavern(1, -1);
		SW = map.new Cavern(-1, -1);
		NW = map.new Cavern(-1, 1);

		caverns.put(origin, "");
		caverns.put(N, "");
		caverns.put(E, "Arrow");
		caverns.put(S, "");
		caverns.put(W, "");
		caverns.put(NE, "Pit");
		caverns.put(NW, "Bats");
		caverns.put(SW, "Whompus");
		caverns.put(SE, "Bats");
	}

	public String checkUserInit() {
		if (player.getPlayerLocation() == origin) {
			return "true";
		} else
			return "false";
	}

	public String MoveSouth() {
		int newX = player.getPlayerLocation().getX();
		int newY = player.getPlayerLocation().getY() - 1;
		Cavern newLocation = map.new Cavern(newX, newY);
		player.setPlayerLocation(newLocation);
		if (caverns.get(newLocation).equals("arrow")) {
			Arrow arrow = new Arrow();
			arrow.setLocation(newLocation);
			return Game.pickupArrow(arrow);
		} else
			return "";
	}

	public String MoveNorth() {
		Movement m = Game.playerCavernMove(Commands.w);
		return m.message;
	}

	public String MoveWest() {
		Movement m = Game.playerCavernMove(Commands.a);
		return m.message;
	}

	public String MoveEast() {
		int newX = player.getPlayerLocation().getX() + 1;
		int newY = player.getPlayerLocation().getY();
		Cavern newLocation = map.new Cavern(newX, newY);
		player.setPlayerLocation(newLocation);
		if (caverns.get(newLocation).equals("arrow")) {
			Arrow arrow = new Arrow();
			arrow.setLocation(newLocation);
			return Game.pickupArrow(arrow);
		} else
			return "";
	}
}
