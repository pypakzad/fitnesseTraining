package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.bat.Bat;
import game.map.Map.Cavern;

public class BatTest {
	private Bat bat;

	@Before
	public void init() {
		this.bat = new Bat();
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
		Cavern newLocation = this.bat.getNewLocation();
		Cavern playerLocation = this.bat.getOriginalLocation();
		assertNotNull(newLocation);
		assertTrue(playerLocation != newLocation);
	}

}
