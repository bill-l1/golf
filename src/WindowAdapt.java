import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class WindowAdapt extends ComponentAdapter{
	GamePanel gamePanel;
	public WindowAdapt(GamePanel frame){
		gamePanel = frame;
	}
	public void componentResized(ComponentEvent componentEvent) {
		gamePanel.setScreenDimensions();
		
    }
}
