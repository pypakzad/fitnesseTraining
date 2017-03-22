package game.map;

import java.util.HashMap;

public class Map {

	private HashMap<Cavern, String> caverns = new HashMap<Cavern, String>();
	// private Stack<Coordinate> cavernsToConnect = new Stack<Coordinate>();

	public Map(int mapsize) {
		makeCaverns(mapsize);
	}

	public HashMap<Cavern, String> getCaverns() {
		return caverns;
	}

	public void checkMap(int x, int y) throws Exception {

	}

	public class Cavern {

		int x;
		int y;

		Cavern(int newx, int newy) {
			x = newx;
			y = newy;
		}

		Cavern move(char direction) {
			switch (direction) {
			case 'N':
				return new Cavern(x, y + 1);
			case 'W':
				return new Cavern(x - 1, y);
			case 'S':
				return new Cavern(x, y - 1);
			case 'E':
				return new Cavern(x + 1, y);
			}
			return null;
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

	private void makeCaverns(int mapsize) {
		Cavern firstCavern = new Cavern();
		Cavern origin = new Cavern(0, 0);
		caverns.put(origin, firstCavern);
		cavernsToConnect.add(origin);
		// Random connectionGenerator = new Random(System.currentTimeMillis());
		// int connection = connectionGenerator.nextInt(3) + 1;
		int connection = 0;
		char[] directions = new char[4];
		directions[0] = 'N';
		// directions[1] = 'W';
		// directions[2] = 'S';
		// directions[3] = 'E';

		for (int i = 0; i < mapsize; i++) {
			Cavern currentCoordinate = cavernsToConnect.pop();
			connectCavern(caverns.get(currentCoordinate), currentCoordinate, directions[connection]);
			caverns.put(currentCoordinate, firstCavern);
			connection--;
			if (connection < 0) {
				connection = connectionGenerator.nextInt(5);
			}
		}
	}

}
