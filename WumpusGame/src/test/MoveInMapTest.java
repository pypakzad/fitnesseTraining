package test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game;
import game.Game.Direction;
import game.map.Map;
import game.map.MapInter;
import game.map.MockMap;
import game.map.MockMap.MockMapType;

public class MoveInMapTest {

	@Test
	public void NullPlayerMovementThrowsException() {
		Direction move = null;
		int starting = 0;
		MockMap m = new MockMap(0);
		m.setMap(MockMapType.Wall);
		Game g = new Game((MapInter) m);
		try{
			g.playerMove(starting,move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Input Null Move")
				fail("Wrong Exception Thrown");	
		}
	}
	@Test
	public void PlayerHitsWallsThrowsException(){
		Direction move = Direction.N;
		int starting = 0;
		MockMap m = new MockMap(0);
		m.setMap(MockMapType.Wall);
		Game g = new Game((MapInter) m);
		try{
			g.playerMove(starting, move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Wall")
				fail("Wrong Exception Thrown");
		}
	}
	@Test
	public void PlayerHitsBatsThrowsException(){
		Direction move = Direction.N;
		MockMap m = new MockMap(0);
		int starting = 0;
		m.setMap(MockMapType.Bats);
		Game g = new Game((MapInter) m);
		try{
			g.playerMove(starting, move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Bats")
				fail("Wrong Exception Thrown");
		}
	}
	@Test
	public void PlayerHitsPitThrowsException(){
		Direction move = Direction.N;
		MockMap m = new MockMap(0);
		int starting = 0;
		m.setMap(MockMapType.Pit);
		Game g = new Game((MapInter) m);
		try{
			g.playerMove(starting, move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Pit")
				fail("Wrong Exception Thrown");
		}
	}
	@Test
	public void PlayerHitsWumpusThrowsException(){
		Direction move = Direction.N;
		MockMap m = new MockMap(0);
		int starting = 0;
		m.setMap(MockMapType.Wumpus);
		Game g = new Game((MapInter) m);
		try{
			g.playerMove(starting, move);
			fail("No Exception Thrown");
		}catch(Exception e){
			if(e.getMessage()!="Wumpus")
				fail("Wrong Exception Thrown");
		}
	}

}
