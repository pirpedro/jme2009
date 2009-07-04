
public class Bot extends Car
{

	public Bot(int angle)
	{
		super(angle);
		
	}
	
	public int getFrame(int numberBot)
	{
		return this.getFrame() + 4 * numberBot;
	}

}
