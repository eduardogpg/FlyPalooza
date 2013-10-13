/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Boss extends Thread{
     
    private Clock TimeLife= new Clock();
    private ImageIcon IconBoss;
    private Image Boss;
    private int posY=0,posX=90;
    private int CposY =0;
    private boolean life= true;
    private boolean Fight= true;
           
    public int ScorepLiefe= 100;
    private final int speed= 40;
    Random random = new Random();
    ArrayList ListPath;
    private int posIz= 0;
    private int posDr =1;
   
    private boolean Droped = true;
    private boolean boss= false;
    
   public Boss(int x, int y){
        this.posX = x;
        CposY= y;
        this.posY = (y-800);
       
        this.ListPath = new ArrayList();
        LoadImage();
        TimeLife.start();
   }
   
   private void LoadImage(){
       
       this.ListPath.add("Imagenes/Boss/1.png");//0
       this.ListPath.add("Imagenes/Boss/1b.png");//1
       this.ListPath.add("Imagenes/Boss/2.png");//2
       this.ListPath.add("Imagenes/Boss/2b.png");//3

       this.ListPath.add("Imagenes/Combo.png");//4
       
       
       String Ruta =(String) this.ListPath.get(0);
       this.IconBoss = new ImageIcon(Ruta);
       this.Boss = this.IconBoss.getImage();
    
    }
   
    public void run(){
        while(life){
            try {
                if(Fight)
                    this.behavior();
                else
                    this.Bye();
                
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }this.ChangeForcombo();
        
    }
    public void stopFly(){
        this.life= false;
    }
    
    private void behavior(){
        if(this.Droped)
            this.Down();
           
        if(this.ScorepLiefe <=0){
            this.life= false;
            this.posX=100;
            this.posY= 300;
        }
        else if(this.ScorepLiefe< 80){
            this.posIz=2;
            this.posDr=3;
      }
        
        if(this.TimeLife.getSecond()>15)
            this.Fight = false;
        
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
    public void ChangeForcombo(){
       
         this.NextImage(4);
    }
    private void Bye(){
       if(this.posY>-500)
            this.posY = this.posY- this.speed;
       else
           this.life= false;
    }
    public boolean getBoos(){
        return this.boss;
    }
    private void NextImage(int pos){
        
        String Ruta =(String) this.ListPath.get(pos);
        this.IconBoss = new ImageIcon(Ruta);
        this.Boss = this.IconBoss.getImage();
    }
    private void Up(){
        if(this.posY>0)
            this.posY = this.posY- this.speed;
    }
    private void Down(){
        if(this.posY<500)
            this.posY = this.posY + this.speed;
        if(this.posY>(CposY+200))
            this.Droped=false;
     }   
    private void Left(){
        if(this.posX>0)
            this.posX = this.posX- this.speed;
        this.NextImage(this.posIz);
    }
    private void Right(){
       if(this.posX<600)
           this.posX = this.posX+ this.speed;
       this.NextImage(this.posDr);
    }
    
   public Image getimage(){
     return this.Boss;
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

     High = this.Boss.getHeight(null);
     Width = this.Boss.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }

    private int M=5;
    private int m= 0;
 
    public boolean getLife(){
       
        return this.life;
    }
    
}

    
    
    
