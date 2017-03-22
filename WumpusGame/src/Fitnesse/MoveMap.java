package Fitnesse;

import java.util.ArrayList;
import java.util.List;

import game.Game.Direction;
import game.Game.Movement;

public class MoveMap {
	int startingChamber;
	int endingChamber;
	String message;
	public MoveMap(){
		
	}
	public void putIn(String user,int chamber){
		startingChamber = chamber-1;
	}
	public void go(Direction d) throws Exception {
		Movement m = HuntTheWumpusContext.game.playerMove(startingChamber,d);
			endingChamber = m.endingChamber;
			message = m.message;
		//map.moveOnMap(startingChamber, direction);
	}
	public int user(String user){
		return endingChamber;
	}
	public String message(){
		return message;
	}
}
