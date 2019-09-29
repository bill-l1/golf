import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyAdapt extends KeyAdapter{
	GamePanel game;
	
	public KeyAdapt(GamePanel g){
		this.game = g;
	}
	
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
}
