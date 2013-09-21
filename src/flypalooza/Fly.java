
package flypalooza;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Fly extends Thread{

    private ImageIcon IconFly;
    private Image Fly;
    private int posY=0,posX=90;
    private boolean life= true;

    private final int speed= 20;
   Random random = new Random();
    public Fly(int x, int y){
        this.posX = x;
        this.posY = y;

        this.IconFly = new ImageIcon("Imagenes/fly.png");
        this.Fly = this.IconFly.getImage();
        
    }
    public void run(){
        while(life){
            try {
                this.Comportamiento();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void Comportamiento(){
           int coordenada = this.random.nextInt(4)+1;
           
           if(coordenada == 1){
               this.Up();
           }else if(coordenada == 2){
               this.Down();
           }else if(coordenada == 3){
               this.Left();
           }else if(coordenada == 4){
                this.Right();
           }
 
    }
    private void Up(){
        if(this.posY>0)
            this.posY = this.posY- this.speed;
    }
    private void Down(){
        if(this.posY<700)
            this.posY = this.posY + this.speed;
    }
    private void Left(){
        if(this.posX>0)
            this.posX = this.posX- this.speed;
    }
    private void Right(){
       if(this.posX<600)
           this.posX = this.posX+ this.speed;
    }
    
   public Image getimage(){
     return this.Fly;
    }

    public int getX(){
        return this.posX;
}
    public int getY(){
        return this.posY;
    }
    public Rectangle Rectangulo(){
     int High;
     int Width;

     High = this.Fly.getHeight(null);
     Width = this.Fly.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
    
}
