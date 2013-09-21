package flypalooza;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */
public class Insect implements Runnable{
    private int posX;
    private int posY;
    private int newPosX, newPosY;
    
    private boolean life;
    
    Random random = new Random();
    
    public Insect(){
    
    }
    
    public void run(){
        while(life){
            this.Comportamiento();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    
    }
    
    private void Comportamiento(){
           this.newPosX = this.random.nextInt(10);
           this.newPosY = this.random.nextInt(10);
           
           if((this.newPosX>5) && (this.newPosY>5)){
               this.posX = this.posX+ this.posX;
               this.posY = this.posY +this.posY;
           }else if((this.newPosX<5) && (this.newPosY<5)){
               this.posX = this.posX -this.posX;
               this.posY = this.posY -this.posY;          
           }else if(this.newPosX<5){
               this.posX = this.posX -this.posX;
           }else if(this.newPosY<5){
               this.posX = this.posX -this.posX;
           }
        }
}
