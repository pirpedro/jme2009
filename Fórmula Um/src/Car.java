
public class Car
{
	public Car(int angle)
	{
		this.angle = angle;
		speed = 0;
	}
	
	
	public void update()
	{
		if(speed > 0)
			speed = speed - 1;
	}
	
	
	public void speedUp()
	{
		if(speed <= 8)
			speed = speed + 2;
	}
	
	public void turnLeft()
	{
		angle = angle - 22.5;
		
		if(angle < 0)
			angle = angle + 360;
	}
	
	public void turnRight()
	{
		angle = angle + 22.5;
		
		if(angle >= 360)
			angle = angle - 360;
	}
	
	public int returnDX()
	{
		return speed;
	}
	
	public int returnDY()
	{
		return speed;
	}
	
	public int getFrame()
	{
		if(angle == 0 || angle == 360)
			return 0;
			
		if(angle == 22.5 || angle == 22.5 + 90 || angle == 22.5 + 180 || angle == 22.5 + 270)
			return 1;
		
		if(angle == 45 || angle == 45 + 90 || angle == 45 + 180 || angle == 45 + 270)
			return 2;
		
		if(angle == 67.5 || angle == 67.5 + 90 || angle == 67.5 + 180 || angle == 67.5 + 270)
			return 3;
		
		if(angle == 90 || angle == 180)
			return 5;
		
		return 0;
	}
	
	public int getQuadrante()
	{
		if(angle >= 0 && angle < 90)
			return 1;
		
		if(angle >= 90 && angle < 180)
			return 2;
		
		if(angle >= 180 && angle < 270)
			return 3;
		
		if(angle >= 270 && angle < 360)
			return 4;
		
		return 1;
	}
	
	private double angle;
	private int speed;
}
