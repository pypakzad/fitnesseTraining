package game.map;

import java.util.HashMap;

import game.map.Map.Cavern;

public abstract class MapInter  {
	public abstract void checkMap(int start, int direction) throws Exception;

	public abstract void connect(int start, int end, int direction);

	public abstract int moveOnMap(int start, int d) throws Exception;
}
