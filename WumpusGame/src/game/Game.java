package game;
import game.map.Map;
public class Game {
	private Map map;
public enum Direction{N,S,E,W};
	public static void playerMove(Direction d) throws Exception {
		validateMove(d);
	}

	private static void validateMove(Direction d) throws Exception {
		if(d==null)
			throw new Exception("Input Null Move");
	}
	
	public Game() {
		this.map = new Map();
	}

	public Map getMap() {
		return this.map;
	}

}
