import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class HitGuide {

	int dist = 6;
	public HitGuide (){

	}

	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY, Ball ball, double hitAngle, Club club){
		AffineTransform oldAT = g2d.getTransform();
		g2d.translate((int)Math.round(ball.x-screenX-ball.size/2)+ball.size/2, (int)Math.round(ball.y-screenY-ball.size/2)+ball.size/2);
		
		double b = club.powerZ;
		double a = 0.5*ball.gravity;
		
		double t = -(-2*b)/(2*a);
		//System.out.println(t*club.maxHit*club.powerXY);
		
		int x2 = (int)(Math.cos(hitAngle)*(t*club.maxHit*(club.powerXY)));
		int y2 = (int)(Math.sin(hitAngle)*(t*club.maxHit*(club.powerXY)));
		
		if(club.name.equals("Putter")) {
			x2 = (int)(Math.cos(hitAngle)*340);
			y2 = (int)(Math.sin(hitAngle)*340);
		}
		
		g2d.drawLine(x2, y2, 0, 0);
		g2d.setTransform(oldAT);
	}
	
	//for image, comes after
	public Image getImg(){
		ImageIcon ic = new ImageIcon("hitAngleArrow.png");
		return ic.getImage();
	}
}
