package Fitnesse;

import java.util.ArrayList;
import java.util.HashMap;

import game.map.Map;
import game.map.Map.Cavern;

public class GenerateMap {

	Map map = new Map();
	HashMap<Cavern, String> caverns = new HashMap<Cavern, String>();
	Cavern currentCavern;

	public GenerateMap() {
		Map.Cavern origin = map.makeCavern(0, 0);
		caverns.put(origin, "Empty");
		currentCavern = origin;
	}

	public void makeMap(String state, char direction) {
		ArrayList<Cavern> neighbors = map.getNeighbors(currentCavern);
		Cavern newCavern = null;
		switch (direction) {
		case 'N':
			newCavern = neighbors.get(0);
			break;
		case 'W':
			newCavern = neighbors.get(1);
			break;
		case 'S':
			newCavern = neighbors.get(2);
			break;
		case 'E':
			newCavern = neighbors.get(3);
			break;
		}
		caverns.put(newCavern, state);
	}

}
