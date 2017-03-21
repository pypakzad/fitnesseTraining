package game.map;

import java.util.ArrayList;

public class Map {
	
	private ArrayList<Cavern> caverns = new ArrayList<Cavern>();

	public Map(int mapsize) {
		makeCaverns(mapsize);
	}
	
	public class Cavern {
		Cavern northCavern;
		public void connectNorthCavern(Cavern cavern) {
			northCavern = cavern;
		}
		public Cavern getNorthCavern() {
			return northCavern;
		}
	}

	public ArrayList<Cavern> getCaverns() {
		return caverns;
	}

	private void makeCaverns(int mapsize) {
		Cavern firstCavern = new Cavern();
		caverns.add(firstCavern);	
		for (int i=0; i<mapsize; i++) {
			Cavern cavern = new Cavern();
			cavern.connectNorthCavern(caverns.get(0));
			caverns.add(cavern);
		}
	}
	
}
