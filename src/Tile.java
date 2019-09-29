import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {
	double x;
	double y;
	int size;
	double friction = 0.15;
	String sprite;
	int holeSize = 7;
	int type; //type of tile (0 - regular, 1 - start, 2 - hole, 3 - hazard)
	Image image;
	
	public Tile (double X, double Y, int Size, double Friction, String Sprite, int Type){
		x = X;
		y = Y;
		size = Size;
		friction = Friction;
		sprite = Sprite;
		type = Type;
		ImageIcon ic = new ImageIcon(sprite);
		image = ic.getImage();
	}
	/*
	public void update(){
		
	}
	*/
	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY){
		//AffineTransform oldAT = g2d.getTransform();
		g2d.drawImage(image, (int)Math.round(this.x-screenX), (int)Math.round(this.y-screenY), null);
		//g2d.drawRect((int)Math.round(this.x-screenX), (int)Math.round(this.y-screenY), size, size);
		//g2d.setTransform(oldAT);
		g2d.setColor(Color.DARK_GRAY);
	}
	
	
	public double getFriction(){
		return friction;
	}
	
	public boolean inHoleBounds(double x2, double y2){
		if(type == 2){
			int holePadding = (size-holeSize)/2;
			if(x2 >= x+holePadding && x2 <= x+size-holePadding && y2 >= y+holePadding && y2 <= y+size-holePadding){
				return true;
			}
		}
		return false;
	}
	
}
