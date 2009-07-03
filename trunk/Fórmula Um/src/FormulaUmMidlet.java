import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class FormulaUmMidlet extends MIDlet
{

	public FormulaUmMidlet()
	{
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException
	{
		// TODO Auto-generated method stub

	}

	protected void pauseApp()
	{
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException
	{
		game = new Game(Display.getDisplay(this));
		
		game.start();
	}
	
	private Game game;

}
