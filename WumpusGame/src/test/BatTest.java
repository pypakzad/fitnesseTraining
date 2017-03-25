package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import game.bat.Bat;
import game.map.Cavern;
import game.map.Map;
import game.player.Player;

public class BatTest {
	private Bat bat;
	private Map map;
	private Player player;
	private HashMap<Cavern, String> caverns;

	@Before
	public void init() {
		this.player = new Player();
		caverns = new HashMap<Cavern, String>();
		this.bat = new Bat(player, caverns);
	}

	@Test
	public void batIsNotNullWhenCreated() {
		assertNotNull(this.bat);
	}

	@Test
	public void batMessageIsCorrect() {
		String message = this.bat.eventMessage();
		String expectedMessage = "You have encountered bats! You are being flown to another location...";
		assertTrue(message.equals(expectedMessage));
	}

	@Test
	public void getNewPlayerLocation() {
		// map = new Map(0);
		map = new Map(10, 0, 0, 0);
		Cavern cavernOne = new Cavern(0, 0);
		Cavern cavernTwo = new Cavern(1, 0);
		Cavern cavernThree = new Cavern(2, 0);
		Cavern cavernFour = new Cavern(0, 1);
		caverns.put(cavernOne, "");
		caverns.put(cavernTwo, "");
		caverns.put(cavernThree, "");
		caverns.put(cavernFour, "");
		this.bat = new Bat(this.player, caverns);

		player.setPlayerLocation(cavernOne);

		Cavern newLocation = this.bat.getNewLocation();
		Cavern playerLocation = this.bat.getOriginalLocation();
		assertNotNull(newLocation);
		assertTrue(playerLocation != newLocation);
	}

}
