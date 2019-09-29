import java.awt.Graphics2D;

public class UIGroup {
	HitAngleArrow hitAngleArrow;
	HitPowerRect hitPowerRect;
	HitGuide hitGuide;
	HitPowerRectBackground hitPowerRectBackground;
	
	WindArrow windArrow;
	
	Minimap minimap;
	
	ClubUI clubUI;
	
	HoleIndicator holeIndicator;
	public UIGroup(GamePanel gamePanel){
		hitAngleArrow = new HitAngleArrow();
		hitPowerRect = new HitPowerRect();
		hitGuide = new HitGuide();
		hitPowerRectBackground = new HitPowerRectBackground();
		
		windArrow = new WindArrow();
		
		minimap = new Minimap("maps/"+gamePanel.map.mapId+".png");
		
		clubUI = new ClubUI();
		
		holeIndicator = new HoleIndicator();
	}
	
	public void draw(Graphics2D g2d, GamePanel gamePanel){
		if(!gamePanel.ballFlying){
			int screenWidth = gamePanel.screenWidth;
			int screenHeight = gamePanel.screenHeight;
			double hitAngle = gamePanel.hitAngle;
			double hitPower = gamePanel.hitPower;
			double screenX = gamePanel.screenX;
			double screenY = gamePanel.screenY;
			Ball ball = gamePanel.ball;
			Club club = gamePanel.currentClub;
			Wind wind = gamePanel.wind;
			Map map = gamePanel.map;
			int strokes = gamePanel.strokes;
			
			hitAngleArrow.draw(g2d, screenX, screenY, ball, hitAngle);
			hitPowerRectBackground.draw(g2d, screenWidth, screenHeight, hitPowerRect);
			hitPowerRect.draw(g2d, screenWidth, screenHeight, hitPower, club.maxHit);
			hitGuide.draw(g2d, screenX, screenY, ball, hitAngle, club);
			windArrow.draw(g2d, screenX, screenY, wind);
			minimap.draw(g2d, screenWidth, screenHeight, map, ball, hitAngle, club, strokes);
			clubUI.draw(g2d, screenWidth, screenHeight, minimap.image.getWidth(null), club.name);
			holeIndicator.draw(g2d, screenX, screenY, screenWidth, screenHeight, ball, map.endCoords[0], map.endCoords[1]);
		}
	}
	
	public void setHitPowerRectMaxHeight(double height){
		hitPowerRect.setMaxBarHeight(height);
	}
}
