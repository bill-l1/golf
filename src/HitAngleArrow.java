import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class HitAngleArrow {

	int dist = 6;
	public HitAngleArrow (){

	}

	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY, Ball ball, double hitAngle){
		AffineTransform oldAT = g2d.getTransform();
		g2d.translate((int)Math.round(ball.x-screenX-ball.size/2)+ball.size/2, (int)Math.round(ball.y-screenY-ball.size/2)+ball.size/2);
		g2d.rotate(hitAngle+Math.PI/2);
		//g2d.drawImage(getImg(), (int)(Math.cos(hitAngle)*dist-3), (int)(Math.sin(hitAngle)*dist-1), null);
		g2d.drawImage(getImg(), (int)(-5), (int)(-11), null);
		g2d.setTransform(oldAT);
	}
	
	//for image, comes after
	public Image getImg(){
		ImageIcon ic = new ImageIcon("hitAngleArrow.png");
		return ic.getImage();
	}

}
