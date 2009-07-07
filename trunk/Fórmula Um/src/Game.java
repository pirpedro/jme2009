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

		this.display = display;
	}
	
	public void start()
	{
		display.setCurrent(this);
		
		loading();	
		
		sleeping = false;
		
		Thread thread = new Thread(this);

		thread.start();
		
		car = new Car(90);
		botOne = new Bot(90);
		botTwo = new Bot(90);
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
				
				//TODO
				//System.out.println(carSprite.getX() + ", " + carSprite.getY() + ", 1,");
				
				if(checkCollision())
				{
					car.turnRight();
				}
			}
			else if ((keyState & RIGHT_PRESSED) != 0)
			{			
				car.turnRight();
				//TODO
				//System.out.println(carSprite.getX() + ", " + carSprite.getY() + ", 2,");
				
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
		else if(car.getQuadrante() == 2)
		{
			carSprite.setTransform(Sprite.TRANS_ROT90);
		}
		else if(car.getQuadrante() == 3)
		{
			carSprite.setTransform(Sprite.TRANS_ROT180);
		}
		else if(car.getQuadrante() == 4)
		{
			carSprite.setTransform(Sprite.TRANS_ROT270);
		}
		
		if(botOne.getQuadrante() == 1)
		{
			botOneSprite.setTransform(Sprite.TRANS_NONE);
		}
		else if(botOne.getQuadrante() == 2)
		{
			botOneSprite.setTransform(Sprite.TRANS_ROT90);
		}
		else if(botOne.getQuadrante() == 3)
		{
			botOneSprite.setTransform(Sprite.TRANS_ROT180);
		}
		else if(botOne.getQuadrante() == 4)
		{
			botOneSprite.setTransform(Sprite.TRANS_ROT270);
		}
		
		if(botTwo.getQuadrante() == 1)
		{
			botTwoSprite.setTransform(Sprite.TRANS_NONE);
		}
		else if(botTwo.getQuadrante() == 2)
		{
			botTwoSprite.setTransform(Sprite.TRANS_ROT90);
		}
		else if(botTwo.getQuadrante() == 3)
		{
			botTwoSprite.setTransform(Sprite.TRANS_ROT180);
		}
		else if(botTwo.getQuadrante() == 4)
		{
			botTwoSprite.setTransform(Sprite.TRANS_ROT270);
		}
			
		carSprite.move(car.returnDX(), - car.returnDY());
		carSprite.setFrame(car.getFrame());
		
		botOneSprite.move(botOne.returnDX(), - botOne.returnDY());
		botOneSprite.setFrame(botOne.getFrame(1));
		
		botTwoSprite.move(botTwo.returnDX(), - botTwo.returnDY());
		botTwoSprite.setFrame(botTwo.getFrame(2));
		
		if(checkCollision())
		{
			carSprite.move(-car.returnDX(), car.returnDY());
		}
		
		//TODO
		//System.out.println(carSprite.getX() + ", " + carSprite.getY() + ", 0,");
		
		layerManager.setViewWindow(carSprite.getX()-carSprite.getWidth(), carSprite.getY()-carSprite.getHeight(), getWidth(), getHeight());
	}

	private boolean checkCollision()
	{
		if(carSprite.collidesWith(barreiraLayer, true))
		{
			return true;
		}
		
		if(carSprite.collidesWith(bleacherLayer, true))
		{
			return true;
		}
		
		if(carSprite.collidesWith(objectsLayer, true))
		{
			return true;
		}
		if(carSprite.collidesWith(botOneSprite, true))
		{
			return true;
		}
		if(carSprite.collidesWith(botTwoSprite, true))
		{
			return true;
		}
		
		return false;
	}

	private void loading()
	{
		layerManager = new LayerManager();
		
		try
		{
			barreiraLayer = new TiledLayer(33, 33, Image.createImage("/barreira.png"), 46, 46);
			objectsLayer = new TiledLayer(TAM_OBJECTS, TAM_OBJECTS, Image.createImage("/elementos.png"), TAM_SPRITE_OBJECTS, TAM_SPRITE_OBJECTS);
			floorLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			bleacherLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			trackLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			carSprite = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
			botOneSprite = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
			botTwoSprite = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}

		int[] barreira = {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		                    0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
						 	0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
		
		
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
		
		int[] floor = {	25, 25, 25,  0,  0,  0,  0, 25, 25, 25,
		               	25,  0,  0,  0,  0,  0,  0,  0,  0, 25,
		                 0,  0,  0, 25, 25, 25, 25,  0,  0,  0,
		                 0,  0, 25, 25, 25, 25, 25, 25,  0,  0,
		                 0,  0, 25, 25, 25, 25, 25, 25,  0,  0,
		                 0,  0, 25, 25, 25, 25, 25, 25,  0,  0,
		                 0,  0, 25, 25, 25, 25, 25, 25,  0,  0,
		                 0,  0,  0, 25, 25, 25, 25,  0,  0,  0,
		               	25,  0,  0,  0,  0,  0,  0,  0,  0, 25,
		               	25, 25, 25,  0,  0,  0,  0, 25, 25, 25};
		
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
		
		for (int i = 0; i < barreira.length; i++)
		{
			int column = i % 33;
			int row = (i - column) / 33;

			barreiraLayer.setCell(column, row, barreira[i]);
		}
			
		layerManager.append(barreiraLayer);
		layerManager.append(objectsLayer);
		layerManager.append(bleacherLayer);
		layerManager.append(botOneSprite);
		layerManager.append(botTwoSprite);		
		layerManager.append(carSprite);
		layerManager.append(trackLayer);
		layerManager.append(floorLayer);
		
		carSprite.defineReferencePixel(carSprite.getWidth()/2,carSprite.getHeight()/2);
		
		carSprite.setPosition(INITIAL_POSITION_X, INITIAL_POSITION_Y);
		
		botOneSprite.setPosition(BOT_ONE_INITIAL_POSITION_X, BOT_ONE_INITIAL_POSITION_Y);
		
		botTwoSprite.setPosition(BOT_TWO_INITIAL_POSITION_X, BOT_TWO_INITIAL_POSITION_Y);
	}

	public void run()
	{		
		Graphics graphics = getGraphics();

		while (!sleeping)
		{
			update();

			car.update();
			
			botOne.updateAll();
			
			botTwo.updateAll();
			
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
	
	private static final int TAM_OBJECTS = 15;
	private static final int TAM_TRACK = 10;
	private static final int TAM_SPRITE_TRACK = 150;
	private static final int TAM_SPRITE_CAR = 64;
	private static final int TAM_SPRITE_OBJECTS = 102;
	
	private static final int FRAME_DELAY = 33;
	
	private static final int INITIAL_POSITION_X = 519;
	private static final int INITIAL_POSITION_Y = 163;
	private static final int BOT_ONE_INITIAL_POSITION_X = 670;
	private static final int BOT_ONE_INITIAL_POSITION_Y = 170;	
	private static final int BOT_TWO_INITIAL_POSITION_X = 585;
	private static final int BOT_TWO_INITIAL_POSITION_Y = 220;
	
	private LayerManager layerManager;
	private Display display;
	private Sprite carSprite;
	private Sprite botOneSprite;
	private Sprite botTwoSprite;
	private boolean sleeping;
	private int inputDelay;
	
	private Car car;
	private Bot botOne;
	private Bot botTwo;
	
	private TiledLayer bleacherLayer = null;
	private TiledLayer barreiraLayer = null;
	private TiledLayer trackLayer = null;
	private TiledLayer objectsLayer = null;
	private TiledLayer floorLayer = null;
}