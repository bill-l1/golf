import java.awt.Color;


public class TileData {

	private static class TileValue {
		String name;
		double friction;
		int type;
		int key;
		TileValue(String Name, int Id, double Friction, int Type, int[] Key){
			name = Name;
			friction = Friction;
			type = Type;
			Color c = new Color(Key[0], Key[1], Key[2]);
			key = c.getRGB(); //shortening RGB key to a single int
			
		}
	}
	
	//TODO palettes for different sprites
	private static TileValue[] tileValues = 
	{
		new TileValue("start", 0, 0.015, 1, new int[]{0, 0, 255}),
		new TileValue("finish", 1, 0.015, 2, new int[]{255, 0, 0}),
		new TileValue("green", 2, 0.015, 0, new int[]{0, 255, 0}),
		new TileValue("fairway", 3, 0.03, 0, new int[]{0, 200, 0}),
		new TileValue("rough", 4, 0.07, 0, new int[]{0, 100, 0}),
		new TileValue("bunker", 5, 100, 0, new int[]{255, 255, 0}),
		new TileValue("water", 6, 0, 3, new int[]{0, 255, 255}),
	};
	
	public static Tile getTile(double x, double y, int size, Color c){
		double friction = 0.15;
		String sprite = "";
		int type = 0;
		
		for(int i = 0; i < tileValues.length; i++){
			if(c.getRGB() == tileValues[i].key){
				TileValue val = tileValues[i];
				friction = val.friction;
				sprite = val.name;
				type = val.type;
				break;
			}
				
		}
		
		return new Tile(x, y, size, friction, "tiles/"+sprite+".png", type);
	}
}
