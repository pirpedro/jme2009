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

	private void loading()
	{
		layerManager = new LayerManager();

		TiledLayer trackLayer = null;

		try
		{
			trackLayer = new TiledLayer(TAM_TRACK, TAM_TRACK, Image.createImage("/pista.png"), TAM_SPRITE_TRACK, TAM_SPRITE_TRACK);
			car = new Sprite(Image.createImage("/carros.png"), TAM_SPRITE_CAR, TAM_SPRITE_CAR);
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		
		layerManager.append(car);
		layerManager.append(trackLayer);
	}

	public void run()
	{
		display.setCurrent(this);

		loading();

		int x = 0;
		int y = 0;

		Graphics graphics = getGraphics();
		layerManager.paint(graphics, x, y);

		while (true)
		{
			car.setPosition(x, y);
			layerManager.setViewWindow(x, y, getWidth(), getHeight());
			layerManager.paint(graphics, 0, 0);
			flushGraphics();

			int keyState = getKeyStates();
			if ((keyState & LEFT_PRESSED) != 0)
			{
				x--;
			}
			else if ((keyState & RIGHT_PRESSED) != 0)
			{
				x++;
			}
			if ((keyState & UP_PRESSED) != 0)
			{
				y--;
			}
			else if ((keyState & DOWN_PRESSED) != 0)
			{
				y++;
			}
		}
	}

	private final int TAM_TRACK = 10;
	private final int TAM_SPRITE_TRACK = 150;
	private final int TAM_SPRITE_CAR = 64;

	private LayerManager layerManager;
	private Display display;
	private Sprite car;
}
