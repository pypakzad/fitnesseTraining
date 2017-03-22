package Fitnesse;

import java.util.ArrayList;
import java.util.List;

import game.Game.Direction;

public class InputMap {
	int[][] inputMap;
	int start;
	int end;
	int direction;
	public void setStart(int start){
	this.start=start-1;
	}
	public void setEnd(int end){
		this.end=end-1;
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
		HuntTheWumpusContext.game.map.connect(start, end, direction);
	}
}
