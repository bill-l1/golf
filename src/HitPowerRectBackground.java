import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class HitPowerRectBackground {

	int barWidth = 50;
	int barHeight = 100;
	int maxBarHeight = 200;
	
	public HitPowerRectBackground (){
		
	}

	//main draw loop
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight, HitPowerRect hitPowerRect){
		AffineTransform oldAT = g2d.getTransform();
		Color c = new Color(0, 0, 0, 100);
		g2d.setColor(c);
		g2d.translate(screenWidth-hitPowerRect.barWidth-20, screenHeight-hitPowerRect.maxBarHeight-20);
		g2d.fillRect(0, 0, hitPowerRect.barWidth, hitPowerRect.maxBarHeight);
		
		Font oldFont = g2d.getFont();
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 20)); 
		g2d.drawString("Power", -5, -20);
		g2d.setFont(oldFont);
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.setTransform(oldAT);
	}
	
	//for image, comes after
	public Image getImg(){
		ImageIcon ic = new ImageIcon("hitAngleArrow.png");
		return ic.getImage();
	}
	
	public void setMaxBarHeight(double height){
		maxBarHeight = (int) height*8;
	}
}
