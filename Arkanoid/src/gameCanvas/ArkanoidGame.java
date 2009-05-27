package gameCanvas;

import java.util.Random;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

public class ArkanoidGame extends GameCanvas implements Runnable {

	private final long FRAME_DELAY = 33;
	private final int PAD_WIDTH = 40;
	private final int PAD_HEIGHT = 10;
	private final int PAD_DISTANCE_Y = 30;
	private final int BALL_WIDTH = 10;
	private final int BALL_HEIGHT = 10;
	private final int DISTANCIA_DEFAULT = 6;
	private final long INICIO_JOGO = System.currentTimeMillis();
		
	private Display display;
	private boolean sleeping;
	private int inputDelay;
	private Random randPosicaoInicialBola;
	private Image padImage;
	private Image ballImage;
	private Sprite pad;
	private Sprite ball;
	private boolean gameOver;
	private int posicaoAnteriorX;
	private int posicaoAnteriorY;
	private long score;
	private int distanciaPadrao;
	
	
	
	public ArkanoidGame(Display d) {
		super(true);
		this.display = d;
		inputDelay = 0;
		
	}
	
	public void start(){
		display.setCurrent(this);
		
		gameOver = false;
		distanciaPadrao = DISTANCIA_DEFAULT;
		randPosicaoInicialBola = new Random();
		posicaoAnteriorX = -1;
		posicaoAnteriorY = -1;
		
		 
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
		ball.setPosition((randPosicaoInicialBola.nextInt(getWidth())-BALL_WIDTH)+BALL_WIDTH/2, 0);
		
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
		
		//aumentaVelocidade();
		
		if (gameOver) {
			int keyState = getKeyStates();
			if ((keyState & FIRE_PRESSED) != 0) {
			// Start a new game
			ball.setPosition((randPosicaoInicialBola.nextInt(getWidth())-BALL_WIDTH)+BALL_WIDTH/2, 0);
			gameOver = false;
			posicaoAnteriorX=-1;
			posicaoAnteriorY=-1;
			distanciaPadrao = DISTANCIA_DEFAULT;
			
			}
			return;
		}
		
		
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
		checkBoundsBall();
		
		
		
		if (ball.collidesWith(pad, true) ){
			int posicaoAtualX = ball.getX();
			int posicaoAtualY = ball.getY();
			if (ball.getY() + BALL_HEIGHT < pad.getY()){
				if (!impulsionaBall(-(posicaoAtualX - posicaoAnteriorX),(posicaoAtualY-posicaoAnteriorY))){
					ball.move(-(posicaoAtualX - posicaoAnteriorX), (posicaoAtualY-posicaoAnteriorY));
				}
			}else{
				if (!impulsionaBall(posicaoAtualX - posicaoAnteriorX, -(posicaoAtualY-posicaoAnteriorY))){
					ball.move((posicaoAtualX - posicaoAnteriorX), -(posicaoAtualY-posicaoAnteriorY));
				}
			}
			
			
			posicaoAnteriorX = posicaoAtualX;
			posicaoAnteriorY = posicaoAtualY;
			
		}
		
		//checa GameOver
		if (ball.getY()> getHeight()){
			gameOver=true;
			score = System.currentTimeMillis();
		}
		
	}
	
	public void draw(Graphics g){
		
		//----Inicio desenha Fundo-----
		g.setColor(0x0000ff);
		g.fillRect(0, 0, getWidth(), getHeight());
		//---Final desenha fundo----
		
		pad.paint(g);
		ball.paint(g);
		
		if (gameOver) {
			// Draw the game over message and score
			g.setColor(255, 255, 255); // white
			Font fonte = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
			int tamanhoString = fonte.stringWidth("GAME OVER");
			System.out.println("GAME OVER:" + tamanhoString);
			System.out.println("tamanho tela:" + getWidth());
			g.setFont(fonte);
			g.drawString("GAME OVER", (getWidth()-tamanhoString)/2 , (getHeight()-fonte.getHeight())/2 - 20, Graphics.LEFT|Graphics.TOP);
			fonte = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
			String textScore = "You scored " + ((score-INICIO_JOGO)/1000) + " points.";
			tamanhoString = fonte.stringWidth(textScore);
			g.setFont(fonte);
			g.drawString(textScore, (getWidth()-tamanhoString)/2, (getHeight()-fonte.getHeight())/2+10, Graphics.TOP |
			Graphics.LEFT);
			}
		flushGraphics();
	}
	
	// checa a colisao do PAD com as paredes laterais
	private void checkBounds(Sprite sprite){
		int larguraTela = getWidth();
				
		if (sprite.getX() + PAD_WIDTH > larguraTela){
			sprite.setPosition(larguraTela - PAD_WIDTH, getHeight() - PAD_DISTANCE_Y);
		}
		if (sprite.getX() < 0){
			sprite.setPosition(0, getHeight() - PAD_DISTANCE_Y);
		}
		
	}
	
	//calcula a trajetoria da bola
	private void calculaTrajetoriaBola(){
		
		int posicaoAtualX = ball.getX();
		int posicaoAtualY = ball.getY();
		
		if ((posicaoAnteriorX == -1) && (posicaoAnteriorY == -1)){
			posicaoAnteriorX = posicaoAtualX;
			posicaoAnteriorY = 0;
			Random valor = new Random();
			int moveY = valor.nextInt(distanciaPadrao-1) +1;
			ball.move(geraMoveX(moveY) , moveY);
			
			ball.nextFrame();
			
		}
		else{
						
			posicaoAtualX = (posicaoAtualX - posicaoAnteriorX);
			posicaoAtualY = (posicaoAtualY - posicaoAnteriorY);
			posicaoAnteriorX = ball.getX();
			posicaoAnteriorY = ball.getY();
			
			ball.move(posicaoAtualX, posicaoAtualY);
			ball.nextFrame();
		}
		
	}
	
	// checa as colisoes da bola com as paredes laterais e com o topo
	public void checkBoundsBall(){
		int posicaoAtualX = ball.getX();
		int posicaoAtualY = ball.getY();
		
		if (posicaoAtualX+BALL_WIDTH > getWidth()){
			ball.move(-(posicaoAtualX-posicaoAnteriorX), (posicaoAtualY - posicaoAnteriorY));
			posicaoAnteriorX = posicaoAtualX;
			posicaoAnteriorY = posicaoAtualY;
		}
		if (posicaoAtualX < 0){
			ball.move(-(posicaoAtualX-posicaoAnteriorX), (posicaoAtualY - posicaoAnteriorY));
			posicaoAnteriorX = posicaoAtualX;
			posicaoAnteriorY = posicaoAtualY;
		}
		if (posicaoAtualY < 0){
			ball.move((posicaoAtualX-posicaoAnteriorX), -(posicaoAtualY - posicaoAnteriorY));
			posicaoAnteriorX = posicaoAtualX;
			posicaoAnteriorY = posicaoAtualY;
		}
		
				
	}
	
	//gera um valor inicial para a coordenada X referente ao valor de Y escolhido 
	public int geraMoveX(int coordenadaY){
		int coordenadaX=0;
		while(Math.sqrt(coordenadaY*coordenadaY + coordenadaX*coordenadaX)<distanciaPadrao){
			coordenadaX++;
		}
		
		return coordenadaX;
	}
	
	public boolean impulsionaBall(int dx, int dy){
		double parametro=1.1;
		
		while (Math.sqrt((parametro*parametro*dx*dx) + (parametro*parametro*dy*dy))<distanciaPadrao){
			parametro = parametro + 0.1;
			int paramX = (int) (parametro*dx);
			int paramY = (int) (parametro*dy);
			if ((paramX == parametro*dx) && (paramY == parametro*dy)){
				ball.move(paramX, paramY);
				return true;
			}
			
			if (parametro>2){
				return false;
			}
		}
		
		
		return false;
		
	}
	
	public void aumentaVelocidade(){
		long tempoTranscorrido = System.currentTimeMillis() - INICIO_JOGO;
		distanciaPadrao = (int) (DISTANCIA_DEFAULT + (tempoTranscorrido/1000)/30); 	
		
	}

}
