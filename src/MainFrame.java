import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	CardLayout cardLayout;
	JPanel mainPanel;
	MenuPanel menuPanel;
	GamePanel gamePanel;
	
	public MainFrame(){
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		menuPanel = new MenuPanel(this);
		gamePanel = new GamePanel();
		
		mainPanel.add(menuPanel, "menu");
		mainPanel.add(gamePanel, "game");
		
		setSize(1000, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Golf?");
		add(mainPanel);
		setVisible(true);
		//createGamePanel();
	}
	
	public void createGamePanel(){
		cardLayout.show(mainPanel, "game");
		gamePanel.requestFocusInWindow();
		gamePanel.soundHandler.play("birds.wav", true);
	}
}
