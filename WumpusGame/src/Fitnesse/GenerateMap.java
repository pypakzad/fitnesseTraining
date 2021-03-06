package Fitnesse;

import java.util.ArrayList;

import game.map.Cavern;
import game.map.Map;

public class GenerateMap {

	Map map = new Map();
	Cavern currentCavern;
	String currentState;

	public GenerateMap() {
		Cavern origin = map.makeCavern(0, 0);
		currentCavern = origin;
		GenerateMapContext.caverns.put(currentCavern, "Empty");
	}

	public void makeMap(String state, char direction) {
		currentState = state;
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
		currentCavern = newCavern;
		if (currentState.equals("Arrow")) {
			GenerateMapContext.caverns.put(currentCavern, "Arrow|1|");
		} else {
			GenerateMapContext.caverns.put(currentCavern, currentState);
		}
	}

}
