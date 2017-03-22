package Fitnesse;

import java.util.ArrayList;
import java.util.List;

import game.Game.Direction;

public class InputMap {
	int[][] inputMap;
	int start;
	int end;
	int d;
	public void setStart(int start){
	this.start=start;
	}
	public void setEnd(int end){
		this.end=end;
	}
	public void setDirection(Direction d){
		int direction = 0;
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
		this.d=direction;
	}
	public void run(){
		
	}
}
