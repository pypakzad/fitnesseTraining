package game.map;

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