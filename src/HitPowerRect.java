import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HitPowerRect {

	int barWidth = 50;
	int barHeight = 100;
	int maxBarHeight = 200;
	
	public HitPowerRect (){
		
	}

	//main draw loop
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight, double hitPower, double windupMax){
		double hitRatio = hitPower/windupMax;
		g2d.setColor(new Color((int)(hitPower/windupMax*255), 255-(int)(hitPower/windupMax*255), 0));
		
		barHeight = (int)(hitRatio*maxBarHeight);
		g2d.fillRect(screenWidth-barWidth-20, screenHeight-barHeight-20, barWidth, barHeight);
		g2d.setColor(Color.DARK_GRAY);
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
