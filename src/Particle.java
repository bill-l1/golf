import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Particle {
	ArrayList<Particle> container;
	double x;
	double y;
	int time;
	int deathTime;
	int image;
	public Particle(double X, double Y, int DeathTime, ArrayList<Particle> Container){
		time = 0;
		x = X;
		y = Y;
		deathTime = DeathTime;
		container = Container;
		//ImageIcon ic = new ImageIcon("");
		//image = ic.getImage();
		//image = image.getScaledInstance((int)(image.getWidth(null)*1.5), (int)(image.getHeight(null)*1.5), java.awt.Image.SCALE_SMOOTH);
	}
	
	public void draw(Graphics2D g2d, double screenX, double screenY){
		if(time >= deathTime){
			container.remove(0);
		}
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int)Math.round(this.x-screenX-1), (int)Math.round(this.y-screenY-1), 2, 2);
		g2d.setColor(Color.DARK_GRAY);
		time++;
	}
	
}
