
package flypalooza;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.util.ArrayList;
/**
 *
 * @author Eduardo
 */
public class Fly extends Thread{

    private ImageIcon IconFly;
    private Image Fly;
    private int posY=0,posX=90;
    private int CposY =0;
    private boolean life= true;
    
    private int speed= 40;
    Random random = new Random();
    ArrayList ListRuta;
    
    private int posI = 0;
    private boolean BH = false;
    private boolean Droped = true;
    private boolean boss= false;
    
    private int FinalSize=0;
   public Fly(int x, int y, ArrayList listaImg){
        this.posX = x;
        CposY= y;
        this.posY = (y-800);
       
        this.ListRuta = new ArrayList();
        ListRuta = listaImg;
        
        FinalSize = this.ListRuta.size()-1;
        LoadImage();
   }
   private void LoadImage(){//Este metodo se puede Omitir :3
       
       String Ruta =(String) this.ListRuta.get(0);
       this.IconFly = new ImageIcon(Ruta);
       this.Fly = this.IconFly.getImage();
    
    }
   
    public void run(){
        while(life){
            try {
                this.behavior();
           
                        if(this.BH==false){
                            if(this.posI<FinalSize){
                                this.NextImage();
                                this.posI++;
                            }else
                                this.BH=true;
                        }else{
                            if(this.posI>0){
                                this.NextImage();
                                this.posI--;
                            }else
                                this.BH= false;
                        }
                      
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void stopFly(){
        this.life= false;
    }
    private void behavior(){
        if(this.Droped){
            this.Down();
        }   
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
    
    public boolean getBoos(){
        return this.boss;
    }
    private void NextImage(){
        
        String Ruta =(String) this.ListRuta.get(this.posI);
        IconFly = new ImageIcon(Ruta);
        this.Fly = this.IconFly.getImage();
    }
    private void Up(){
        if(this.posY>0)
            this.posY = this.posY- this.speed;
    }
    private void Down(){
        if(this.posY<500)
            this.posY = this.posY + this.speed;
        if(this.posY>CposY)
            this.Droped=false;
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
    public void SetSpeed(int c){
        this.speed= c;
    }
    private int M=5;
    private int m= 0;
    

}
