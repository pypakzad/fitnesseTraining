package game.map;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {

	private HashMap<Cavern, String> caverns = new HashMap<Cavern, String>();
	private HashMap<Cavern, String> unvisitedCaverns = new HashMap<Cavern, String>();

	public Map(int mapsize, int bats, int pits, int wumpus) {
		makeCaverns(mapsize, bats, pits, wumpus);
	}

	public HashMap<Cavern, String> getCaverns() {
		return caverns;
	}

	public HashMap<Cavern, String> getUnvisitedCaverns() {
		return unvisitedCaverns;
	}

	public void checkMap(int x, int y) throws Exception {

	}

	public class Cavern {

		private int x;
		private int y;

		public Cavern(int newx, int newy) {
			x = newx;
			y = newy;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object obj) {
			Cavern other = (Cavern) obj;
			return (other.x == x && other.y == y);
		}

		@Override
		public int hashCode() {
			return x * 100 + y;
		}
	}

	private void makeCaverns(int mapsize, int bats, int pits, int wumpus) {
		Cavern firstCavern = new Cavern(0, 0);
		caverns.put(firstCavern, "Empty");
		addNeighborsToVisitList(firstCavern);

		for (int i = 1; i < mapsize; i++) {
			Double newIndex = Double.valueOf(Math.floor(Math.random() * unvisitedCaverns.size()));
			Cavern newCavern = (Cavern) unvisitedCaverns.keySet().toArray()[newIndex.intValue()];
			caverns.put(newCavern, "Empty");
			addNeighborsToVisitList(newCavern);
			removeVisitedCaverns();
		}
		for (int i = 0; i < bats; i++) {
			ArrayList<Cavern> options = getEmptyCaverns();
			System.out.println(options.size());
			Double newIndex = Double.valueOf(Math.floor(Math.random() * options.size()));
			Cavern newCavern = options.get(newIndex.intValue());
			caverns.replace(newCavern, "Bats");
		}
		for (int i = 0; i < pits; i++) {
			ArrayList<Cavern> options = getEmptyCaverns();
			Double newIndex = Double.valueOf(Math.floor(Math.random() * options.size()));
			Cavern newCavern = options.get(newIndex.intValue());
			caverns.replace(newCavern, "Pit");
		}
		for (int i = 0; i < wumpus; i++) {
			ArrayList<Cavern> options = getEmptyCaverns();
			Double newIndex = Double.valueOf(Math.floor(Math.random() * options.size()));
			Cavern newCavern = options.get(newIndex.intValue());
			caverns.replace(newCavern, "Wumpus");
		}
	}

	private void addNeighborsToVisitList(Cavern cavern) {
		unvisitedCaverns.put(new Cavern(cavern.getX(), cavern.getY() + 1), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX() - 1, cavern.getY()), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX(), cavern.getY() - 1), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX() + 1, cavern.getY()), "Empty");
	}

	private void removeVisitedCaverns() {
		ArrayList<Cavern> visited = new ArrayList<Cavern>();
		unvisitedCaverns.forEach((key, state) -> {
			if (caverns.containsKey(key)) {
				visited.add(key);
			}
		});
		for (Cavern key : visited) {
			unvisitedCaverns.remove(key);
		}
	}

	private ArrayList<Cavern> getEmptyCaverns() {
		ArrayList<Cavern> empty = new ArrayList<Cavern>();
		caverns.forEach((key, state) -> {
			if (state.equals("Empty")) {
				empty.add(key);
			}
		});
		return empty;
	}

	public int moveOnMap(int startingChamber, int d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
