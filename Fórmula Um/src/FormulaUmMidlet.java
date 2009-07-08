import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class FormulaUmMidlet extends MIDlet implements CommandListener
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

		Command exitCommand = new Command("Exit", Command.EXIT, 0);
		game.addCommand(exitCommand);
		game.setCommandListener(this);

		game.start();
	}

	private Game game;

	public void commandAction(Command c, Displayable d)
	{
		if (c.getCommandType() == Command.EXIT)
		{
			try
			{
				destroyApp(true);
			}
			catch (MIDletStateChangeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyDestroyed();
		}

	}

}
