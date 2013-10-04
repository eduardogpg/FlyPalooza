/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Cloud extends Thread{
    
    private Image Cloud;
    private ImageIcon IconCloud;
    
    private int posY=50,posX=80;
    private final int speed= 20;
    
    private Random random = new Random();
    
    public Cloud(int x ,int y){
        this.IconCloud = new ImageIcon("Imagenes/Nube1.png");
        this.Cloud = this.IconCloud.getImage();
        this.posX= x;
        this.posY= y;
    }
    
    private boolean BH = true;
    public void run(){
        while(BH){
            this.Behavior();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cloud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
   public void Behavior(){
       if(this.posX<800)
            this.posX = this.posX + this.speed;
       else{
           int x = this.random.nextInt(200);
           this.posX= x-(x*3);
       }
        
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

     High = this.Cloud.getHeight(null);
     Width = this.Cloud.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
    
    public Image getimage(){
     return this.Cloud;
    }
    public void setBHFalse(){
        this.BH= false;
    }
}
