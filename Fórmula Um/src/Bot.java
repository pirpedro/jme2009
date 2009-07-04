
public class Bot extends Car
{
	public Bot(int angle, int inicialPositionX, int inicialPositionY)
	{
		super(angle);
		
		positionX = inicialPositionX;
		positionY = inicialPositionY;
	}
	
	public int getFrame(int botNumber)
	{
		return this.getFrame() + 4 * botNumber;
	}

	public void updateAll()
	{
		if(this.getSpeed() < 7)
		this.speedUp();
		
		positionX = positionX + this.returnDX();
		positionX = positionX + this.returnDY();
		
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	private int positionX;
	private int positionY;
}
