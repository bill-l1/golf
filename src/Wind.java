
public class Wind {
	double angle;
	double speed;
	double maxSpeed = 0.3;
	public Wind(WindArrow windArrow){
		setWind(windArrow);
	}
	
	public void setWind(WindArrow windArrow){
		//randomly generate wind
		angle =  Math.random()*(Math.PI*2);
		speed = Math.random()*maxSpeed;
		windArrow.setColor(speed, maxSpeed);
		//System.out.println("Wind angle: " + angle + " Wind speed: " + speed);
	}
	
	
	
}
