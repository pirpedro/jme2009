/*
 * Velocimetro.java
 *
 * Created on 3 de Julho de 2007, 22:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Bruno Araujo
 */
public class Velocimetro extends GenericElement {
    
    private int startRange;
    
    private int endRange;
    
    /** Creates a new instance of Velocimetro */
    public Velocimetro(Graphics g, GameCanvas gCanvas) {
        super("/velocimetro2.png", g, gCanvas);
        setRange(0, 8);
    }

    public void setDirecao(int direcao) {
    }
    
    public void setRange(int start, int end) {
        startRange = start;
        endRange = end;
    }
    
    public void drawVelocity(int vel) {
        sprite.paint(g);
        int raio = 14;
        //Ajusta a velocidade ao intervalo 30..330 
        double angle = (vel - startRange);
        double num = (double)300.0/(endRange - startRange);
        angle = angle*num + 30;
       // Ajusta o �ngulo para come�ar de 90 graus e girar no sentido hor�rio
        angle = -angle + 90;
        //Transforma em radianos
        angle = angle * Math.PI/180;
        int posX = (int)(21 + Math.cos(angle)*raio);
        int posY = (int)(22 - Math.sin(angle)*raio);
        drawPointer(posX, posY);
        
    }
    
    private void drawPointer(int x, int y) {        
        int midX, midY;
       /* midX = sprite.getX() + sprite.getWidth()/2;
        midY = sprite.getY() + sprite.getHeight()/2; */
        midX = 21;
        midY = 22;
        g.setColor(20, 20, 20);
        g.fillArc(midX - 2, midY - 2, 4, 4, 0, 360);
        g.setColor(0, 0, 0);
        g.drawLine(midX, midY, x, y);
    }
}
