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
import java.util.logging.Logger;
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
  
    private ImageIcon IconGrass;
    private Image Grass;
 
    private Image Premiasion;
    private ImageIcon IconPremiasion;
    
    private ArrayList List_Fly;
    private ArrayList ListSpecial;
    private ArrayList ListPathP1;
    private ArrayList listClouds;
    private ArrayList ListPathP2;
    private ArrayList pathfly;
    private ArrayList pathLuciernagas;
    
    private final Color MyColor=new Color( 14, 60, 180);

    private int deadTime = 120;
    private int TimeCreateSpecial =0 ;
    private int Horda = 20;
    private int Level = 0;
    private int AuxTimer = 0;
    private int NumSp = 0;
    private int numPlayerFre=0;
    private int TimeCombo= -10;
    private int TimeCreateBoss = 0;
    
    
    private boolean Combo= false;
    private boolean timeOut= false;
    private boolean SpecialBoolean = false;
    private boolean SpecialPush = false;
    private boolean loser = false;
    private boolean coperativo = true;
    
    String Mesage = "You Ready fot Cath "+this.Horda+ " in "+this.deadTime +" seconds ";
    
    Clock clock = new Clock();
    Font font;
    Timer time;
    
    
    Boss Brutus;
    //Point MyMouse = MouseInfo.getPointerInfo().getLocation();
    
    Random random = new Random();
    Frog Player ;
    Frog PlayerTwo;
    Scope MiraPlayerUno;
    Scope MiraPlayerDos;
    
    public Screen()throws Exception{
    
        myBackground = new ImageIcon("Imagenes/Escenario.png");
        BackGround = myBackground.getImage();
        this.font = new Font("SansSerif",Font.BOLD,24);   

        this.IconGrass = new ImageIcon("Imagenes/pasto.png");
        this.Grass = this.IconGrass.getImage();
        
        
        this.List_Fly = new ArrayList();
        this.ListPathP1 = new ArrayList();
        this.ListPathP2 = new ArrayList();
        this.ListSpecial = new ArrayList();
        this.listClouds= new ArrayList();
        
        //Funciones 
        this.MakeFirstPlayer();
        this.MakeSecondPlayer();
        this.MakeCloud();
        this.LoadImgFly();
        this.LoadImgLuci();
        this.MakeBites(this.Horda,false);
        
            time = new Timer(4,this); 
            time.start();
            this.clock.start();
            setFocusable(true);
            addKeyListener(this);
            addMouseListener(this);

    }        
    
  
    public void MakeBites(int horda,boolean Luciernagas){
        
        for(int x=0; x<horda;x++){
            int Cx = this.random.nextInt(700)+1;
            int Cy = this.random.nextInt(400)+1;
            if(!Luciernagas){
                Fly moscas = new Fly(Cx,Cy, this.pathfly);
                moscas.start();
                this.List_Fly.add(moscas);
            }else{
                Fly moscas = new Fly(Cx,Cy, this.pathLuciernagas);
                moscas.start();
                this.List_Fly.add(moscas);              
            }
         }
        this.TimeCreateSpecial = this.random.nextInt(10)+1; //Me ayuda a crear un Item Especial
        if(!Luciernagas)
            this.TimeCreateBoss = this.random.nextInt(20)+1;
        System.err.println(this.TimeCreateBoss);
      
    }
    
    private void LoadImgFly(){
       pathfly = new ArrayList();
       this.pathfly.add("Imagenes/Moscas/1.png");//0
       this.pathfly.add("Imagenes/Moscas/2.png");//1
       this.pathfly.add("Imagenes/Moscas/3.png");//3
       this.pathfly.add("Imagenes/Moscas/4.png");//4
       this.pathfly.add("Imagenes/Moscas/5.png");//5
       this.pathfly.add("Imagenes/Moscas/6.png");//6
    
    }
    
    private void LoadImgLuci(){
       pathLuciernagas = new ArrayList();
       this.pathLuciernagas.add("Imagenes/Luciernaga/1.png");//7
       this.pathLuciernagas.add("Imagenes/Luciernaga/2.png");//8
       this.pathLuciernagas.add("Imagenes/Luciernaga/3.png");//9
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
    
    private boolean  booelanBoss= false;
    Audio bossSong ;
    
    private void MakeBoos() throws Exception{
        if(this.Level==1){   
            System.err.println("Entro para el jefe");
        int w = this.random.nextInt(600);
            int y= this.random.nextInt(100);
            this.Brutus = new Boss(w,y);
            Brutus.start();
            booelanBoss= true;
        }
      }
    
    private void MakeFirstPlayer(){
        
       this.ListPathP1.add("Imagenes/RanaUno/1.png"); //0
       this.ListPathP1.add("Imagenes/RanaUno/2.png"); //1
       this.ListPathP1.add("Imagenes/RanaUno/3.png"); //2
      
        this.Player = new Frog(true, this.ListPathP1,50,380);
        this.Player.start();
        this.MiraPlayerUno = new Scope(300,300,1);
        
    }
    
    private void MakeSecondPlayer(){
        
        
       this.ListPathP2.add("Imagenes/RanaDos/1b.png"); //0
       this.ListPathP2.add("Imagenes/RanaDos/2b.png"); //1
       this.ListPathP2.add("Imagenes/RanaDos/3b.png"); //2
       this.ListPathP2.add("Imagenes/RanaDos/1b.png"); //3
  
        this.PlayerTwo = new Frog(true, this.ListPathP2,580,330);
        this.PlayerTwo.start();
        this.MiraPlayerDos= new Scope(400,300,2);
        
    }
    
    public void paint(Graphics g){
     
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g; //Pasamos G a Graphics 2D
     g.drawImage(this.BackGround,0,0, this);
      
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     
     if(Premio){
         g2.drawImage(this.Premiasion,100,350, null);
            if(this.RanitaGandatora==1){
                g2.drawImage(this.Player.getimage(), 300,220 ,null); 
                g2.drawImage(this.PlayerTwo.getimage(), 100,300 ,null);
                
            }else{
                 g2.drawImage(this.PlayerTwo.getimage(), 300,220 ,null);
                 g2.drawImage(this.Player.getimage(), 100,300 ,null);   
        }
          g2.drawString("Score Player One : "+this.Player.ScoreFrog,80,40);
          g2.drawString("Score Player Two : "+this.PlayerTwo.ScoreFrog,400,40);
     }else{
     
     
        if(Level!=2)
           g2.drawImage(this.Grass,0,200, null);
        if(this.coperativo){
           g2.drawImage(this.Player.getimage(), this.Player.getX(),this.Player.getY() ,null);
           g2.drawImage(this.PlayerTwo.getimage(), this.PlayerTwo.getX(),this.PlayerTwo.getY() ,null);
        }
        if((Level!=2) || this.loser)
           for(int x=0;x<this.listClouds.size();x++){
                     Cloud myCloud =(Cloud) this.listClouds.get(x);
                     g2.drawImage(myCloud.getimage(),myCloud.getX(), myCloud.getY(),null);
            }

           g2.drawImage(this.MiraPlayerUno.getimage(),this.MiraPlayerUno.getX(), MiraPlayerUno.getY(),null);
           g2.drawImage(this.MiraPlayerDos.getimage(),this.MiraPlayerDos.getX(), MiraPlayerDos.getY(),null);

         if(((this.Level==0)||(this.Level==1))&&(this.clock.getSecond()<4) &&(inGame==false)){
               g2.drawString(this.Mesage +this.clock.getSecond(),180,120);  

           }else if(loser==true)
                g2.drawString(this.Mesage,300,80);
           else{
                  if((this.clock.getSecond()==4)&&((this.Level==0)||(this.Level==1)) && this.inGame==false)
                       g2.drawString("GOOOOOO",240,120);

           }
       if(this.Level!=0){
               g2.drawString("Score Player One : "+this.Player.ScoreFrog,80,40);
               g2.drawString("Score Player Two : "+this.PlayerTwo.ScoreFrog,400,40);

               g2.drawString("Timer : "+this.clock.getSecond(),300,80);  
        }
        if(booelanBoss)
            g2.drawImage(this.Brutus.getimage(),this.Brutus.getX(), Brutus.getY(),null);


        if(this.List_Fly.size()>0)
            for(int x=0;x<this.List_Fly.size();x++){
                Fly LittleMosca = (Fly)this.List_Fly.get(x);
                g2.drawImage(LittleMosca.getimage(),LittleMosca.getX(), LittleMosca.getY(),null);
            }

           if( this.ListSpecial.size()>0){
               Special Att =(Special) this.ListSpecial.get(0);
               g2.drawImage(Att.getimage(),Att.getX(), Att.getY(),null);

               if(Att.getY()>800){
                 this.ListSpecial.remove(0);
                 Att.stop();
               }
           }
     }
     }
    public void actionPerformed(ActionEvent e) {
        repaint();
        try {
            this.Sinapsis();
        } catch (Exception ex) {
            Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.CheckSpecial(); 
    }
   
    int coZ = 0;//Me ayuda a controlar los especiales
    private void CheckSpecial(){
         if(this.TimeCreateSpecial== this.clock.getSecond()){
              this.SpecialBoolean= true;
              this.coZ++;
         }
         if((this.SpecialBoolean)&& (this.coZ==1)){
             Special Attac = new Special();
             Attac.start();
             this.ListSpecial.add(Attac);
             SpecialBoolean= false;
             TimeCreateSpecial= 0;//Coloco el tiempo para que no se vuelva a crear otro especial en el nivel
         }
      
      }
    
    int NewTimerforNextHorda=0;
    private boolean NextLevel(){
        if(this.List_Fly.size()==0)
            return true;
        else
            return false;
    }
    
    Audio level2;
    private void ChangedForDifficult() throws Exception{
        myBackground = new ImageIcon("Imagenes/Escenario2.png");
        BackGround = myBackground.getImage();
        
        level2 = new Audio("Audio/Nivel2.wav");
        level2.play();
        Events.audio4.stop();
        if(this.ListSpecial.size()>0)
            this.ListSpecial.remove(0);
        this.Horda=20;
        this.MakeBites(this.Horda,true);
        
        for(int x=0;x<this.listClouds.size();x++){
            this.listClouds.remove(x);
        }
    }
    
    //Cambiar estas variables Arriba
    private boolean inGame= false;//Me ayuda a poder Controlar los tiempos de Dibujo de las sentencias
    private boolean Premio = false;
    private int RanitaGandatora= 0;    
    
    private void Premiasion() throws Exception{
            Audio price = new Audio("Audio/Credits.wav");
            level2.stop();
            price.play();
            this.Premio= true;
            myBackground = new ImageIcon("Imagenes/Ganador.png");
            BackGround = myBackground.getImage();
            
            this.IconPremiasion = new ImageIcon("Imagenes/Podium2.png");
            this.Premiasion = this.IconPremiasion.getImage();
        
            if(this.Player.ScoreFrog>this.PlayerTwo.ScoreFrog)
                this.RanitaGandatora= 1;
             else
                this.RanitaGandatora= 2;
       }
    Audio begin = new Audio("Audio/starPlay.wav");;
    boolean sonBoss=false;
    
    private void Sinapsis() throws Exception{
        if((this.TimeCreateBoss==this.clock.getSecond())&&(booelanBoss==false)){
            this.MakeBoos();
            begin.stop();
            System.err.println("Entro a terminar la musica");
        }
        
        if(((this.Level==0)||(this.Level==1))&&(this.clock.getSecond()==5) &&(inGame==false)){
            begin.play();
            this.clock.Restart();
             this.Level++;
                if(this.Level==1){
                   MiraPlayerUno.start();
                   MiraPlayerDos.start();
                }
                if(Level==2)
                    this.ChangedForDifficult();
             inGame= true;
        }
        
       if((TimeCombo == this.clock.getSecond())&& (this.booelanBoss))
           this.booelanBoss= false;
      
       
       if((this.NextLevel())&&(this.inGame)){      
           this.clock.Restart();
           this.timeOut= true;
           inGame= false;
           if(this.Level==2)
               this.Premiasion();
       }else{
           if(this.clock.getSecond()>this.deadTime)
           {this.StopAll();
           this.timeOut= true;
           this.Mesage="Perdieron los dos";
           inGame= false;
           loser= true;
           Level=0;
           }
       }//Comprobamos si hay algun Especial
       if(SpecialPush){
           if(NumSp==1){
                      if(this.clock.getSecond()==this.AuxTimer){//Reanudamos la velocidad que anterior mente le quitamos
                        for(int x=0;x<this.List_Fly.size();x++){
                             Fly littles_fly = (Fly)List_Fly.get(x); 
                             littles_fly.SetSpeed(40);
                            }
                       }else{
                          if(this.AuxTimer<this.clock.getSecond())
                              this.SpecialPush= false;
                      }
           }else if(NumSp==3){//Para quitarle velociada al contrincante
               if(this.numPlayerFre==1)
                    this.SleepPlayer(MiraPlayerDos);
               else
                   this.SleepPlayer(MiraPlayerUno);
               
           }else if(this.NumSp==2){//Hacer la mira más grande
               if(this.numPlayerFre==1)
                   this.bigerScope(MiraPlayerUno);
               else
                   this.bigerScope(MiraPlayerDos);
            }

       }//Salimos de los especiales 
       
     }
    
    private void bigerScope(Scope myMira){
              if(this.clock.getSecond()< this.AuxTimer)
                  myMira.ChangeforBigImg(2);
               else{
                       myMira.ChangeforBigImg(0);
                       SpecialPush=false; this.NumSp= 100;//Coloco aqui el false y no en Sinapsi por La facilidad de manejo
                   }
    }
    
    private void SleepPlayer(Scope Nplayer){
                   if(this.clock.getSecond()< this.AuxTimer)
                        Nplayer.getSpeed(0);
                   else{
                       Nplayer.getSpeed(20);
                       Nplayer.Left();
                       SpecialPush=false; this.NumSp= 100;
                   }
               
    }
    
    private void StopAll(){
        this.clock.StopClock();
        this.Player.StopFrog();
        this.PlayerTwo.StopFrog(); 
        this.MiraPlayerUno.StopM();
        this.MiraPlayerDos.StopM();
        for(int x=0;x<this.List_Fly.size();x++){
           Fly LittleFly = (Fly) this.List_Fly.get(x);
           LittleFly.stopFly();
        }
        for(int x=0;x<listClouds.size();x++){
            Cloud nube =(Cloud) this.listClouds.get(x);
                nube.setBHFalse();
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
             this.CollisionDetector(this.MiraPlayerUno,this.Player);
             if(!Premio)
                this.Player.OpenMouth();
             
          }else if(Key== KeyEvent.VK_G){
              if(!Premio )
              this.PlayerTwo.OpenMouth();
              this.CollisionDetector(this.MiraPlayerDos,this.PlayerTwo);
          
          }
    }
    
    private void CollisionDetector(Scope mira, Frog littleFrog){
             
        Rectangle p1 = mira.Rectangulo();

        for(int x=0;x<this.List_Fly.size();x++){//Solo Moscas y Moscas de noche
             Fly littles_fly = (Fly)List_Fly.get(x); 
             Rectangle RectFly = littles_fly.Rectangulo();
             if(RectFly.intersects(p1)){
                 this.List_Fly.remove(x);
                 littleFrog.ScoreFrog++;
               
             }
        }
        if(this.ListSpecial.size()>0){//Especiales
            Special Atta = (Special)this.ListSpecial.get(0);
            Rectangle RectSpe = Atta.Rectangulo();
            if(RectSpe.intersects(p1)){
                NumSp = Atta.GetSpecial();
                this.TiggeredSpecial(this.NumSp,mira.numberMi);
                this.ListSpecial.remove(0);
                
               } 
        }
        if(this.booelanBoss){
            Rectangle RectBoss = this.Brutus.Rectangulo();
             if((this.Brutus.getLife()==false)){
                            Combo= true;
                            littleFrog.ScoreFrog+=50;
                            TimeCombo = this.clock.getSecond()+1;
                           
                 }else{
                     if(RectBoss.intersects(p1)){
                        this.Brutus.ScorepLiefe-=20;
                 }
             }
                        
        }
            
    }
    
    private void TiggeredSpecial(int num,int player){
         this.SpecialPush= true;
          
       if(num == 0){//Moscas al Doble
           if(this.Level==1)
                this.MakeBites(this.List_Fly.size()*2,false);
           else
               this.MakeBites(this.List_Fly.size()*2,true);
           
           AuxTimer = this.clock.getSecond()+3;
       
       }else if(num==1){//Congelar Moscas
           this.ChangeSpeedbites();
           AuxTimer = this.clock.getSecond()+3;
        
       }else if(num==2){ //Mira más grande
          AuxTimer = this.clock.getSecond()+5;
          this.numPlayerFre = player;
          
        }else if(num==3){//Compañero Congelado
            AuxTimer = this.clock.getSecond()+5;
           this.numPlayerFre = player;
        }
    }
    
    private void ChangeSpeedbites(){
        for(int x=0;x<this.List_Fly.size();x++){
             Fly LittleMosca = (Fly)this.List_Fly.get(x);
             LittleMosca.SetSpeed(10);
            }
       
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
