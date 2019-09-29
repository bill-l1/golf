import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {

	int height;
	int width;
	int mapId;
	int tileSize;
	int[] startCoords;
	int[] endCoords;
	Tile[][] tiles;
	ArrayList<Prop> props;
	String name;
	int par;
	
	public Map(int MapId, int TileSize){
		mapId = MapId;
		tileSize = TileSize;
		startCoords = new int[2];
		endCoords = new int[2];
		init();
	}
	
	public void draw(Graphics2D g2d, double screenX, double screenY){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				tiles[i][j].draw(g2d, screenX, screenY);
			}
		}
		
		for(int i = 0; i < props.size(); i++) {
			props.get(i).draw(g2d, screenX, screenY);
		}
	}
	
	public void init(){
		try{
			File input = new File("maps/"+Integer.toString(mapId)+".png");
			BufferedImage img = ImageIO.read(input);
			width = img.getWidth();
			height = img.getHeight();
			
			FileReader input2 = new FileReader("maps/"+Integer.toString(mapId)+".mapprop");
			BufferedReader in = new BufferedReader(input2);
			
			String line; 
			for(int i = 0; i < 2; i++) {
				line = in.readLine();
				if(i == 0) {
					name = line;
				}else{
					par = Integer.valueOf(line);
				}
			}
			
			tiles = new Tile[height][width];
			props = new ArrayList<Prop>();
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					Color c = new Color(img.getRGB(j, i));
					tiles[i][j] = TileData.getTile(tileSize*j, tileSize*i, tileSize, c);
					switch(tiles[i][j].type) {
					case 1:
						startCoords[0] = tileSize*j+tileSize/2;
						startCoords[1] = tileSize*i+tileSize/2;
						break;
					case 2:
						endCoords[0] = tileSize*j+tileSize/2;
						endCoords[1] = tileSize*i+tileSize/2;
						props.add(new Prop(tileSize*j, tileSize*(i-1), "props/flag.png"));
						break;
					}
				}
			}
			in.close();
			
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public int[] getStartCoords(){
		return startCoords;
	}
	
}
