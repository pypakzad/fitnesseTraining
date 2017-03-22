package Fitnesse;

import java.util.ArrayList;
import java.util.List;

import game.Game.Direction;

public class MoveMap {
	int startingChamber;
	int endingChamber;
	public MoveMap(){
		
	}
	public void putIn(String user,int chamber){
		startingChamber = chamber-1;
	}
	public void go(Direction d) throws Exception {
			endingChamber = HuntTheWumpusContext.game.playerMove(startingChamber,d);
		//map.moveOnMap(startingChamber, direction);
	}
	public int user(String user){
		return endingChamber + 1;
	}
}
