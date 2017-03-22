package game.map;

import game.Game.Direction;

public class MockMap extends MapInter{
	private String mapHazard;
	private Integer[][] mockMap;
	private int mapSize;
	private int hazardNumber = 4;
	public enum MockMapType{Wall,Bats,Pit,Wumpus};
	public MockMap(int mapsize) {
		mockMap = new Integer[mapsize+hazardNumber][4];
		this.mapSize = mapsize;
		
	}
	public void setMap(MockMapType m) {
			if (m == MockMapType.Wall)
				this.mapHazard = "Wall";
			if (m==MockMapType.Bats)
				this.mapHazard = "Bats";
			if (m==MockMapType.Pit)
				this.mapHazard = "Pit";
			if (m==MockMapType.Wumpus)
				this.mapHazard = "Wumpus";
			
		}
	@Override
	public int moveOnMap(int start, int d) throws Exception{
		Integer endChamber = mockMap[start][d];
		
		if(endChamber == 0)
		{
			String exception = "Wall";
			throw new Exception(exception);
		}
		return mockMap[start][d];
	}
	@Override
	public void connect(int start,int end, int direction){
		mockMap[start][direction]=end + 1;
		mockMap[end][(direction+2)%4]=start + 1;
	}
	@Override
	public void connect(int start,String end,int direction){
		if (end =="Wumpus")
			mockMap[start][direction]=this.mapSize-hazardNumber;
		if(end == "Pit")
			mockMap[start][direction]=this.mapSize-hazardNumber+1;
		if(end == "Bats")
			mockMap[start][direction]=this.mapSize-hazardNumber+2;
	}
	@Override
	public void checkMap(int start, int direction) throws Exception {
		if(mapHazard != null)
			throw new Exception(mapHazard);
	}

}
