package game.bat;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import game.map.Map.Cavern;
import game.player.Player;

public class Bat {
	private Player player;
	private Cavern originalLocation;
	private HashMap<Cavern, String> caverns;
	private Cavern newLocation = null;
	private boolean testBat = false;

	public Bat(Player player, HashMap<Cavern, String> caverns) {
		this.player = player;
		this.originalLocation = this.player.getPlayerLocation();
		this.caverns = caverns;
	}

	public Bat(Cavern cavern) {
		newLocation = cavern;
		testBat = true;
	}

	public String eventMessage() {
		return "You have encountered bats! You are being flown to another location...";
	}

	public Cavern getNewLocation() {
		boolean newLocationFound = false;

		while (!newLocationFound && !testBat) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, this.caverns.keySet().size());
			newLocation = (Cavern) this.caverns.keySet().toArray()[randomNum];
			if (newLocation != this.originalLocation)
				newLocationFound = true;
		}

		return newLocation;
	}

	public Cavern getOriginalLocation() {
		return this.originalLocation;
	}

}
