package game.map;
public class MockMap extends Map{
	public MockMap(int mapsize) {
		super(mapsize);
		// TODO Auto-generated constructor stub
	}
	String mapHazard;
		private String message;
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
		

}
