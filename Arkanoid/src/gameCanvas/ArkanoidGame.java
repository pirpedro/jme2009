package gameCanvas;

import java.util.Random;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

public class ArkanoidGame extends GameCanvas implements Runnable {

	private final long FRAME_DELAY = 33;
	private final int PAD_WIDTH = 40;
	private final int PAD_HEIGHT = 10;
	private final int PAD_DISTANCE_Y = 30;
	private final int BALL_WIDTH = 10;
	private final int BALL_HEIGHT = 10;
		
	private Display display;
	private boolean sleeping;
	private int inputDelay;
	private Random randPosicaoInicialBola;
	private Image padImage;
	private Image ballImage;
	private Sprite pad;
	private Sprite ball;
	private int ballSpeed;
	private boolean gameOver;
	private int score;
	private int posicaoAnteriorX;
	private int posicaoAnteriorY;
	
	
	public ArkanoidGame(Display d) {
		super(true);
		this.display = d;
		inputDelay = 0;
		
	}
	
	public void start(){
		display.setCurrent(this);
		
		gameOver = false;
		score = 0;
		ballSpeed = 1;
		randPosicaoInicialBola = new Random();
		posicaoAnteriorX = 0;
		posicaoAnteriorY = 0;
		 
		Graphics g;
		padImage = Image.createImage(PAD_WIDTH, PAD_HEIGHT);
		g = padImage.getGraphics();
		g.setColor(0x0fa10a);
		g.fillRect(0, 0,PAD_WIDTH , PAD_HEIGHT);
		
		
		ballImage = Image.createImage(BALL_WIDTH, BALL_HEIGHT);
		
		g = ballImage.getGraphics();
		g.setColor(0x0000ff);
		g.fillRect(0, 0, BALL_WIDTH, BALL_HEIGHT);
		g.setColor(0xffff00);
		g.fillArc(0, 0, BALL_WIDTH, BALL_HEIGHT, 0, 360);
			
		pad = new Sprite(padImage);
		pad.setPosition((getWidth()- PAD_WIDTH)/2 , getHeight()-PAD_DISTANCE_Y);
			
		ball = new Sprite(ballImage);
		ball.setPosition(randPosicaoInicialBola.nextInt()%getWidth(), 0);
		
		//inicia a animação
		sleeping = false;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		sleeping = true;
	}

	public void run() {
		Graphics g = getGraphics();
		
		while(!sleeping){
			update();
			draw(g);
			try {
				Thread.sleep(FRAME_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(){
		
		if(++inputDelay>2){
			int keyState = getKeyStates();
			
			if ((keyState & LEFT_PRESSED) != 0){
				pad.move(-6, 0);
				pad.nextFrame();
			}
			else if ((keyState & RIGHT_PRESSED)!=0){
				pad.move(6, 0);
				pad.nextFrame();
			}
			checkBounds(pad);
		}
		
		calculaTrajetoriaBola();
		
	}
	
	public void draw(Graphics g){
		
		//----Inicio desenha Fundo-----
		g.setColor(0x0000ff);
		g.fillRect(0, 0, getWidth(), getHeight());
		//---Final desenha fundo----
		
		
		pad.paint(g);
		ball.paint(g);
		flushGraphics();
	}
	
	private void checkBounds(Sprite sprite){
		int larguraTela = getWidth();
		
		if (sprite.getX() + PAD_WIDTH > larguraTela){
			sprite.setPosition(larguraTela - PAD_WIDTH, getHeight() - PAD_DISTANCE_Y);
		}
		if (sprite.getX() < 0){
			sprite.setPosition(0, getHeight() - PAD_DISTANCE_Y);
		}
		
	}
	
	private void calculaTrajetoriaBola(){
		
		int posicaoAtualX = getWidth();
		int posicaoAtualY = getHeight();
		
		if ((posicaoAnteriorX == 0) && (posicaoAnteriorY == 0)){
			posicaoAnteriorY = 6*ballSpeed;
			ball.move(0, posicaoAnteriorY * ballSpeed);
			ball.nextFrame();
			
		}
		else{
			posicaoAnteriorX = (ball.getX() - posicaoAnteriorX)* ballSpeed;
			posicaoAnteriorY = (ball.getY() - posicaoAnteriorY)* ballSpeed;
			System.out.println("X anterior: " +ball.getX());
			System.out.println("Y anterior: " +ball.getY());
			System.out.println("X atual: " +posicaoAnteriorX);
			System.out.println("Y atual: " +posicaoAnteriorY);
			ball.move(posicaoAnteriorX, posicaoAnteriorY);
			ball.nextFrame();
		}
		
		
		
		
		
		
		
		
	}
	

}
