package game;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game.Direction;
import game.map.Map;
import game.map.MockMap;
import game.map.MockMap.MockMapType;

public class MoveInMapTest {

	@Test
	public void NullPlayerMovementThrowsException() {
		Direction move = null;
		Game g = new Game(1);
		try{
			g.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Input Null Move")
				fail("Wrong Exception Thrown");	
		}
	}
	@Test
	public void PlayerHitsObjectThrowsException(){
		Direction move = Direction.N;
		MockMap m = new MockMap(MockMapType.Wall);
		Game g = new Game((Map) m);
		try{
			for(int i=0;i<100;i++)
			g.playerMove(move);
			fail("No Exception Thrown");
		}catch(Exception e){
		}
	}

}
