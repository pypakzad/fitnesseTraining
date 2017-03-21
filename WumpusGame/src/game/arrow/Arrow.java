package game.arrow;

import game.map.Map.Cavern;

public class Arrow {

	private boolean olayerHasArrow;
	private Cavern location;

	public Arrow() {
		this.olayerHasArrow = true;
	}

	public boolean canUseArrow() {
		return this.olayerHasArrow;
	}

	public void setArrowStatus(boolean status) {
		this.olayerHasArrow = status;
	}

	public void setLocation(Cavern cavern) {
		this.location = cavern;
	}

	public Cavern getLocation() {
		return this.location;
	}

}
