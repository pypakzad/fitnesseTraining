package Fitnesse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import game.Game;
import game.arrow.Arrow;
import game.commands.Commands;
import game.map.Map;
import game.map.Movement;
import game.player.Player;

public class TimeToDie {
	Map map = new Map(0,0,0,0);
	
	public TimeToDie(){
		map.setMap(GenerateMapContext.caverns);
		Game.setMap(map);
		Game.populateCaverns();
	}
	
	public void putAt(Player player, int x, int y){
		
	}

}
