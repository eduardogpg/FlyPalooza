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
    public int  ScoreFrog=0;
    
   private boolean life= true;
   private int PosImg = 0; //Lo coloco en 3 debido a que en la posicion 3 esta la Imagen inicial
   
   private int Sleep = 2000;
   public boolean Pleft= false;
   
   private boolean OpenM = false;
   
   public Frog(boolean game,ArrayList listaA,int x, int y){
       this.ListRuta = new ArrayList();
       this.ListRuta = listaA;
       this.posX=x;
       this.posY = y;
    }
   
    public void run(){
        while(life){
            try {
               this.behavior();
               Thread.sleep(Sleep);
               
               if(this.PosImg == 0){ //Pequeño algoritmo para controlar el parpadeo de la ranita 
                   this.Sleep= 3000;
                }else{
                   if(this.PosImg== 1)
                        this.Sleep=200;
                   else
                       this.Sleep= 50;
               }
               
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

       if(this.PosImg < 2){ //Algoritmo de parpadeo
           this.NextImage(this.PosImg);
           this.PosImg++;
       }else
            this.PosImg= 0;
      
          
   }
   
   public void StopFrog(){
       this.life= false;
   }

    public void OpenMouth(){
     this.Sleep= 50; 
     this.PosImg= 2;
      this.NextImage(this.PosImg);
    }
    public void LeftFace(){
        
    }
    public void UpFace(){
        this.NextImage(5);
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
