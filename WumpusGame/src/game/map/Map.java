package game.map;

import java.util.HashMap;

public class Map {

	private HashMap<Cavern, String> caverns = new HashMap<Cavern, String>();
	private HashMap<Cavern, String> unvisitedCaverns = new HashMap<Cavern, String>();

	public Map(int mapsize) {
		makeCaverns(mapsize);
	}

	public HashMap<Cavern, String> getCaverns() {
		return caverns;
	}

	protected HashMap<Cavern, String> getUnvisitedCaverns() {
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

	private void addNeighborsToVisitList(Cavern cavern) {
		unvisitedCaverns.put(new Cavern(cavern.getX(), cavern.getY() + 1), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX() - 1, cavern.getY()), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX(), cavern.getY() - 1), "Empty");
		unvisitedCaverns.put(new Cavern(cavern.getX() + 1, cavern.getY()), "Empty");
	}

	private void makeCaverns(int mapsize) {
		Cavern firstCavern = new Cavern(0, 0);
		caverns.put(firstCavern, "Empty");
		addNeighborsToVisitList(firstCavern);

		for (int i = 0; i < mapsize; i++) {
			Double newIndex = Double.valueOf(Math.rint(Math.random() * unvisitedCaverns.size()));
			Cavern newCavern = (Cavern) unvisitedCaverns.keySet().toArray()[newIndex.intValue()];
			caverns.put(newCavern, "Empty");
			unvisitedCaverns.remove(newCavern);
		}
	}

}
