package midlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

import contador.Contador;

public class HCanvas extends GameCanvas implements Runnable {
  
	private final int CAR_SPEED = -4;
	private final int TEMPO_JOGO= 60*2;
	private final int CHICKEN_SPEED=2;
	
	
	private Display  display;
	private Contador counter;
	private int tempoTranscorrido;
	private int alturaTela;
	private int larguraTela; 
	private boolean  sleeping;
	private long     frameDelay;
	private int      inputDelay;
	private Image    background;
	private Sprite[] chickenSprite = new Sprite[4];
	private int[]   posicaoGalinha = {0,0,0,0,0,0,0,0,0,0,0,0};
	private int galinhasNoJogo;
	private Sprite[] carSprite = new Sprite[4];
	private int[]    carYSpeed = new int[4];
	private int[]   posicaoCarro = {0,0,0,0,0,0,0,0,0};
	private boolean  gameOver;
	private int      score;
	private int 	 pontosPerdidos;
	private Player   musicPlayer;
	private VolumeControl volume;
	
	public HCanvas(Display d) {
		super(true);
		display = d;
		larguraTela = getWidth();
		alturaTela = getHeight();
	
	    // Set the frame rate (30 fps)
	    frameDelay = 33;
	
	    // Clear the input delay
	    inputDelay = 0;
	    
	    tempoTranscorrido = TEMPO_JOGO;
	    
	    
	    counter = new Contador();
	}
  
	public void start() {
	    // Set the canvas as the current screen
	    display.setCurrent(this);
	
	    // Initialize the game variables
	    gameOver = false;
	    score = 0;
	    pontosPerdidos=0;
	    galinhasNoJogo=2;
	    

	    // Initialize the background image and chicken and car sprites
	    try {
	      background = Image.createImage("/Highway.png");
	           
	      chickenSprite[0] = new Sprite(Image.createImage("/Chicken.png"), 22, 22);
	      chickenSprite[0].setPosition(0, posicaoInicialGalinha());
	      chickenSprite[1] = new Sprite(Image.createImage("/Chicken.png"), 22, 22);
	      chickenSprite[1].setPosition(0, posicaoInicialGalinha());
	      chickenSprite[2] = new Sprite(Image.createImage("/Chicken.png"), 22, 22);
	      chickenSprite[3] = new Sprite(Image.createImage("/Chicken.png"), 22, 22);
	      
	
	      carSprite[0] = new Sprite(Image.createImage("/Car1.png"));
	      carSprite[0].setPosition(27, 0);
	      carYSpeed[0] = 3;
	      carSprite[1] = new Sprite(Image.createImage("/Car2.png"));
	      carSprite[1].setPosition(62, 0);
	      carYSpeed[1] = 1;
	      carSprite[2] = new Sprite(Image.createImage("/Car4.png"));
	      carSprite[2].setPosition(128, 64);
	      carYSpeed[2] = -5;
	      carSprite[3] = new Sprite(Image.createImage("/Car3.png"));
	      carSprite[3].setPosition(93, alturaTela-carSprite[3].getWidth());
	      carYSpeed[3] = CAR_SPEED;
	      
	    }
	    catch (IOException e) {
	      System.err.println("Failed loading images!");
	    }
	    
	    //inicia o player
	    try{
	    	InputStream is = getClass().getResourceAsStream("/ChickenDance.mid");
	    	musicPlayer = Manager.createPlayer(is,"audio/midi");
	    	musicPlayer.prefetch();
	    	musicPlayer.setLoopCount(-1);
	    	musicPlayer.start();
	    }catch(IOException e){
	    	
	    }catch(MediaException e){
	    	e.printStackTrace();
	    }
	    volume = (VolumeControl)musicPlayer.getControl("javax.microedition.media.control.VolumeControl");
	    // Start the animation thread
	    sleeping = false;
	    Thread t = new Thread(this);
	    t.start();
	}
  
	  public void stop() {
		  // Stop the animation
		  sleeping = true;
		  
			musicPlayer.close();
		
	  }
	  
	  public void run() {
		  Graphics g = getGraphics();
	    
		  // The main game loop
		  while (!sleeping) {
		  update();
		  draw(g);
		  try {
			  Thread.sleep(frameDelay);
		  }catch (InterruptedException ie) {}
		  }
	  }

	  private void update() {
		  // Check to see whether the game is being restarted
		  if (gameOver) {
			  int keyState = getKeyStates();
			  if ((keyState & FIRE_PRESSED) != 0) {
				  // Start a new game
				  counter.restart();
				  try {
					musicPlayer.start();
				} catch (MediaException e) {
					e.printStackTrace();
				}
				  carSprite[3].setPosition(93, alturaTela-carSprite[3].getWidth());
				  gameOver = false;
				  score = 0;
				  pontosPerdidos = 0;
				  galinhasNoJogo=2;
				  
			  }
	
	      // The game is over, so don't update anything
			  return;
		  }
	
		  // Process user input to move the chicken
		if (++inputDelay > 1) {
			int keyState = getKeyStates();
			
			if ((keyState & DOWN_PRESSED)!=0){
				carSprite[3].move(0, CAR_SPEED/2);
			}else{
				carSprite[3].move(0, carYSpeed[3]);
		    	
			}
			if ((keyState & LEFT_PRESSED)!=0){
				carSprite[3].move(-3, 0);
			}else if ((keyState & RIGHT_PRESSED) !=0){
				carSprite[3].move(3, 0);
			}
		   	checkBounds(carSprite[3], false);
		
		    // Reset the input delay
		  	inputDelay = 0;
		}
				
		    // See whether the chicken made it across
		    for(int i= 0; i<galinhasNoJogo;i++){
				if (chickenSprite[i].getX() > 154) {
			    	// Play a sound for making it safely across
			    	// Reset the chicken position and increment the score
			    	chickenSprite[i].setPosition(0, posicaoInicialGalinha());
			    	
			    }
		    }	
		
		    for(int i =0; i<galinhasNoJogo;i++){
		    	chickenSprite[i].move(CHICKEN_SPEED, 0);
		    	chickenSprite[i].nextFrame();
		    }
		    // Update the car sprites
		    for (int i = 0; i < 3; i++) {
		    	// Move the car sprites
		    	carSprite[i].move(0, carYSpeed[i]);
		    	checkBounds(carSprite[i], true);
		
		    	// Check for a collision between the chicken and cars
		    	for(int j = 0; j<galinhasNoJogo;j++){
		    		if (chickenSprite[j].collidesWith(carSprite[i], true)) {
		    		// Play a sound for losing a chicken
		    			chickenSprite[j].setPosition(-22, posicaoInicialGalinha());
		    		}	
		
		        
		    	}
		    }
		    for(int i = 0; i<galinhasNoJogo;i++){
		    	
		        if(carSprite[3].collidesWith(chickenSprite[i], true)){
			    	pontosPerdidos+=2;
			    	posicaoGalinha[i*3]=chickenSprite[i].getX();
	    			posicaoGalinha[i*3+1]=chickenSprite[i].getY();
	    			posicaoGalinha[i*3+2]=8;
			    	chickenSprite[i].setPosition(0, posicaoInicialGalinha());
			    }
		    }
		    
		    for(int i=0; i<3; i++){
		    	if( carSprite[3].collidesWith(carSprite[i], true)){
		    		pontosPerdidos+=1;
		    		posicaoCarro[i*3]=carSprite[i].getX();
	    			posicaoCarro[i*3+1]=carSprite[i].getY();
	    			posicaoCarro[i*3+2]=8;
		    		if(i ==0){
		    			carSprite[i].setPosition(27, -49);
		    		}else if (i ==1){
		    			carSprite[i].setPosition(62, -49);
		    		}else{
		    			carSprite[i].setPosition(128, alturaTela);
		    		}
		    		
		    	}
		    }
	  }

	  private void draw(Graphics g) {
		  // Draw the highway background
		  
		  g.drawImage(background, 0, 0, Graphics.TOP | Graphics.LEFT);
    
		  // Draw the chicken sprite
		  for(int i = 0; i<galinhasNoJogo;i++){
		  	  chickenSprite[i].paint(g);
		  }
		  // Draw the car sprites
		  for (int i = 0; i < 4; i++)
			  carSprite[i].paint(g);
		  
		  
		  for (int i = 0; i < 4; i++){
			if (posicaoGalinha[i*3+2]!=0){
				posicaoGalinha[i*3+2]--;
				g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL));
				g.setColor(255, 0, 0);
			    g.drawString("-2",posicaoGalinha[i*3] ,posicaoGalinha[i*3+1] , Graphics.TOP | Graphics.HCENTER);
			    posicaoGalinha[i*3+1]--;
			}
		  }
		  
		  for (int i = 0; i < 3; i++){
				if (posicaoCarro[i*3+2]!=0){
					posicaoCarro[i*3+2]--;
					g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL));
					g.setColor(255, 0, 0);
				    g.drawString("-1",posicaoCarro[i*3]+10 ,posicaoCarro[i*3+1]+20 , Graphics.TOP | Graphics.HCENTER);
				    posicaoCarro[i*3+1]--;
				    
				}
		  }
		
		  if (gameOver) {
			  // Draw the game over message and score
			  g.setColor(255, 255, 255); // white
		      g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		      g.drawString("GAME OVER", 90, 40, Graphics.TOP | Graphics.HCENTER);
		      g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		      g.drawString("You scored " + score + " points.", 90, 70, Graphics.TOP |
		        Graphics.HCENTER);
		   }else{
			   g.setColor(255, 255, 255); // white
				Font fonte = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
				g.setFont(fonte);
				g.drawString(getHoraFormatada(), 1 , 1, Graphics.LEFT|Graphics.TOP);
				g.drawString(String.valueOf(score), larguraTela-1, 1, Graphics.RIGHT|Graphics.TOP);
		   }
		
		    // Flush the offscreen graphics buffer
		    flushGraphics();
	  }

	  private void checkBounds(Sprite sprite, boolean wrap) {
		  // Wrap/stop the sprite if necessary
	    if (wrap) {
	      // Wrap the sprite around the edges of the screen
	      if (sprite.getX() < -sprite.getWidth())
	        sprite.setPosition(larguraTela, sprite.getY());
	      else if (sprite.getX() > larguraTela)
	        sprite.setPosition(-sprite.getWidth(), sprite.getY());
	      if (sprite.getY() < -sprite.getHeight())
	        sprite.setPosition(sprite.getX(), alturaTela);
	      else if (sprite.getY() > alturaTela)
	        sprite.setPosition(sprite.getX(), -sprite.getHeight());
	    }
	    else {
	      // Stop the sprite at the edges of the screen
	      
	      if (sprite.getX()<22){
	    	 sprite.setPosition(22,sprite.getY());
	      }else if (sprite.getX()> larguraTela-sprite.getWidth()-22){
	    	  sprite.setPosition(larguraTela-sprite.getWidth()-22, sprite.getY());
	      }
	      if (sprite.getY() < -sprite.getHeight())
		        sprite.setPosition(sprite.getX(), alturaTela);
		      else if (sprite.getY() > alturaTela)
		        sprite.setPosition(sprite.getX(), -sprite.getHeight());
	    	
	    }
 	}
	  
	  private String getHoraFormatada(){
		  int aux = counter.getTempoTranscorrido();
		  int tempo = tempoTranscorrido-aux;
		  
		  if (tempo == 0){
			  gameOver = true;
			  try {
				musicPlayer.stop();
			} catch (MediaException e) {
				e.printStackTrace();
			}
		  }
		  if(aux >60){
			  galinhasNoJogo = 3;
			  if (aux>120){
				  galinhasNoJogo = 4; 
			  }	  
		  }
		  score = (int) (aux/10)*8 - pontosPerdidos;
			  String minute = String.valueOf(tempo/60);
			  String second = String.valueOf(tempo%60);
			  if (minute.length()<2){
				  minute = "0"+minute;
			  }
			  if (second.length()<2){
				  second = "0"+second;
			  }
			  return minute+":"+second;
		 		  
	  }
	  
	  private int posicaoInicialGalinha(){
		  int valor = new Random().nextInt()%alturaTela+22;
		  if (valor<0){
			  valor = -valor;
		  }
		  if (valor>alturaTela-22){
			  valor = alturaTela-22;
		  }
		   return valor;
	  }
	  
	 
}
