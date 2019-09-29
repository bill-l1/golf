import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Ball {
	double x, y, z;
	double velX, velY, velZ;
	double windOffsetX = 0, windOffsetY = 0;
	double gravity = 0.05;
	int size = 6;
	double speed;
	double angle;
	double frictionPercent = 1;
	boolean visible;
	
	public Ball (double x, double y){
		this.x = x;
		this.y = y;
		angle = Math.PI/3;
		speed = 0;
		z = 0;
		visible = true;
	}
	
	public void update(){
		if(speed > 0){
			velX = Math.cos(angle)*speed*frictionPercent + windOffsetX*frictionPercent;
			velY = Math.sin(angle)*speed*frictionPercent + windOffsetY*frictionPercent;
			//System.out.println(windOffsetX);
			if(z > 0){
				velZ -= gravity;
			}else if(z < 0){
				z = 0;
				velZ = 0;
			}
			
			x += velX;
			y += velY;
			z += velZ;
			size = (int)(6+z);
		}
		//System.out.println(velX + ", "+ velY);
	}
	
	//main draw loop
	public void draw(Graphics2D g2d, double screenX, double screenY){
		//AffineTransform oldAT = g2d.getTransform();
		//g2d.drawImage(getImg(), x, y, null);
		if(visible) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect((int)Math.round(this.x-screenX-size/2), (int)Math.round(this.y-screenY-size/2), size, size);
			g2d.setColor(Color.DARK_GRAY);
		}
		//g2d.setTransform(oldAT);
	}
	
	public Image getImg(){
		//ImageIcon ic = new ImageIcon("ball.png");
		return null;
	}
	public Ball clone(){
		Ball newBall = new Ball(this.x, this.y);
		return newBall;
	}

	public void pushByWind(Wind wind) {
		windOffsetX += Math.cos(wind.angle)*wind.speed;
		windOffsetY += Math.sin(wind.angle)*wind.speed;
	}
}
