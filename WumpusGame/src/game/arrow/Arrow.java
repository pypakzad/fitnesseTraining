package game.arrow;

public class Arrow {

	private boolean isAvailable;

	public Arrow() {
		this.isAvailable = true;
	}

	public boolean canUseArrow() {
		return this.isAvailable;
	}

	public void setArrowStatus(boolean status) {
		this.isAvailable = status;
	}

}
