package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import game.Game;
import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Map;
import game.map.Movement;
import game.player.Player;

public class TimeToDie {
	private Map map = new Map(0,0,0,0);
	private Player player = new Player();
	
	public TimeToDie(){
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
		player.setPlayerLocation(map.makeCavern(0, 0));
		Game.setPlayer(player);
	}
	
	public void movePlayer(int x, int y){
		player.setPlayerLocation(map.makeCavern(0, 1));
	}
	
	public String event(){
		ArrayList<String> eventList = Game.getEventList();
		String testOutput = eventList.get(eventList.size()-1);
		eventList.remove(eventList.size() - 1);
		return testOutput;
	}

}
