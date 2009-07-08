
public class Car
{
	public Car(int angle)
	{
		this.angle = angle;
		speed = 0;
		wayPoint = 0;
	}
	
	
	public void update()
	{
		if(speed > 0)
			speed = speed - 1;
				
		if(speed < 0)
			speed = speed + 1;
	}
	
	
	public void speedUp()
	{
		if(speed < MAX_SPEED)
			speed = speed + 2;
		
		if(speed > MAX_SPEED)
			speed = MAX_SPEED;
	}
	
	public void reverse()
	{
		if(speed > -MAX_SPEED)
			speed = speed - 2;
		
		if(speed < -MAX_SPEED)
			speed = -MAX_SPEED;
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
		return ((int) Math.floor((Math.sin(Math.toRadians(angle)) * speed)));
	}
	
	public int returnDY()
	{
		return ((int) Math.floor((Math.cos(Math.toRadians(angle)) * speed)));
	}
	
	public int getFrame()
	{
		if(angle == 0 || angle == 360 || angle == 90 || angle == 180)
			return 0;
			
		if(angle == 22.5 || angle == 22.5 + 90 || angle == 22.5 + 180 || angle == 22.5 + 270)
			return 1;
		
		if(angle == 45 || angle == 45 + 90 || angle == 45 + 180 || angle == 45 + 270)
			return 2;
		
		if(angle == 67.5 || angle == 67.5 + 90 || angle == 67.5 + 180 || angle == 67.5 + 270)
			return 3;
		
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
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getAngle()
	{
		return speed;
	}
	
	public int getLap()
	{
		return ((int) Math.floor((wayPoint-1)/4)) + 1;
	}
	
	public int getPosition(int wayPoint2, int wayPoint3,int positionX,int positionY, int getOneX, int getOneY, int getTwoX,int getTwoY)
	{
		if(this.wayPoint == wayPoint2 && this.wayPoint > wayPoint3)
		{
			if((this.wayPoint%4) == 1)
			{
				if(positionX >= getOneX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 2)
			{
				if(positionY >= getOneY)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 3)
			{
				if(positionX <= getOneX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 0)
			{
				if(positionY <= getOneY)
					return 2;
				else
					return 3;
			}
		}
		else if(this.wayPoint == wayPoint2 && this.wayPoint < wayPoint3)
		{
			if((this.wayPoint%4) == 1)
			{
				if(positionX >= getOneX)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 2)
			{
				if(positionY >= getOneY)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 3)
			{
				if(positionX <= getOneX)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 0)
			{
				if(positionY <= getOneY)
					return 1;
				else
					return 2;
			}
		}
		if(this.wayPoint == wayPoint2 && this.wayPoint == wayPoint3)
		{
			if((this.wayPoint%4) == 1)
			{
				if(positionX >= getOneX && positionX >= getTwoX)
					return 1;
				if(positionX >= getOneX && positionX < getTwoX)
					return 2;
				if(positionX < getOneX && positionX >= getTwoX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 2)
			{
				if(positionY >= getOneY && positionY >= getTwoY)
					return 1;
				if(positionY >= getOneY && positionY < getTwoY)
					return 2;
				if(positionY < getOneY && positionY >= getTwoY)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 3)
			{
				if(positionX <= getOneX && positionX <= getTwoX)
					return 1;
				if(positionX <= getOneX && positionX > getTwoX)
					return 2;
				if(positionX > getOneX && positionX <= getTwoX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 0)
			{
				if(positionY <= getOneY && positionY <= getTwoY)
					return 1;
				if(positionY <= getOneY && positionY > getTwoY)
					return 2;
				if(positionY > getOneY && positionY <= getTwoY)
					return 2;
				else
					return 3;
			}
		}
		if(this.wayPoint > wayPoint2 && this.wayPoint == wayPoint3)
		{
			if((this.wayPoint%4) == 1)
			{
				if(positionX >= getTwoX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 2)
			{
				if(positionY >= getTwoY)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 3)
			{
				if(positionX <= getTwoX)
					return 2;
				else
					return 3;
			}
			if((this.wayPoint%4) == 0)
			{
				if(positionY <= getTwoY)
					return 2;
				else
					return 3;
			}
		}
		if(this.wayPoint < wayPoint2 && this.wayPoint == wayPoint3)
		{
			if((this.wayPoint%4) == 1)
			{
				if(positionX >= getTwoX)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 2)
			{
				if(positionY >= getTwoY)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 3)
			{
				if(positionX <= getTwoX)
					return 1;
				else
					return 2;
			}
			if((this.wayPoint%4) == 0)
			{
				if(positionY <= getTwoY)
					return 1;
				else
					return 2;
			}
		}
		else if(this.wayPoint > wayPoint2 && this.wayPoint > wayPoint3)
		{
			return 1;
		}
		else
		{
			return 3;
		}
		
		return 3;
	}
	
	private double angle;
	private int speed;
	
	private final int MAX_SPEED = 12;
	public int wayPoint;
	public boolean passouUmwayPoint = false;
	
}
