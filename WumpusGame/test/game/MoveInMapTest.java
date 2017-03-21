package game;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game.Direction;

public class MoveInMapTest {

	@Test
	public void NullPlayerMovementThrowsException() {
		Direction move = null;
		try{
			Game.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Input Null Move")
				fail("Wrong Exception Not Thrown");	
		}
	}
//	@Test
//	public void EmptyPlayerMovementThrowsException(){
//		String move = "";
//		try{
//			Game.playerMove(move);
//			fail("No Exception Thrown");
//		}catch(Exception e){
//			if(e.getMessage()!="Input Empty Move")
//				fail("Wrong Exception Not Thrown");	
//		}
//	}
	
//	@Test
//	public void NotAcceptablePlayerMovementThrowsException(){
//		String move;
//		try{
//			move = "NE";
//			Game.playerMove(move);
//			fail("No Exception Thrown");
//		}catch(Exception e){
//			if(e.getMessage()!="Unacceptable Move")
//				fail("Wrong Exception Not Thrown");
//		}
//	}
	
//	@Test
//	public void AcceptedPlayerMovementThrowsNoException(){
//		String move;
//		try{
//			move = "N";
//			Game.acceptedMove(move);
//			move = "S";
//			Game.acceptedMove(move);
//			move = "W";
//			Game.acceptedMove(move);
//			move = "E";
//			Game.acceptedMove(move);
//		}catch(Exception e){
//				fail("Exception Thrown");	
//		}
//	}

}
