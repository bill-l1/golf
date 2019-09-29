import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ParticleBall extends Particle{

	Ball ball;
	int initSize;
	public ParticleBall(double X, double Y, int DeathTime, ArrayList<Particle> Container, Ball Ball) {
		super(X, Y, DeathTime, Container);
		ball = Ball;
		initSize = ball.size;
	}
	
	public void draw(Graphics2D g2d, double screenX, double screenY){
		if(time >= deathTime){
			container.remove(0);
		}
		int size = (int)((1-(double)time/deathTime)*initSize);
		Color c = new Color(255, 255, 255, (int)((1-(double)time/deathTime)*255));
		g2d.setColor(c);
		g2d.fillRect((int)Math.round(this.x-screenX-size/2), (int)Math.round(this.y-screenY-size/2), size, size);
		g2d.setColor(Color.DARK_GRAY);
		time++;
	}

}
