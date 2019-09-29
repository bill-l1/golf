import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Button {
	Image image;
	Image ogimage;
	Runnable onClick;
	int x;
	int y;
	int imageWidth;
	int imageHeight;
	boolean hovered;
	public Button(int X, int Y, String ImagePath, Runnable OnClick){
		try {
			File in = new File(ImagePath);
			ogimage = ImageIO.read(in);
			image = ogimage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		
		onClick = OnClick;
		x = X;
		y = Y;
		hovered = false;
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(image, x, y, null);
	}
	
	public void resize(double scale) {
		image = ogimage.getScaledInstance((int)(ogimage.getWidth(null)*scale), (int)(ogimage.getHeight(null)*scale), java.awt.Image.SCALE_SMOOTH);
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		
	}
	
}
