import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	Ball ball;
	Ball ogBall;
	
	int mapWidth;
	int mapHeight;
	int tileSize;
	Map map;
	
	double screenX, screenY;
	int screenWidth, screenHeight; 
	
	int strokes;
	
	double hitAngle;
	double hitPower;
	
	boolean lockedView;
	int screenSpeed;
	
	boolean rightPressed = false;
	boolean leftPressed = false;
	boolean shiftPressed = false;

	boolean dPressed = false;
	boolean aPressed = false;
	boolean sPressed = false;
	boolean wPressed = false;
	
	boolean windupToggled;
	int windupIncrement;
	int windupMax;
	
	boolean ballFlying;
	
	Club[] clubs;
	Club currentClub;
	
	Wind wind;
	
	Timer mainTimer;
	
	UIGroup UI;
	
	ParticleHandler particleHandler;
	
	int pauseTime;
	Runnable pauseAction;
	
	SoundHandler soundHandler;
	
	private void initGame(int mapId){
		mapWidth = 50;
		mapHeight = 50;
		tileSize = 40;
		
		screenX = 0;
		screenY = 0;
		
		setScreenDimensions();
		
		strokes = 0;
		
		hitPower = 0;
		hitAngle = 3*Math.PI/2;
		
		lockedView = true;
		screenSpeed = 10;
		
		windupToggled = false;
		windupIncrement = 1;
		windupMax = 30;
		
		ballFlying = false;
		
		map = new Map(mapId, tileSize);
		int startX = map.startCoords[0];
		int startY = map.startCoords[1];
		
		ball = new Ball(startX, startY);
		ogBall = new Ball(startX, startY);
		UI = new UIGroup(this);
		
		//create clubs
		clubs = new Club[4];
		clubs[0] = new Club("Driver", 75, 0.60, 1.25);
		clubs[1] = new Club("Iron", 55, 0.55, 1);
		clubs[2] = new Club("Wedge", 35, 0.5, 0.75);
		clubs[3] = new Club("Putter", 30, 0.4, 0);
		switchClub(0);
		
		wind = new Wind(UI.windArrow);
		
		particleHandler = new ParticleHandler(this);
		
		pauseTime = 0;
		pauseAction = new Runnable() {public void run() {}};
		
		soundHandler = new SoundHandler();
		
		mainTimer.restart();
		
		particleHandler.createTextSplash(200, map.name, "Par: " + String.valueOf(map.par));
	};
	
	public GamePanel(){
		//game init
		mainTimer = new Timer(10, this);
		setFocusable(true);
		addKeyListener(new KeyAdapt(this));
		addComponentListener(new WindowAdapt(this));
		initGame(0);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(pauseTime == 0) {
			pauseAction.run();
			ball.update();
			
			if(lockedView){
				screenX = ball.x - screenWidth/2;
				screenY = ball.y - screenHeight/2;
			}
			
			ball.angle = hitAngle;
			
			if(ballFlying){
				//stuff to do when ball flying
				if(ball.z == 0){
					if(ball.x > tileSize*map.width || ball.y > tileSize*map.height || ball.x < 0 || ball.y < 0){
						pauseTime = 50;
						ball.visible = false;
						pauseAction = new Runnable() {
							public void run() {
								ball = ogBall.clone();
								pauseAction = new Runnable() {public void run() {}};
							}
						};
					}else{
						Tile currentTile = map.tiles[(int)(ball.y/tileSize)][(int)(ball.x/tileSize)];
						switch(currentTile.type){
						case 0:
						case 1:
							ball.frictionPercent = Math.max(0, ball.frictionPercent-currentTile.getFriction());
							if(ball.frictionPercent > 0.3)
								particleHandler.createParticleGrass(ball.x+Math.random()*5-3, ball.y+Math.random()*5-3);
							break;
						case 2:
							//System.exit(0);
							if(currentTile.inHoleBounds(ball.x, ball.y)) {
								pauseTime = 300;
								String[] splashTexts = getScoreText();
								particleHandler.createTextSplash(300, splashTexts[0], splashTexts[1]);
								soundHandler.play("hole.wav", false);
								ball.visible = false;
								pauseAction = new Runnable() {
									public void run() {
										initGame(map.mapId+1);
										pauseAction = new Runnable() {public void run() {}};
									}
								};
							}
								//TODO make end game sequence
							break;
						case 3:
							pauseTime = 50;
							ball.visible = false;
							soundHandler.play("water.wav", false);
							pauseAction = new Runnable() {
								public void run() {
									ball = ogBall.clone();
									pauseAction = new Runnable() {public void run() {}};
								}
							};
							break;
						}
						
					}
					if(ball.frictionPercent <= 0){
						ballFlying = false;
						ball.windOffsetX = 0;
						ball.windOffsetY = 0;
						ball.frictionPercent = 1;
						ball.speed = 0;
						wind.setWind(UI.windArrow);
					}
				}else{
					ball.pushByWind(wind);
					particleHandler.createParticleBall(ball.x, ball.y);
				}
				
			}else{
				double increment = Math.PI/132;
				if(shiftPressed)
					increment = Math.PI/394;
				
				if(rightPressed){
					hitAngle += increment;
				}else if(leftPressed){
					hitAngle -= increment;
				}
				
				if(hitAngle >= Math.PI*2){
					hitAngle = 0;
				}else if(hitAngle < 0){
					hitAngle = Math.PI*2;
				}
				
				if(windupToggled){
					//System.out.println("HIT POWER: " + hitPower);
					lockedView = true;
					hitPower += windupIncrement;
					if(hitPower <= 0 || hitPower >= currentClub.maxHit){
						windupIncrement = -windupIncrement;
					}
				}else{
					if(sPressed){
						screenY += screenSpeed;
						lockedView = false;
					}else if(wPressed){
						screenY -= screenSpeed;
						lockedView = false;
					}
					
					if(dPressed){
						screenX += screenSpeed;
						lockedView = false;
					}else if(aPressed){
						screenX -= screenSpeed;
						lockedView = false;
					}
					
				}
				
			}
			
			if(screenX < 0){
				screenX = 0;
			}else if(screenX > map.width*tileSize-screenWidth){
				screenX = map.width*tileSize-screenWidth;
			}
			
			if(screenY < 0){
				screenY = 0;
			}else if(screenY > map.height*tileSize-screenHeight){
				screenY = map.height*tileSize-screenHeight;
			}
		}else{
			pauseTime--;
		}
		
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		map.draw(g2d, screenX, screenY);
		
		particleHandler.draw(g2d, screenX, screenY, screenWidth, screenHeight);
		
		ball.draw(g2d, screenX, screenY);
		
		UI.draw(g2d, this);
		
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_Y:
				lockedView = !lockedView;
				break;
			case KeyEvent.VK_ESCAPE:
				if(windupToggled){
					windupToggled = false;
					hitPower = 0;
					windupIncrement = Math.abs(windupIncrement);
				}
				break;
			case KeyEvent.VK_RIGHT:
				this.rightPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
			case KeyEvent.VK_SHIFT:
				shiftPressed = true;
				break;
			case KeyEvent.VK_SPACE:
				hitBall();
				break;
			case KeyEvent.VK_1:
				switchClub(0);
				break;
			case KeyEvent.VK_2:
				switchClub(1);
				break;
			case KeyEvent.VK_3:
				switchClub(2);
				break;
			case KeyEvent.VK_4:
				switchClub(3);
				break;
			case KeyEvent.VK_W:
				wPressed = true;
				break;
			case KeyEvent.VK_A:
				aPressed = true;
				break;
			case KeyEvent.VK_S:
				sPressed = true;
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				break;
		}
			
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_SHIFT:
				shiftPressed = false;
				break;
			case KeyEvent.VK_W:
				wPressed = false;
				break;
			case KeyEvent.VK_A:
				aPressed = false;
				break;
			case KeyEvent.VK_S:
				sPressed = false;
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				break;
		}
			
	}
	
	public void hitBall(){
		if(!ballFlying && !windupToggled){
			windupToggled = true;
		}else if(!ballFlying){
			windupToggled = false;
			ballFlying = true;
			ogBall = ball.clone();
			ball.speed = hitPower*currentClub.powerXY;
			ball.velZ = currentClub.powerZ;
			hitPower = 0;
			windupIncrement = Math.abs(windupIncrement);
			soundHandler.play("hit.wav", false);
			strokes++;
		}
		
	}
	
	public void switchClub(int clubVal){
		currentClub = clubs[clubVal];
		hitPower = 0;
		windupIncrement = Math.abs(windupIncrement);
		UI.setHitPowerRectMaxHeight(currentClub.maxHit);
	}
	
	public void endGame() {
		
	}
	
	public String[] getScoreText() {
		int diff = strokes-map.par;
		
		if(strokes <= 0){
			return new String[]{"nice cheats", "this should never happen"};
		}else if(strokes == 1) {
			soundHandler.play("yeah.wav", false);
			soundHandler.play("success.wav", false);
			return new String[]{"Hole-in-one!", String.valueOf(diff)+ " - outstanding!"};
		}
		
		switch(diff) {
			case -4: soundHandler.play("success.wav", false); return new String[]{"Condor!", String.valueOf(diff)+ " - Incredible!"};
			case -3: soundHandler.play("success.wav", false); return new String[]{"Double Eagle!", String.valueOf(diff)+ " - Amazing!"};
			case -2: soundHandler.play("success.wav", false); return new String[]{"Eagle!", String.valueOf(diff)+ " - Great!"};
			case -1: soundHandler.play("success.wav", false); return new String[]{"Birdie!", String.valueOf(diff)+ " - Good work!"};
			case 0: soundHandler.play("success.wav", false); return new String[]{"Par", String.valueOf(diff)+ " - Nice job!"};
			case 1: soundHandler.play("failure.wav", false); return new String[]{"Bogey", "+"+String.valueOf(diff)+ " - Close!"};
			case 2: soundHandler.play("failure.wav", false); return new String[]{"Double Bogey...", "+"+String.valueOf(diff)+ " - Almost there!"};
			case 3: soundHandler.play("failure.wav", false); return new String[]{"Triple Bogey...", "+"+String.valueOf(diff)+ " - Keep trying"};
			case 4: soundHandler.play("failure.wav", false); return new String[]{"Quadruple Bogey...", "+"+String.valueOf(diff)+ " - Better luck next time..."};
			case 5: soundHandler.play("failure.wav", false); return new String[]{"Quintuple Bogey...", "+"+String.valueOf(diff)+ " - better luck next time..."};
		}
		
		soundHandler.play("superfailure.wav", false);
		return new String[]{"+"+String.valueOf(diff) +"...", "Better luck next time..."};
	}
	
	public void setScreenDimensions(int width, int height){
		screenWidth = width;
		screenHeight = height;
	}
	
	public void setScreenDimensions(){
		Dimension screenDim = this.getSize();
		//gameFrame.setScreenDimensions(screenDim.width, screenDim.height);
		
		screenWidth = screenDim.width;
		screenHeight = screenDim.height;
	}
}
