import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WindArrow {

	BufferedImage img;
	public WindArrow (){
		
	}

	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY, Wind wind){
		AffineTransform oldAT = g2d.getTransform();
		g2d.translate(70, 70);
		g2d.rotate(wind.angle+Math.PI/2);
		g2d.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2d.setTransform(oldAT);
		
		g2d.setColor(Color.WHITE);
		Font oldFont = g2d.getFont();
		g2d.setFont(new Font("Arial", Font.BOLD, 20)); 
		g2d.drawString(Math.round(wind.speed*1000)/10.0 + " km/h", 30, 70); 
		g2d.setFont(oldFont);
		g2d.setColor(Color.DARK_GRAY);
	}
	
	public void setColor(double speed, double maxSpeed){
		File input = new File("windArrow.png");
		try {
			img = ImageIO.read(input);
			int width = img.getWidth();
			int height = img.getHeight();
			WritableRaster raster = img.getRaster();
			
			
			for(int i = 0; i < width; i++){
				for(int j = 0; j < height; j++){
					int[] pixel = raster.getPixel(i, j, (int[])null);
					pixel[0] = (int)(255*speed/maxSpeed); //r
					pixel[1] = 0; //g
					pixel[2] = (int)(255-(255*speed/maxSpeed)); //b
					raster.setPixel(i, j, pixel);
				}
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
