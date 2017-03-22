package Fitnesse;

import java.util.ArrayList;
import java.util.List;

import game.Game.Direction;

public class InputMap {
	int[][] inputMap;
	int start;
	int end;
	int direction;
//	public void setStart(int start){
//	this.start=start-1;
//	}
	public void setStart(String start){
		if (start =="Wumpus")
			this.start=8;
		if(start == "Pit")
			this.start=9;
		if(start == "Bats")
			this.start=10;
		this.start =  Integer.parseInt(start) - 1;
	}
	public void setEnd(String end){
		if (end =="Wumpus")
			this.end=8;
		if(end == "Pit")
			this.end=9;
		if(end == "Bats")
			this.end=10;
		this.end =  Integer.parseInt(end) - 1;
	}
	public void setDirection(Direction d){
		switch(d){
		case N:direction = 0;
				break;
		case E:direction = 1;
				break;
		case S:direction = 2;
				break;
		case W:direction = 3;
				break;
		}
	}
	public void execute(){
		System.out.println(String.format("connect %d, %d, %d", start, end, direction));
		HuntTheWumpusContext.game.mapInterface.connect(start, end, direction);
	}
}
