import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	int screenWidth, screenHeight; 
	Timer mainTimer;
	BufferedImage logoImg;
	Button playButton;
	Button quitButton;
	MainFrame mainFrame;
	
	public MenuPanel(final MainFrame main){
		//menu init
		mainTimer = new Timer(10, this);
		mainFrame = main;
		MouseAdapt mouseAdapter = new MouseAdapt(this);
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setFocusable(true);
		setBackground(new Color(124, 183, 137));
		try {
			File logoIn = new File("logo.png");
			logoImg = ImageIO.read(logoIn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Runnable playOnClick = new Runnable(){
			public void run() {
				mainFrame.createGamePanel();
			}
		};
		playButton = new Button(0, 0, "playButton.png", playOnClick);
		Runnable quitOnClick = new Runnable(){
			public void run() {
				System.exit(0);
			}
		};
		quitButton = new Button(0, 0, "quitButton.png", quitOnClick);
		mainTimer.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		repaint();
		setScreenDimensions();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(logoImg, screenWidth/2-logoImg.getWidth()/2, screenHeight/2-logoImg.getHeight()/2-200, null);
		playButton.x = screenWidth/2-playButton.imageWidth/2;
		playButton.y = screenHeight/2-playButton.imageHeight/2+100;
		playButton.draw(g2d);
		quitButton.x = screenWidth/2-quitButton.imageWidth/2;
		quitButton.y = screenHeight/2-quitButton.imageHeight/2+300;
		quitButton.draw(g2d);
	}
	
	public void setScreenDimensions(int width, int height){
		screenWidth = width;
		screenHeight = height;
	}
	
	public void setScreenDimensions(){
		Dimension screenDim = this.getSize();

		screenWidth = screenDim.width;
		screenHeight = screenDim.height;
	}

	public void mouseReleased(MouseEvent e) {
		if(playButton.hovered) {
			playButton.onClick.run();
		}
		if(quitButton.hovered) {
			quitButton.onClick.run();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		if(mouseX > playButton.x && mouseX < playButton.x+playButton.imageWidth 
		&& mouseY > playButton.y && mouseY < playButton.y+playButton.imageHeight) {
			playButton.resize(1.2);
			playButton.hovered = true;
		}else {
			playButton.resize(1);
			playButton.hovered = false;
		}
		if(mouseX > quitButton.x && mouseX < quitButton.x+quitButton.imageWidth 
		&& mouseY > quitButton.y && mouseY < quitButton.y+quitButton.imageHeight) {
			quitButton.resize(1.2);
			quitButton.hovered = true;
		}else {
			quitButton.resize(1);
			quitButton.hovered = false;
		}
	}
}
