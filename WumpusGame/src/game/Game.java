package game;

public class Game {
public enum Direction{N,S,E,W};
	public static void playerMove(Direction d) throws Exception {
		validateMove(d);
	}

	public static void validateMove(Direction d) throws Exception {
		if(d==null)
			throw new Exception("Input Null Move");
	}

}
