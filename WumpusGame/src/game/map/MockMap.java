package game.map;
import java.util.ArrayList;

import game.map.Map.Cavern;
public class MockMap extends Map{
		public MockMap(int mapsize) {
		super(mapsize);
	}
		private ArrayList<Cavern> caverns = new ArrayList<Cavern>();
		private String message;
		public enum MockMapType{Wall,Bats,Pit,Wumpus};
		public void setMap(MockMapType m) {
			if (Wall){
				
			}
		}

}
