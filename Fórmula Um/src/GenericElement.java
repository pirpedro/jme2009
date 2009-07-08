/*
 * GenericElement.java
 *
 * Created on 3 de Julho de 2007, 22:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Bruno Araujo
 */
public abstract class GenericElement {
    
    public Sprite sprite;
    
    public int direcao;
    
    protected Graphics g;
    
    protected GameCanvas gCanvas;
    
    /** Creates a new instance of GenericElement */
    public GenericElement(String imageName, Graphics gra, GameCanvas gCanvas) {
        try {
            sprite = new Sprite(Image.createImage(imageName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Erro ao ler imagem " + imageName);
        }
        this.g = gra;
        this.gCanvas = gCanvas;
    }
    
    /** Creates a new instance of GenericElement */
    public GenericElement(Graphics gra, GameCanvas gCanvas) {
        this.g = gra;
        this.gCanvas = gCanvas;
    }    
    
    /** Creates a new instance of GenericElement */
    public GenericElement(String imageName, int width, int height, Graphics gra, GameCanvas gCanvas) {
        try {
            sprite = new Sprite(Image.createImage(imageName), width, height);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Erro ao ler imagem " + imageName);
        }
        this.g = gra;
        this.gCanvas = gCanvas;
    }    
    
    public abstract void setDirecao(int direcao);
    
}
