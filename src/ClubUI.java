import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;


public class ClubUI {

	Image image;
	public ClubUI (){
		ImageIcon ic = new ImageIcon("clubs/driver.png");
		image = ic.getImage();
		//image = image.getScaledInstance((int)(image.getWidth(null)*1.5), (int)(image.getHeight(null)*1.5), java.awt.Image.SCALE_SMOOTH);
	}

	//main draw loop
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight, int minimapWidth, String clubName){
		AffineTransform oldAT = g2d.getTransform();
		g2d.translate(minimapWidth, screenHeight);
		Color rectColor = new Color(0, 0, 0, 100);
		g2d.setColor(rectColor);
		g2d.fillRect(0, -105, 300, 105);
		
		g2d.drawImage(image, 0, -100, null);
		
		Font oldFont = g2d.getFont();
		Color textColor = new Color(255, 255, 255, 255);
		g2d.setColor(textColor);
		g2d.setFont(new Font("Arial", Font.BOLD, 40)); 
		g2d.drawString(clubName, 135, -40);
		g2d.setFont(oldFont);
		g2d.setTransform(oldAT);
	}
	
	//for image, comes after
	public Image getImg(){
		ImageIcon ic = new ImageIcon("");
		return ic.getImage();
	}
	
}
