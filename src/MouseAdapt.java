import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseAdapt extends MouseAdapter{
	MenuPanel menu;
	
	public MouseAdapt(MenuPanel m){
		this.menu = m;
	}
	
	public void mouseReleased(MouseEvent e){
		menu.mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		menu.mouseMoved(e);
	}
}
	

