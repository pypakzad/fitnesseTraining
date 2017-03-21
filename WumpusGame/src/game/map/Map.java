package game.map;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

public class Map {
	
	private HashMap<Coordinate, Cavern> caverns = new HashMap<Coordinate, Cavern>();

	public Map(int mapsize) {
		makeCaverns(mapsize);
	}

	
	private class Coordinate {
		int x;
		int y;
		Coordinate(int newx, int newy) {
			x = newx;
			y = newy;
		}
		Coordinate move(char direction){
			switch (direction) {
			case 'N':	Cavern.northCavern=cavern;
						northCavern.southCavern = this;
						break;
			case 'W':	westCavern=cavern;
						westCavern.eastCavern = this;
						break;
			case 'S':	southCavern=cavern;
						southCavern.northCavern = this;
						break;
			case 'E':	eastCavern=cavern;
						eastCavern.westCavern = this;
			}	
		}

	}
	
	public class Cavern {
		Cavern northCavern;
		Cavern eastCavern;
		Cavern westCavern;
		Cavern southCavern;
		private void connectCavern(Cavern cavern, char direction) {
			switch (direction) {
			case 'N':	northCavern=cavern;
						northCavern.southCavern = this;
						break;
			case 'W':	westCavern=cavern;
						westCavern.eastCavern = this;
						break;
			case 'S':	southCavern=cavern;
						southCavern.northCavern = this;
						break;
			case 'E':	eastCavern=cavern;
						eastCavern.westCavern = this;
			}
		}
		public Cavern getCavern(char direction) {
			switch (direction) {
				case 'N':	return northCavern;
				case 'W':	return westCavern;
				case 'S':	return southCavern;
				case 'E':	return eastCavern;
			}
			return null;
		}
	}

	public HashMap<Coordinate, Cavern> getCaverns() {
		return caverns;
	}

	private void makeCaverns(int mapsize) {
		Cavern firstCavern = new Cavern();
		Coordinate currentCoordinate = new Coordinate(0,0);
		caverns.put(currentCoordinate, firstCavern);
		Random connectionGenerator = new Random(System.currentTimeMillis());
		int connection = connectionGenerator.nextInt(4)+1;
		char[] directions = new char[4];
		directions[0] = 'N';
		directions[1] = 'W';
		directions[2] = 'S';
		directions[3] = 'E';
		int currentCavern = 0;

		for (int i=0; i<mapsize; i++) {
			Cavern cavern = new Cavern();
			cavern.connectCavern(caverns.get(currentCavern), directions[connection]);
			caverns.put(currentCoordinate, firstCavern);
			connection--;
			if (connection<0) {
				connection = connectionGenerator.nextInt(5);
				currentCavern++;
			}
		}
	}
	public  void checkMap(int x, int y) throws Exception
	{
		
	}
}
