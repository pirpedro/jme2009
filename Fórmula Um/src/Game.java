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
				
				if(checkCollision())
				{
					car.turnRight();
				}
			}
			else if ((keyState & RIGHT_PRESSED) != 0)
			{			
				car.turnRight();
				
				if(checkCollision())
				{
					car.turnLeft();
				}
			}
			if ((keyState & UP_PRESSED) != 0)
			{
				car.speedUp();
			}
			if ((keyState & DOWN_PRESSED) != 0)
			{
				car.reverse();
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
		
		carSprite.move(car.returnDX(), - car.returnDY());
		
		if(checkCollision())
		{
			carSprite.move(-car.returnDX(), car.returnDY());
		}
				
		layerManager.setViewWindow(carSprite.getX()-carSprite.getWidth(), carSprite.getY()-carSprite.getHeight(), getWidth(), getHeight());
	}

	private boolean checkCollision()
	{
		if(carSprite.collidesWith(bleacherLayer, true))
		{
			return true;
		}
		
		if(carSprite.collidesWith(objectsLayer, true))
		{
			return true;
		}
		
		return false;
	}

	private void loading()
	{
		layerManager = new LayerManager();
		
		TiledLayer floorLayer = null;
		
		try
		{
			objectsLayer = new TiledLayer(TAM_OBJECTS, TAM_OBJECTS, Image.createImage("/elementos.png"), TAM_SPRITE_OBJECTS, TAM_SPRITE_OBJECTS);
			floorLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			bleacherLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			trackLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			carSprite = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}

		int[] track = {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                 0, 11,  5, 19, 19, 19, 19, 15, 12,  0,
						 0,  3, 17,  0,  0,  0,  0, 16,  4,  0,
						 0, 18,  0,  0,  0,  0,  0,  0, 18,  0,
						 0, 18,  0,  0,  0,  0,  0,  0, 18,  0,
						 0, 18,  0,  0,  0,  0,  0,  0, 18,  0,
						 0, 18,  0,  0,  0,  0,  0,  0, 18,  0,
						 0,  8, 12,  0,  0,  0,  0, 11,  9,  0,
						 0, 16, 10, 19, 19, 19, 19, 20, 17,  0,
						 0,  0,  0,  0,  0,  0,  0,  0,  0,  0};

		int[] objects =	{ 	2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  0,
		               	  	2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  0,
		                    2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  2,  0};
		
		int[] bleacher = {  0,  0,  0, 23, 23, 23, 23,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    22, 0,  0,  0,  0,  0,  0,  0,  0, 24,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0, 21, 21, 21, 21,  0,  0,  0};
		
		int[] floor = {	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
		               	25, 25, 25, 25, 25, 25, 25, 25, 25, 25};
		
		for (int i = 0; i < track.length; i++)
		{
			int column = i % TAM_TRACK;
			int row = (i - column) / TAM_TRACK;
		
			trackLayer.setCell(column, row, track[i]);
			bleacherLayer.setCell(column, row, bleacher[i]);
			floorLayer.setCell(column, row, floor[i]);
		}
		
		for (int i = 0; i < objects.length; i++)
		{
			int column = i % TAM_OBJECTS;
			int row = (i - column) / TAM_OBJECTS;

			objectsLayer.setCell(column, row, objects[i]);
		}
		
		layerManager.append(objectsLayer);
		layerManager.append(bleacherLayer);
		layerManager.append(carSprite);
		layerManager.append(trackLayer);
		layerManager.append(floorLayer);
		
		carSprite.defineReferencePixel(carSprite.getWidth()/2,carSprite.getHeight()/2);
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
	
	private final int TAM_OBJECTS = 15;
	private final int TAM_TRACK = 10;
	private final int TAM_SPRITE_TRACK = 150;
	private final int TAM_SPRITE_CAR = 64;
	private final int FRAME_DELAY = 33;
	private static final int INITIAL_POSITION_X = 750;
	private static final int INITIAL_POSITION_Y = 750;
	private static final int TAM_SPRITE_OBJECTS = 102;

	private LayerManager layerManager;
	private Display display;
	private Sprite carSprite;
	private boolean sleeping;
	private int inputDelay;
	
	private Car car;
	
	private TiledLayer bleacherLayer = null;
	private TiledLayer trackLayer = null;
	private TiledLayer objectsLayer = null;
}