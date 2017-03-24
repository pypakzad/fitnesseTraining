package game.map;

import java.util.ArrayList;
import java.util.HashMap;

import game.map.Map.Cavern;

public class Map {

	private HashMap<Cavern, String> caverns = new HashMap<Cavern, String>();
	private HashMap<Cavern, String> unvisitedCaverns = new HashMap<Cavern, String>();
	private Cavern wumpusCavern;
	private String replacedWumpusCavern;

	public Map(int mapsize, int bats, int pits, int wumpus) {
		makeCaverns(mapsize, bats, pits, wumpus);
	}

	public Map() {
	}

	public void setMap(HashMap<Cavern, String> inputCaverns) {
		caverns = inputCaverns;
	}

	public HashMap<Cavern, String> getCaverns() {
		return caverns;
	}

	public HashMap<Cavern, String> getUnvisitedCaverns() {
		return unvisitedCaverns;
	}
	public String near(int x, int y){
		return null;
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

	public Cavern makeCavern(int x, int y) {
		return new Cavern(x, y);
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
			wumpusCavern = newCavern;
			replacedWumpusCavern = "Empty";
		}
	}

	private void addNeighborsToVisitList(Cavern cavern) {
		ArrayList<Cavern> neighbors = getNeighbors(cavern);
		for (Cavern neighbor : neighbors) {
			unvisitedCaverns.put(neighbor, "Empty");
		}
	}

	public ArrayList<Cavern> getNeighbors(Cavern cavern) {
		ArrayList<Cavern> neighbors = new ArrayList<Cavern>();
		neighbors.add(new Cavern(cavern.getX(), cavern.getY() + 1));
		neighbors.add(new Cavern(cavern.getX() - 1, cavern.getY()));
		neighbors.add(new Cavern(cavern.getX(), cavern.getY() - 1));
		neighbors.add(new Cavern(cavern.getX() + 1, cavern.getY()));
		return neighbors;
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

	public ArrayList<Cavern> getEmptyCaverns() {
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
	public void setWumpusLocation(int x, int y) throws Exception{
		String wumpusCheck = caverns.get(new Cavern(x,y));
		if (wumpusCheck == null){
			throw new Exception ("Wumpus Can't Move Here");
		}
		caverns.replace(wumpusCavern, getReplacedWumpusCavern());
		wumpusCavern = new Cavern(x,y);
		setReplacedWumpusCavern(caverns.get(wumpusCavern));
		caverns.replace(wumpusCavern, "Wumpus");
	}
	public Cavern getWumpusLocation() {
		return wumpusCavern;
	}

	private String getReplacedWumpusCavern() {
		return replacedWumpusCavern;
	}

	private void setReplacedWumpusCavern(String replacedWumpusCavern) {
		this.replacedWumpusCavern = replacedWumpusCavern;
	}

}
