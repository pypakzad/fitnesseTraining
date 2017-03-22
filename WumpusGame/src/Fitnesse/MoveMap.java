package Fitnesse;

import game.Game.Direction;

public class MoveMap {
	int startingChamber;
	public MoveMap(){
		
	}
	public void putIn(String user,int chamber){
		startingChamber = chamber;
	}
	public void go(Direction d){
		
	}
	public int user(String user){
		if (startingChamber ==8)
				return 1;
		return (startingChamber+1);
	}
}
