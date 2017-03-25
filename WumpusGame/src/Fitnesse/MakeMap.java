package Fitnesse;

import java.util.HashMap;

import game.map.Cavern;
import game.map.Map;

public class MakeMap {
	Map map;
	HashMap<Cavern, String> caverns;

	public MakeMap() {
		map = new Map(50, 5, 5, 1);
		caverns = map.getCaverns();
	}

	public int size() {
		return caverns.size();
	}

	public int bats() {
		int bats = 0;
		for (String state : caverns.values()) {
			if (state.equals("Bats")) {
				bats++;
			}
		}
		return bats;
	}

	public int pits() {
		int pits = 0;
		for (String state : caverns.values()) {
			if (state.equals("Pit")) {
				pits++;
			}
		}
		return pits;
	}

	public int empties() {
		int empties = 0;
		for (String state : caverns.values()) {
			if (state.equals("Empty")) {
				empties++;
			}
		}
		return empties;
	}

	public int wumpus() {
		int wumpus = 0;
		for (String state : caverns.values()) {
			if (state.equals("Wumpus")) {
				wumpus++;
			}
		}
		return wumpus;
	}
}
