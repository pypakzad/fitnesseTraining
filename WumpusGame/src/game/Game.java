package game;
import game.map.Map;
public class Game {
	private Map map;
public enum Direction{N,S,E,W};
	public void playerMove(Direction d) throws Exception {
		validateMove(d);
		
		throw new Exception("Wall");
	}

	private static void validateMove(Direction d) throws Exception {
		if(d==null)
			throw new Exception("Input Null Move");
	}
	
	public Game() {
		this.map = new Map(50);
	}

	public Map getMap() {
		return this.map;
	}

	public void move() {
		// TODO: return
	};

}
