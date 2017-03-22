package game.map;
public class MockMap extends Map{
	public MockMap(int mapsize) {
		super(mapsize);
		// TODO Auto-generated constructor stub
	}
	private String mapHazard;
	private int[][] mockMap;
	public enum MockMapType{Wall,Bats,Pit,Wumpus};
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
		public void checkMap(int x, int y) throws Exception{
			if(mapHazard != null)
			throw new Exception(mapHazard);
		}
		public void MockMap(int size){
		mockMap = new int[size][4];
		}
		public void connect(int start,int end, int direction){
		mockMap[start][direction]=end;
		mockMap[end][(direction+2)%4]=start;
		}

}
