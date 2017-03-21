package game.player;

import java.util.ArrayList;

import game.arrow.Arrow;
import game.map.Map.Cavern;

public class Player {
	private Cavern location;
	private ArrayList<Arrow> arrows;

	public Player() {
		this.arrows = new ArrayList<>();

	}

	public void setPlayerLocation(Cavern location) {
		this.location = location;
	}

	public Cavern getPlayerLocation() {
		return this.location;
	}

	public int getNumberOfAvailableArrows() {
		// TODO Auto-generated method stub
		return 0;
	}

}
