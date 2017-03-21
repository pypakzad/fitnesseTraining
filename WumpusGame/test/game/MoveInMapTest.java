package game;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game.Direction;

public class MoveInMapTest {

	@Test
	public void NullPlayerMovementThrowsException() {
		Direction move = null;
		Game g = new Game();
		try{
			g.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Input Null Move")
				fail("Wrong Exception Thrown");	
		}
	}
	@Test
	public void PlayerHitsNorthWallThrowsException(){
		Direction move = Direction.N;
		Game g = new Game();
		try{
			for(int i=0;i<100;i++)
			g.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Wall")
				fail("Wrong Exception Thrown");
		}
	}
	@Test
	public void PlayerHitsNorthBatsThrowsException(){
		Direction move = Direction.N;
		Game g = new Game();
		try{
			g.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Bats!!!!!")
				fail("Wrong Exception Thrown");
		}
	}

}
