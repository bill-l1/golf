import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Prop {
	double x;
	double y;
	String sprite;
	int holeSize = 7;
	Image image;
	
	public Prop (double X, double Y, String Sprite){
		x = X;
		y = Y;
		sprite = Sprite;
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
}
