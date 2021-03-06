package game.player;

import java.util.ArrayList;

import game.arrow.Arrow;
import game.map.Cavern;

public class Player {
	private Cavern location;
	private ArrayList<Arrow> arrows;

	public Player() {
		this.initializeArrows();
	}

	public void setPlayerLocation(Cavern location) {
		this.location = location;
		for (Arrow arrow : arrows) {
			if (arrow.canUseArrow()) {
				arrow.setLocation(location);
			}
		}
	}

	public Cavern getPlayerLocation() {
		return this.location;
	}

	public void updateArrowArray(ArrayList<Arrow> arrows) {
		this.arrows = arrows;
	}

	private void initializeArrows() {
		this.arrows = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			this.arrows.add(new Arrow());
		}
	}

	public ArrayList<Arrow> getArrowArray() {
		return this.arrows;
	}

	public int getNumberOfAvailableArrows() {
		int availableArrowCount = 0;
		for (Arrow arrow : arrows) {
			if (arrow.canUseArrow())
				availableArrowCount++;
		}

		return availableArrowCount;
	}

}
