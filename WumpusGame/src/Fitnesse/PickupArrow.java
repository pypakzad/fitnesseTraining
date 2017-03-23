package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import game.Game;
import game.Game.Direction;
import game.arrow.Arrow;
import game.map.Map;
import game.map.Map.Cavern;
import game.player.Player;

public class PickupArrow {
	private Player player;
	private Map map;
	private Cavern northCavern;
	private Cavern centralCavern;
	private Cavern southCavern;
	private Cavern westCavern;
	private Cavern eastCavern;

	private HashMap<Cavern, String> caverns;

	public PickupArrow() throws FileNotFoundException {
		String[] args = null;
		System.setIn(new FileInputStream("GameTest.txt"));
		Game.main(args);
		player = Game.getPlayer();
		initializeCaverns();
		player.setPlayerLocation(centralCavern);
	}

	public void initializeCaverns() {
		caverns = new HashMap<Cavern, String>();
		map = new Map(10, 0, 0, 0);
		Cavern centralCavern = map.new Cavern(0, 0);
		Cavern eastCavern = map.new Cavern(1, 0);
		Cavern westCavern = map.new Cavern(-1, 0);
		Cavern southCavern = map.new Cavern(0, -1);
		Cavern northCavern = map.new Cavern(0, 1);
		caverns.put(centralCavern, "");
		caverns.put(eastCavern, "");
		caverns.put(westCavern, "");
		caverns.put(southCavern, "");
		caverns.put(northCavern, "");

	}

	public void initializeArrowPickupTest() {
		caverns = new HashMap<Cavern, String>();
		map = new Map(10, 0, 0, 0);
		centralCavern = map.new Cavern(0, 0);
		eastCavern = map.new Cavern(1, 0);
		westCavern = map.new Cavern(2, 0);
		southCavern = map.new Cavern(2, -1);
		caverns.put(centralCavern, "");
		caverns.put(eastCavern, "arrow");
		caverns.put(westCavern, "");
		caverns.put(southCavern, "arrow");
		player.setPlayerLocation(centralCavern);
	}

	public void UserShootsArrow() {
		try {
			Game.shootArrow(Direction.S);
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

	public void UserPicksUpArrow() {
		Map map = new Map(10, 0, 0, 0);
		Cavern arrowLocation = map.new Cavern(4, 3);
		player.setPlayerLocation(arrowLocation);
		Arrow arrow = new Arrow();
		arrow.setLocation(arrowLocation);
		Game.pickupArrow(arrow);
	}

	public void PutUserAtLocationOne() {
		initializeArrowPickupTest();
	}

	public void SetArrowCountToThree() {
		UserShootsArrow();
		UserShootsArrow();
	}

	public int getUserArrowCount() {
		return player.getNumberOfAvailableArrows();
	}

	public void MoveNorth() {
		player.setPlayerLocation(northCavern);
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

	public void MoveWest() {
		player.setPlayerLocation(westCavern);
	}
}
