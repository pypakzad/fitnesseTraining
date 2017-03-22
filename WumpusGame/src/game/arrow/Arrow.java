package game.arrow;

import game.map.Map.Cavern;

public class Arrow {

	private boolean playerHasArrow;
	private Cavern location;

	public Arrow() {
		this.playerHasArrow = true;
	}

	public boolean canUseArrow() {
		return this.playerHasArrow;
	}

	public void setArrowStatus(boolean status) {
		this.playerHasArrow = status;
	}

	public void setLocation(Cavern cavern) {
		this.location = cavern;
	}

	public Cavern getLocation() {
		return this.location;
	}

}
