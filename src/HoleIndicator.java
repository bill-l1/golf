import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class HoleIndicator {

	public HoleIndicator (){

	}

	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY, int screenWidth, int screenHeight, Ball ball, double endX, double endY){
		if(screenX > endX || screenX+screenWidth < endX || screenY > endY || screenY+screenHeight < endY) {
			AffineTransform oldAT = g2d.getTransform();
			double xDiff = endX-screenX;
			double yDiff = endY-screenY;
			
			int drawX;
			int drawY;
			
			if(xDiff < 0) {
				drawX = 0;
			}else if(xDiff > screenWidth-50) {
				drawX = screenWidth-50;
			}else {
				drawX = (int) (xDiff);
			}
			
			if(yDiff < 0) {
				drawY = 0;
			}else if(yDiff > screenHeight-50) {
				drawY = screenHeight-50;
			}else {
				drawY = (int) (yDiff);
			}
			
			double drawXDiff = drawX-endX+screenX;
			double drawYDiff = drawY-endY+screenY;
			
			double hypo = Math.sqrt(drawXDiff*drawXDiff + drawYDiff*drawYDiff);
			
			g2d.translate(drawX, drawY);
			g2d.drawImage(getImg(), 0, 0, null);
			
			Font oldFont = g2d.getFont();
			g2d.setFont(new Font("Arial", Font.BOLD, 20)); 
			g2d.drawString(String.valueOf(Math.round(hypo/10))+"m", 0, 30);
			g2d.setFont(oldFont);
			
			g2d.setTransform(oldAT);
		}
		
	}
	
	//for image, comes after
	public Image getImg(){
		ImageIcon ic = new ImageIcon("holeIndicatorFlag.png");
		return ic.getImage();
	}

}
