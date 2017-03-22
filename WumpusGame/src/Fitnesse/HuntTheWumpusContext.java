package Fitnesse;

import game.*;
import game.map.MapInter;
import game.map.MockMap;

public class HuntTheWumpusContext {
	public static Game game;
	
	public HuntTheWumpusContext() {
		MapInter map = new MockMap(8);
		game = new Game(map);
	}

}
