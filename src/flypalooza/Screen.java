/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Eduardo
 */
public class Screen extends JPanel implements ActionListener,KeyListener,MouseListener{
    private Image BackGround;
    private ImageIcon myBackground;
    
    private Image Grass;
    private ImageIcon IconGrass;
    
    private ArrayList List_Fly;
    private ArrayList ListSpecial;
    private ArrayList ListPathP1;
    private ArrayList listClouds;
    
    private final Color MyColor=new Color( 14, 60, 180);
    private int ScoreO=0;
    private int deadTime = 30;
    private int TimeCreateSpecial =0 ;
    private int Horda = 10;
    private int Level = 0;
    private int AuxTimer = 0;
    
    private boolean timeOut= false;
    private boolean SpecialBoolean = false;
    private boolean SpeedFlys = false;
    
    String Mesage = "You Ready fot Cath "+this.Horda+ " in 30 seconds ";
    
    Clock clock = new Clock();
    Font font;
    Timer time;
    
    //Point MyMouse = MouseInfo.getPointerInfo().getLocation();
    
    Random random = new Random();
    Frog Player ;
    Scope MiraPlayerUno;
    Scope MiraPlayerDos = new Scope(0,500);
    
    public Screen(){
    
        myBackground = new ImageIcon("Imagenes/Escenario.png");
        BackGround = myBackground.getImage();
        this.font = new Font("SansSerif",Font.BOLD,24);   

        this.IconGrass = new ImageIcon("Imagenes/pasto.png");
        this.Grass = this.IconGrass.getImage();
        
        this.List_Fly = new ArrayList();
        this.ListPathP1 = new ArrayList();
        this.ListSpecial = new ArrayList();
        this.listClouds= new ArrayList();
        
        //Funciones
        this.MakeBites(this.Horda);
        this.MakeFirstPlayer();
        this.MakeCloud();
        
            time = new Timer(4,this); 
            time.start();
            this.clock.start();
            setFocusable(true);
            addKeyListener(this);
            addMouseListener(this);

    }        
    
    public void MakeBites(int horda){
        
        for(int x=0; x<horda;x++){
            int Cx = this.random.nextInt(700)+1;
            int Cy = this.random.nextInt(400)+1;
                Fly moscas = new Fly(Cx,Cy);
                moscas.start();
                this.List_Fly.add(moscas);
        }
        this.TimeCreateSpecial = this.random.nextInt(10)+1; //Me ayuda a crear un Item Especial
 
    }
    
    private void MakeCloud(){
        for(int x=0;x<4;x++){
            int w = this.random.nextInt(600);
            int y= this.random.nextInt(100);
            Cloud mycloud = new Cloud(w, y);
            
            listClouds.add(mycloud);
            mycloud.start();
        }
    
    }
    
    private void MakeFirstPlayer(){
        
       this.ListPathP1.add("Imagenes/Lateral/1.png"); //0
       this.ListPathP1.add("Imagenes/Lateral/2.png");//1
       this.ListPathP1.add("Imagenes/Lateral/3.png");//2
       this.ListPathP1.add("Imagenes/Parpadeo/1.png"); //3
       this.ListPathP1.add("Imagenes/Parpadeo/2.png");//4
       this.ListPathP1.add("Imagenes/Centro/3.png");//5
       
        //Dibujo la raba y creo la mira
        this.Player = new Frog(true, this.ListPathP1);
        this.Player.start();
        this.MiraPlayerUno = new Scope(300,300);
        
    }
    
    public void paint(Graphics g){
     
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g; //Pasamos G a Graphics 2D
    g.drawImage(this.BackGround,0,0, this);
      
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     
     g2.drawImage(this.Grass,0,200, null);
     
     g2.drawImage(this.Player.getimage(), this.Player.getX(),this.Player.getY() ,null);//Dibujamos al Jugador 
     
     g2.drawImage(this.MiraPlayerUno.getimage(),this.MiraPlayerUno.getX(), MiraPlayerUno.getY(),null);
     g2.drawImage(this.MiraPlayerDos.getimage(),this.MiraPlayerDos.getX(), MiraPlayerDos.getY(),null);
     
     for(int x=0;x<this.listClouds.size();x++){
               Cloud myCloud =(Cloud) this.listClouds.get(x);
               g2.drawImage(myCloud.getimage(),myCloud.getX(), myCloud.getY(),null);
     }
             
        if((this.Level==0)&&(this.clock.getSecond()<4)){
            g2.drawString(this.Mesage +this.clock.getSecond(),300,80);  

        }else{
               if((this.clock.getSecond()==4)&&(this.Level==0))
                    g2.drawString("GOOOOOO",300,80);

        }

        
        
     if(this.timeOut)
           g2.drawString(this.Mesage,250,180); 
     else if(this.Level!=0){
            g2.drawString("Score Player One : "+this.ScoreO,80,40);
            g2.drawString("Score Player Two : "+this.ScoreO,400,40);

            g2.drawString("Timer : "+this.clock.getSecond(),300,80);  
     }
     
     if(this.List_Fly.size()>0)
         for(int x=0;x<this.List_Fly.size();x++){
             Fly LittleMosca = (Fly)this.List_Fly.get(x);
             g2.drawImage(LittleMosca.getimage(),LittleMosca.getX(), LittleMosca.getY(),null);
         }
     
        if( this.ListSpecial.size()>0){
            
            Special Att =(Special) this.ListSpecial.get(0);
            g2.drawImage(Att.getimage(),Att.getX(), Att.getY(),null);
            
            if(Att.getY()>300){
              this.ListSpecial.remove(0);
              Att.stop();
            }
             
        }
        
     }
  
    public void actionPerformed(ActionEvent e) {
        repaint();
        this.Sinapsis();
        this.CheckSpecial();
        
    }
   
    int coZ = 0;
    private void CheckSpecial(){
         if(this.TimeCreateSpecial== this.clock.getSecond()){
              this.SpecialBoolean= true;
              this.coZ++;
         }
         if((this.SpecialBoolean)&& (this.coZ==1)){
                
             Special Attac = new Special();
             Attac.start();
             System.err.println("Entro");/////////////////////////////////////////////////////////
             this.ListSpecial.add(Attac);
             SpecialBoolean= false;
             TimeCreateSpecial= 0;
         }
      
      }
    
    private void Sinapsis(){
        if((this.Level==0)&&(this.clock.getSecond()==5)){
            this.clock.Restart();
             this.Level++;
             MiraPlayerUno.start();
             //MiraPlayerDos.start();
        }
        
        if((TimeOut())&&(this.Level<3)){
            this.Level++;
            this.Mesage ="Preparate para la luciernagas ";
        }
       if(SpeedFlys)
           if(this.AuxTimer== this.clock.getSecond()){
               for(int x=0;x<this.List_Fly.size();x++){
                    Fly littles_fly = (Fly)List_Fly.get(x); 
                    littles_fly.SetSpeed(40);
               }
           }
                        
     }
    
    private void LocatioFace(){
            if(this.MiraPlayerUno.getX()>400)
               this.Player.LeftFace();
            else
                this.Player.Pleft= false;
     
     }
    
    private boolean TimeOut(){
        if(this.clock.getSecond()> this.deadTime){
            if(this.ScoreO>this.Horda)
                this.Mesage ="You Win :3";  
            else
                 this.Mesage = "You are loser :/ " ;
            
            this.StopAll();
           timeOut= true; 
           return true;
       }else if((this.List_Fly.size()==0)){
           this.Mesage ="You Win LOL :3";  
           timeOut= true;
           this.StopAll();
           return true;
       }else
           return false;
       
    }
    
    private void StopAll(){
        this.clock.StopClock();
        this.Player.StopFrog();
        this.MiraPlayerUno.StopM();
        for(int x=0;x<this.List_Fly.size();x++){
           Fly LittleFly = (Fly) this.List_Fly.get(x);
           LittleFly.stopFly();
        }
    }
    
    public void keyTyped(KeyEvent e) { 
    }
    
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void keyPressed(KeyEvent e) {
    int Key = e.getKeyCode();
    
          if(Key == KeyEvent.VK_RIGHT){
              this.MiraPlayerUno.Right();
          }else if(Key==KeyEvent.VK_LEFT) {
              this.MiraPlayerUno.Left();
          }else if(Key==KeyEvent.VK_UP){
              this.MiraPlayerUno.Up();
          }else if(Key==KeyEvent.VK_DOWN){
              this.MiraPlayerUno.Down();
          
          }else if(Key==KeyEvent.VK_D) {
              this.MiraPlayerDos.Right();
          }else if(Key==KeyEvent.VK_A) {
              this.MiraPlayerDos.Left();
          }else if(Key==KeyEvent.VK_W){
              this.MiraPlayerDos.Up();
          }else if(Key==KeyEvent.VK_S){
              this.MiraPlayerDos.Down();
              
          }else if(Key==KeyEvent.VK_SPACE){
             this.CollisionDetector();
             this.Player.OpenMouth();
          }
    }

    private void CollisionDetector(){
        Rectangle M = this.MiraPlayerUno.Rectangulo();
        // Rectangle M = this.MiraPlayerDos.Rectangulo();
        
        for(int x=0;x<this.List_Fly.size();x++){
             Fly littles_fly = (Fly)List_Fly.get(x); 
             Rectangle RectFly = littles_fly.Rectangulo();
             if(RectFly.intersects(M)){
                 this.List_Fly.remove(x);
                 ScoreO++;
             }
        }
        if(this.ListSpecial.size()>0){
            Special Atta = (Special)this.ListSpecial.get(0);
            Rectangle RectSpe = Atta.Rectangulo();
            
            if(RectSpe.intersects(M)){
                this.TiggeredSpecial(Atta.GetSpecial());
                this.ListSpecial.remove(0);
                 System.err.println(this.ListSpecial.size());
               } 
        }
    }
    
    private void TiggeredSpecial(int num){
        if(num == 0){
           this.MakeBites(this.List_Fly.size()*2);
       }else if(num==1){
           this.ChangeSpeedbites();
        }else if(num==2){ 
           System.err.println("Mira má grande");
        }else if(num==3){
           this.ChangeSpeedbites();
        }else if(num==4){
            this.ChangeSpeedbites();
        }
    }
    
    private void ChangeSpeedbites(){
        for(int x=0;x<this.List_Fly.size();x++){
             Fly LittleMosca = (Fly)this.List_Fly.get(x);
             LittleMosca.SetSpeed(10);
            }
        this.SpeedFlys= true;
        AuxTimer = this.clock.getSecond()+3;
    }
    
    public void mouseClicked(MouseEvent e) {//Se llama cuando se oprime y se suelta un botón en el mouse.
    }
    
    public void mousePressed(MouseEvent e) { //Cuando se oprime el boton del mouse.
    }
    
    public void mouseReleased(MouseEvent e) {//Cuando se suelta el boton del mouse
    }
    
    public void mouseEntered(MouseEvent e) { // Ocurre cuando el cursor entra dentro de los límites del componente.
    }
    
    public void mouseExited(MouseEvent e) {
    }
}
