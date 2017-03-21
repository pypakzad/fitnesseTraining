package game.map;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class Map {

	private HashMap<Coordinate, Cavern> caverns = new HashMap<Coordinate, Cavern>();
	private Stack<Coordinate> cavernsToConnect = new Stack<Coordinate>();

	public Map(int mapsize) {
		makeCaverns(mapsize);
	}

	public class Coordinate {
		int x;
		int y;

		Coordinate(int newx, int newy) {
			x = newx;
			y = newy;
		}

		Coordinate move(char direction) {
			switch (direction) {
			case 'N':
				return new Coordinate(x, y++);
			case 'W':
				return new Coordinate(x--, y);
			case 'S':
				return new Coordinate(x, y--);
			case 'E':
				return new Coordinate(x++, y);
			}
			return null;
		}

		@Override
		public boolean equals(Object obj) {
			Coordinate other = (Coordinate) obj;
			return (other.x == x && other.y == y);
		}

		@Override
		public int hashCode() {
			return x * 100 + y;
		}
	}

	public class Cavern {
		Cavern northCavern;
		Cavern eastCavern;
		Cavern westCavern;
		Cavern southCavern;

		public Cavern getCavern(char direction) {
			switch (direction) {
			case 'N':
				return northCavern;
			case 'W':
				return westCavern;
			case 'S':
				return southCavern;
			case 'E':
				return eastCavern;
			}
			return null;
		}

		public void setCavern(char direction, Cavern cavern) {
			switch (direction) {
			case 'N':
				northCavern = cavern;
				break;
			case 'W':
				westCavern = cavern;
				break;
			case 'S':
				southCavern = cavern;
				break;
			case 'E':
				eastCavern = cavern;
			}

		}
	}

	private void connectCavern(Cavern currentCavern, Coordinate currentCoordinate, char direction) {
		Coordinate newCoordinate = currentCoordinate.move(direction);
		if (caverns.containsKey(newCoordinate)) {
			currentCavern.setCavern(direction, caverns.get(currentCoordinate));
		} else {
			Cavern cavern = new Cavern();
			currentCavern.setCavern(direction, cavern);
			cavernsToConnect.add(newCoordinate);
			caverns.put(newCoordinate, cavern);
		}
	}

	public HashMap<Coordinate, Cavern> getCaverns() {
		return caverns;
	}

	private void makeCaverns(int mapsize) {
		Cavern firstCavern = new Cavern();
		Coordinate currentCoordinate = new Coordinate(0, 0);
		caverns.put(currentCoordinate, firstCavern);
		Random connectionGenerator = new Random(System.currentTimeMillis());
		int connection = connectionGenerator.nextInt(4) + 1;
		char[] directions = new char[4];
		directions[0] = 'N';
		directions[1] = 'W';
		directions[2] = 'S';
		directions[3] = 'E';
		int currentCavern = 0;

		for (int i = 0; i < mapsize; i++) {
			Cavern cavern = new Cavern();
			cavern.connectCavern(caverns.get(currentCavern), directions[connection]);
			caverns.put(currentCoordinate, firstCavern);
			connection--;
			if (connection < 0) {
				connection = connectionGenerator.nextInt(5);
				currentCavern++;
			}
		}
	}

	public void checkMap(int x, int y) throws Exception {

	}
}
