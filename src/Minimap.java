import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Minimap {
	
	Image image;
	public Minimap (String imagePath){
		ImageIcon ic = new ImageIcon(imagePath);
		image = ic.getImage();
		image = image.getScaledInstance((int)(image.getWidth(null)*1.5), (int)(image.getHeight(null)*1.5), java.awt.Image.SCALE_SMOOTH);
	}

	//main draw loop
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight, Map map, Ball ball, double hitAngle, Club club, int strokes){
		//image = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		AffineTransform oldAT = g2d.getTransform();
		g2d.setColor(Color.BLACK);
		g2d.translate(0, screenHeight-image.getHeight(null));
		g2d.drawImage(image, 0, 0, null);
		g2d.drawRect(0, 0, image.getWidth(null), image.getHeight(null));
		
		Color rectColor = new Color(0, 0, 0, 100);
		g2d.setColor(rectColor);
		g2d.fillRect(0, -95, image.getWidth(null), 85);
		
		Color textColor = new Color(255, 255, 255, 255);
		g2d.setColor(textColor);
		g2d.drawString("STATS", 5, -75);
		g2d.drawString("Hole "+String.valueOf(map.mapId+1), 5, -55);
		g2d.drawString("Par: "+String.valueOf(map.par), 5, -40);
		g2d.drawString("Strokes: "+String.valueOf(strokes), 5, -25);
		
		
		g2d.translate((int)(ball.x/map.tileSize*1.5), (int)(ball.y/map.tileSize*1.5));
		g2d.setColor(Color.WHITE);
		g2d.drawRect(-2, -2, 4, 4);
		
		double b = club.powerZ;
		double a = 0.5*ball.gravity;
		double t = -(-2*b)/(2*a);
		
		int x2 = (int)(Math.cos(hitAngle)*t*club.maxHit*club.powerXY/map.tileSize*1.5);
		int y2 = (int)(Math.sin(hitAngle)*t*club.maxHit*club.powerXY/map.tileSize*1.5);
		
		Stroke oldStroke = g2d.getStroke();
		Stroke dashedLine = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
		g2d.setStroke(dashedLine);
		g2d.drawLine(x2, y2, 0, 0);
		g2d.setStroke(oldStroke);
		g2d.setColor(Color.DARK_GRAY);
		g2d.setTransform(oldAT);
	}
	
	//for image, comes after
	public Image getImg(int mapId){
		ImageIcon ic = new ImageIcon("maps/"+mapId+".png");
		return ic.getImage();
	}
	
}
