import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class TextSplash {
	int time;
	int deathTime;
	String text;
	String subText;
	int transitionTime = 100;
	int deathTransitionTime;
	TextSplash self;
	ArrayList<TextSplash> container;
	public TextSplash(int DeathTime, String Text, String SubText, ArrayList<TextSplash> Container) {
		time = 0;
		deathTransitionTime = 1;
		deathTime = DeathTime;
		text = Text;
		subText = SubText;
		self = this;
		container = Container;
	}
	
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight){
		if(time > deathTime-10){
			deathTransitionTime++;
		}
		if(time > deathTime){
			container.remove(0);
		}
		AffineTransform oldAT = g2d.getTransform();
		Font oldFont = g2d.getFont();
		Font font = new Font("Arial", Font.BOLD, 40);
		Font subFont = new Font("Arial", Font.PLAIN, 20);
		FontMetrics fontMetrics = g2d.getFontMetrics(font);
		FontMetrics subFontMetrics = g2d.getFontMetrics(subFont);
		
		g2d.translate(0, Math.min(time*5, screenHeight/2-fontMetrics.getHeight()));
		
		Color rectColor = new Color(0, 0, 0, (int)(100*Math.min((double)time/transitionTime, 1))/deathTransitionTime);
		g2d.setColor(rectColor);
		g2d.fillRect(0, 0, screenWidth, (int)(fontMetrics.getHeight()*2));
		
		Color textColor = new Color(255, 255, 255, (int)(255*Math.min((double)time/transitionTime, 1))/deathTransitionTime);
		g2d.setColor(textColor);
		
		g2d.setFont(font);
		g2d.setColor(textColor);
		g2d.drawString(text, screenWidth/2-fontMetrics.stringWidth(text)/2, fontMetrics.getHeight());
		
		g2d.setFont(subFont);
		g2d.setColor(textColor);
		g2d.drawString(subText, screenWidth/2-subFontMetrics.stringWidth(subText)/2, subFontMetrics.getHeight()+fontMetrics.getHeight()+5);
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.setFont(oldFont);
		g2d.setTransform(oldAT);
		time++;
		self = null;
	}
}
