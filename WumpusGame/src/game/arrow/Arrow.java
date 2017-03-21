package game.arrow;

import game.map.Map.Coordinate;

public class Arrow {

	private boolean playerHasArrow;
	private Coordinate location;

	public Arrow(Coordinate location) {
		this.playerHasArrow = true;
		this.location = location;
	}

	public boolean canUseArrow() {
		return this.playerHasArrow;
	}

	public void setArrowStatus(boolean status) {
		this.playerHasArrow = status;
	}

	public void setLocation(Coordinate cavern) {
		this.location = cavern;
	}

	public Coordinate getLocation() {
		return this.location;
	}

}
