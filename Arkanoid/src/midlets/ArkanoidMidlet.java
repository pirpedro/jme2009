package midlets;

import gameCanvas.ArkanoidGame;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class ArkanoidMidlet extends MIDlet implements CommandListener {

	
	private ArkanoidGame canvas;
	private Command cmdExit;
	
	public ArkanoidMidlet() {
		if (canvas== null){
			canvas = new ArkanoidGame(Display.getDisplay(this));
		}
		cmdExit = new Command("Sair", Command.EXIT,1);
		canvas.addCommand(cmdExit);
		canvas.setCommandListener(this);
		
	}

	public void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		canvas.stop();

	}

	protected void pauseApp() {
		

	}

	protected void startApp() throws MIDletStateChangeException {
		canvas.start();

	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdExit){
			try {
				destroyApp(true);
			} catch (MIDletStateChangeException e) {
				e.printStackTrace();
			}
			notifyDestroyed();
		}
		
	}

}
