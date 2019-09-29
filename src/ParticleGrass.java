import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ParticleGrass extends Particle{

	Ball ball;
	public ParticleGrass(double X, double Y, int DeathTime, ArrayList<Particle> Container, Ball Ball) {
		super(X, Y, DeathTime, Container);
		ball = Ball;
	}
	
	public void draw(Graphics2D g2d, double screenX, double screenY){
		if(time >= deathTime){
			container.remove(0);
		}
		int size = (int)((1-time/deathTime)*2);
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect((int)Math.round(this.x-screenX-1), (int)Math.round(this.y-screenY-1), size, size);
		g2d.setColor(Color.DARK_GRAY);
		time++;
		
		double diff = Math.PI/3;
		int rand = (int)Math.round(Math.random());
		if(rand == 1)
			diff = -diff;
		
		this.x += Math.cos(ball.angle+diff)*2;
		this.y += Math.sin(ball.angle+diff)*2;
	}

}
