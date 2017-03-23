package game.bat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import game.map.Map.Cavern;
import game.player.Player;

public class Bat {
	private Player player;
	private Cavern originalLocation;
	private HashMap<Cavern, String> caverns;

	public Bat(Player player, HashMap<Cavern, String> caverns) {
		this.player = player;
		this.originalLocation = this.player.getPlayerLocation();
		this.caverns = caverns;
	}

	public String eventMessage() {
		return "You have encountered bats! You are being flown to another location...";
	}

	public  Cavern getNewLocation() {
		Set<Cavern> keyset = this.caverns.keySet();
		int keysetLength = keyset.size();
		boolean newLocationFound = false;
		Cavern newLocation = null;
		ArrayList<Cavern> cavernList = new ArrayList<>();
		for (Cavern c : keyset) {
			cavernList.add(c);
		}

		while (!newLocationFound) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, keysetLength);
			newLocation = cavernList.get(randomNum);
			if (newLocation != this.originalLocation)
				newLocationFound = true;
		}
		return newLocation;
	}

	public Cavern getOriginalLocation() {
		return this.originalLocation;
	}

}
