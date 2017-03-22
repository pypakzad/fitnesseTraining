package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import game.bat.Bat;

public class BatTest {
	private Bat bat;

	@Before
	public void init() {
		this.bat = new Bat();
	}

	@Test
	public void BatIsNotNullWhenCreated() {
		assertNotNull(this.bat);
	}

}
