import java.awt.Graphics2D;
import java.util.ArrayList;

public class ParticleHandler {
	
	ArrayList<Particle> grassParticleContainer;
	ArrayList<Particle> ballParticleContainer;
	ArrayList<TextSplash> textSplashContainer;
	
	int grassDeathTime = 30;
	int ballDeathTime = 15;
	
	GamePanel gamePanel;
	
	public ParticleHandler(GamePanel GamePanel){
		grassParticleContainer = new ArrayList<Particle>();
		ballParticleContainer = new ArrayList<Particle>();
		textSplashContainer = new ArrayList<TextSplash>();
		gamePanel = GamePanel;
	}
	
	public void draw(Graphics2D g2d, double screenX, double screenY, int screenWidth, int screenHeight){
		for(int i = 0; i < grassParticleContainer.size(); i++){
			grassParticleContainer.get(i).draw(g2d, screenX, screenY);
		}
		
		for(int i = 0; i < ballParticleContainer.size(); i++){
			ballParticleContainer.get(i).draw(g2d, screenX, screenY);
		}
		
		for(int i = 0; i < textSplashContainer.size(); i++){
			textSplashContainer.get(i).draw(g2d, screenWidth, screenHeight);
		}
	}
	
	public void createParticleGrass(double x, double y){
		grassParticleContainer.add(new ParticleGrass(x, y, grassDeathTime, grassParticleContainer, gamePanel.ball));
	}
	
	public void createParticleBall(double x, double y){
		ballParticleContainer.add(new ParticleBall(x, y, ballDeathTime, ballParticleContainer, gamePanel.ball));
	}
	
	public void createTextSplash(int deathTime, String text, String subText) {
		textSplashContainer.add(new TextSplash(deathTime, text, subText, textSplashContainer));
	}
}
