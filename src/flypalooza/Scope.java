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
public class Scope extends Thread{
    
    private ArrayList ListRuta;
    private Image Scope;
    private ImageIcon LittleScope;
    private int posY=0,posX=90;
    
    private int speed = 20;
    
    private  int velx=5,vely=5;
    private boolean life= true;
    private boolean Move = true;
    private  int posI=0;
    
    public int numberMi= 0;
    
    public Scope(int x, int y,int number){
        posX = x;
        posY = y;
        numberMi = number;
        
       this.ListRuta = new ArrayList();
        this.LoadImg();
    }
    private void LoadImg(){
        this.ListRuta.add("Imagenes/Miras/mira.png");
        this.ListRuta.add("Imagenes/Miras/mira2.png");
        this.ListRuta.add("Imagenes/Miras/miragrande.png");
        
        this.LittleScope = new ImageIcon("Imagenes/Miras/mira.png");
        this.Scope = this.LittleScope.getImage();
        
    }
    public void ChangeforBigImg(int x){
      String Ruta =(String) this.ListRuta.get(x);
            LittleScope = new ImageIcon(Ruta);
            this.Scope = this.LittleScope.getImage();
    }
    public void run(){
            while(life){
            try {
                if(this.Move)
                    this.behavior();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void StopM(){
        this.life= false;
    }
    public Image getimage(){
     return this.Scope;
    }
    public void behavior(){
    if(this.posX<=20)
        posX=25;
    else if(this.posX>=700)
        posX=695;
    else if(this.posY<=20)
        this.posY= 25;
    else if(this.posY>=500)
        this.posY = 495;

    
    
        posX+=velx;
        posY+=vely;
    
    }
    public void getSpeed(int x){
        this.speed=x;
    
    }
    
    private void nextImg(){
        if(this.posI<this.ListRuta.size()){
        
            String Ruta =(String) this.ListRuta.get(this.posI);

            LittleScope = new ImageIcon(Ruta);
            this.Scope = this.LittleScope.getImage();
            this.posI++;
        }else
            this.posI=0;
    }
    public void Up(){
        vely=-speed;
        velx=0;
    }
    public void Down(){
        vely=speed;
        velx=0;
    }
    public void Left(){
        velx=-speed;
        vely=0;
    }
    public void Right(){
        velx=speed;
        vely=0;
    }
    
    
    public int getX(){
        return this.posX;
}
    public int getY(){
        return this.posY;
    }
    public void setMove(){
        this.Move = true;
    }
    public Rectangle Rectangulo(){
     int High;
     int Width;

     High = this.Scope.getHeight(null);
     Width = this.Scope.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
}
