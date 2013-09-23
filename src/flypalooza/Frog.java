/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Frog extends Thread{
   private ArrayList ListRuta;
    private Image Frog;
    private ImageIcon LittleFrog;
    private int posY=380,posX=50;
    
   private boolean life= true;
   private int parpadeo = 3;
   
   private int Sleep = 1000;
   
   public Frog(boolean game){
       this.ListRuta = new ArrayList();
       this.LoadImage();
       
    }
   
    public void run(){
        while(life){
            try {
                
                this.behavior();
                if(this.parpadeo ==4)
                    this.Sleep= 2000;
                else
                    this.Sleep= 300;
                Thread.sleep(Sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
   private void NextImage(int number){
        String Ruta =(String) this.ListRuta.get(number);
        LittleFrog = new ImageIcon(Ruta);
        this.Frog = this.LittleFrog.getImage();
    }
        
   private void behavior(){
       if(this.parpadeo<5){
           this.NextImage(this.parpadeo);
           this.parpadeo++;
       }else
           this.parpadeo = 3;
   }
   
   
    private void LoadImage(){
       
       this.ListRuta.add("Imagenes/Lateral/3.png");
       this.ListRuta.add("Imagenes/Lateral/3.png");
       this.ListRuta.add("Imagenes/Lateral/3.png");
       
       this.ListRuta.add("Imagenes/Parpadeo/1.png");
       this.ListRuta.add("Imagenes/Parpadeo/2.png");
       
       String Ruta =(String) this.ListRuta.get(3);
       LittleFrog = new ImageIcon(Ruta);
       this.Frog = this.LittleFrog.getImage();
    
    }
    
   

   public Image getimage(){
     return this.Frog;
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

     High = this.Frog.getHeight(null);
     Width = this.Frog.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
    
}
