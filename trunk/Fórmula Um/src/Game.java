import java.io.IOException;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

public class Game extends GameCanvas implements Runnable
{
	protected Game(Display display)
	{
		super(true);
		// TODO Auto-generated constructor stub

		this.display = display;
	}
	
	public void start()
	{
		display.setCurrent(this);
		
		loading();	
		
		sleeping = false;
		
		Thread thread = new Thread(this);

		thread.start();
		
		car = new Car(0);
	}
	
	private void draw(Graphics graphics)
	{
		layerManager.paint(graphics, 0, 0);
		
		flushGraphics();
	}
	
	private void update()
	{
		if (++inputDelay > 2) 
		{
			int keyState = getKeyStates();
			
			if ((keyState & LEFT_PRESSED) != 0)
			{	
				car.turnLeft();			
			}
			else if ((keyState & RIGHT_PRESSED) != 0)
			{			
				car.turnRight();
			}
			if ((keyState & UP_PRESSED) != 0)
			{
				car.speedUp();
			}
		}
		
		if(car.getQuadrante() == 1)
		{
			carSprite.setTransform(Sprite.TRANS_NONE);
		}
		if(car.getQuadrante() == 2)
		{
			carSprite.setTransform(Sprite.TRANS_ROT90);
		}
		if(car.getQuadrante() == 3)
		{
			carSprite.setTransform(Sprite.TRANS_ROT180);
		}
		if(car.getQuadrante() == 4)
		{
			carSprite.setTransform(Sprite.TRANS_ROT270);
		}
		
		carSprite.setFrame(car.getFrame());
		carSprite.move(car.returnDX(), car.returnDY());
		layerManager.setViewWindow(carSprite.getX()-carSprite.getWidth(), carSprite.getY()-carSprite.getHeight(), getWidth(), getHeight());
	}

	private void loading()
	{
		layerManager = new LayerManager();

		TiledLayer trackLayer = null;

		try
		{
			trackLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			carSprite = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}

		int[] track = { 25, 25, 25, 23, 23, 23, 23, 25, 25, 25,
						25, 11,  5, 19, 19, 19, 19, 15, 12, 25,
						22,  3, 17, 25, 25, 25, 25, 16,  4, 24,
						22, 18, 25, 25, 25, 25, 25, 25, 18, 24,
						22, 18, 25, 25, 25, 25, 25, 25, 18, 24,
						22, 18, 25, 25, 25, 25, 25, 25, 18, 24,
						22, 18, 25, 25, 25, 25, 25, 25, 18, 24,
						22,  8, 12, 25, 25, 25, 25, 11,  9, 24,
						25, 16, 10, 19, 19, 19, 19, 20, 17, 25,
						25, 25, 25, 21, 21, 21, 21, 25, 25, 25};

		for (int i = 0; i < track.length; i++)
		{
			int column = i % TAM_TRACK;
			int row = (i - column) / TAM_TRACK;
			trackLayer.setCell(column, row, track[i]);
		}
		
		layerManager.append(carSprite);
		layerManager.append(trackLayer);
		
		carSprite.setPosition(INITIAL_POSITION_X, INITIAL_POSITION_Y);
	}

	public void run()
	{		
		Graphics graphics = getGraphics();

		while (!sleeping)
		{
			update();
			
			car.update();
			
			draw(graphics);
			
			try
			{
				Thread.sleep(FRAME_DELAY);
			}
			catch (InterruptedException exception) 
			{
				exception.printStackTrace();
			}
		}
	}
	
	private final int TAM_TRACK = 10;
	private final int TAM_SPRITE_TRACK = 150;
	private final int TAM_SPRITE_CAR = 64;
	private final int FRAME_DELAY = 33;
	private static final int INITIAL_POSITION_X = 0;
	private static final int INITIAL_POSITION_Y = 0;

	private LayerManager layerManager;
	private Display display;
	private Sprite carSprite;
	private boolean sleeping;
	private int inputDelay;
	
	private Car car;
}