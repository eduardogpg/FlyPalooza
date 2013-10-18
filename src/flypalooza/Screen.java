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
import java.awt.Toolkit;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


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
    private int TimeCreateSpecial = 0 ;
    private int Horda = 23;
    private int Level = 1;
    private int AuxTimer = 0;
    private int NumSp = 0;
    private int numPlayerFre=0;
    private int TimeCombo= -10;
    private int TimeCreateBoss = 0;
    private int winning_frog= 0;
       
    
    private boolean inGame= false;//It helps to control the time of the Draws
    private boolean reward = false;
     
    
    private boolean Combo= false;
    private boolean timeOut= false;
    private boolean SpecialBoolean = false;
    private boolean SpecialPush = false;
    private boolean loser = false;
    private boolean coperativo = true;
    
    private boolean LeveTwo= true;
    
    public static JLabel buttonReturnMainMenu;
    static public ImageIcon buttonReturnMainMenuImg;
    
    String Mesage = "Are you ready for Catch the Flyes in "+this.deadTime +" seconds ";
    
    Audio combo= new Audio("Audio/Combo.wav");
    Audio doubleFlyes = new Audio("Audio/DoubleFlyes.wav");
    Audio antiGraviti = new Audio("Audio/AntiGravity.wav");
    Audio superScope = new Audio("Audio/SuperScope.wav");
    Audio freeze = new Audio("Audio/Freeze.wav");
    
    Clock clock = new Clock();
    Font font;
    Timer time;
    
    
    Boss Brutus;
    
    
    Random random = new Random();
    Frog Player ;
    Frog PlayerTwo;
    Scope MiraPlayerUno;
    Scope MiraPlayerDos;
    
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("FlyPalooza/icono32.png"));
       return retValue;
    }
    public Screen(boolean Level,int num)throws Exception{
        Events.ventana.setIconImage(getIconImage());
        LeveTwo= Level;
        this.Level=num;
        
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
        
        //Functions
        this.MakeFirstPlayer();
        this.MakeSecondPlayer();
        this.MakeCloud();
        this.LoadImgFly();
        this.LoadImgLuci();
        
        
            time = new Timer(4,this); 
            time.start();
            this.clock.start();
            setFocusable(true);
            addKeyListener(this);
            addMouseListener(this);
            
            if((LeveTwo)&&(this.Level==1)){
                    this.ChangedForDifficult();
            }else{
                this.MakeBites(this.Horda,false);
            }

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
        this.TimeCreateSpecial = this.random.nextInt(10)+7; //It helps to create a special item
        if(!Luciernagas)
            this.TimeCreateBoss = this.random.nextInt(20)+1;
        
      
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
     Graphics2D g2 = (Graphics2D)g; //We pass g to g2D
     g.drawImage(this.BackGround,0,0, this);
      
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     
     if(reward){
         g2.drawImage(this.Premiasion,100,350, null);
            if(this.winning_frog==1){
                g2.drawImage(this.Player.getimage(), 300,220 ,null); 
                g2.drawImage(this.PlayerTwo.getimage(), 100,300 ,null);
                
            }else{
                 g2.drawImage(this.PlayerTwo.getimage(), 300,220 ,null);
                 g2.drawImage(this.Player.getimage(), 100,300 ,null);   
        }
          g2.drawString("Score Player One : "+this.Player.ScoreFrog,80,40);
          g2.drawString("Score Player Two : "+this.PlayerTwo.ScoreFrog,400,40);
     }else{
     
     
        if(!this.reward)
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
                       g2.drawString("<html><h1>GOOOOOOO</h1></html>",240,120);

           }
       if(this.Level!=0){
           
               g2.drawString("Score Player One : "+this.Player.ScoreFrog,80,40);
               g2.drawString("Score Player Two : "+this.PlayerTwo.ScoreFrog,400,40);

               g2.drawString("Time : "+this.clock.getSecond(),300,80);  
        }
        if((booelanBoss)&&(DrawBoos))
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
    private boolean DrawBoos= true;
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
             TimeCreateSpecial= 0;//we place the tima for not create another level
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
        timerCreatMoreFly= timeForM;
        myBackground = new ImageIcon("Imagenes/Escenario2.png");
        BackGround = myBackground.getImage();

        level2 = new Audio("Audio/Nivel2.wav");
        level2.play();
        Events.audio4.stop();
        if(this.ListSpecial.size()>0)
            this.ListSpecial.remove(0);
        this.Horda=22;
        this.MakeBites(this.Horda,true);
        
        for(int x=0;x<this.listClouds.size();x++){
            this.listClouds.remove(x);
        }
    }
   boolean Activado= true;
    private void awards() throws Exception{
            Audio price = new Audio("Audio/Credits.wav");
            level2.stop();
            price.play();
            this.reward= true;
            this.clock.Restart();
            TimerforBack= this.clock.getSecond()+10;
            
          myBackground = new ImageIcon("Imagenes/Ganador.png");
          BackGround = myBackground.getImage();
          
          this.IconPremiasion = new ImageIcon("Imagenes/Podium2.png");
            this.Premiasion = this.IconPremiasion.getImage();
        
            if(this.Player.ScoreFrog>this.PlayerTwo.ScoreFrog)
                this.winning_frog= 1;
             else
                this.winning_frog= 2;
            this.clock.Restart();
    }
    Audio begin = new Audio("Audio/starPlay.wav");;
    boolean sonBoss=false;
    
    int timeForM=40;
    int timerCreatMoreFly= timeForM;
    int TimerforBack= 0;
    
  
    boolean Lala = false;
    private void Sinapsis() throws Exception{
        
        if((this.reward)&&(TimerforBack==this.clock.getSecond())){
            System.exit(0);
        }
        if((this.loser)&&(TimerforBack==this.clock.getSecond())){
            System.exit(0);
        }
        
        if((this.timerCreatMoreFly==this.clock.getSecond())&&(this.clock.getSecond()<120)){
            this.timerCreatMoreFly+=5;
            if(this.Level==1)
                this.MakeBites(10,false);
            else
                this.MakeBites(10,true);
        }
        if((this.TimeCreateBoss==this.clock.getSecond())&&(booelanBoss==false)){
            this.MakeBoos();
            begin.stop();
            TimeCreateBoss=0;
        }

        
        if(((this.Level==0)||(this.Level==1)||(this.Level==2))&&(this.clock.getSecond()==5) &&(inGame==false)){
            begin.play();
            this.clock.Restart();
            this.Level++;
             
             if(Activado){
                MiraPlayerUno.start();
                MiraPlayerDos.start();
                Activado= false;
             }
                if(Level==2)
                    if(!this.LeveTwo)
                        this.ChangedForDifficult();
             inGame= true;
        }
        
       
       if((this.NextLevel())&&(this.inGame)){      
           this.clock.Restart();
           this.timeOut= true;
           inGame= false;
           if(this.Level==2)
               this.awards();
       }else{
           if(this.clock.getSecond()>this.deadTime)
           {this.StopAll();
           this.timeOut= true;
           this.Mesage="You Lose";
           inGame= false;
           loser= true;
           Level=0;
           }
       }//We comprove if there are another special
       if(SpecialPush){
           if(NumSp==1){
                      if(this.clock.getSecond()==this.AuxTimer){//we pplace the speed that before we take out
                        for(int x=0;x<this.List_Fly.size();x++){
                             Fly littles_fly = (Fly)List_Fly.get(x); 
                             littles_fly.SetSpeed(40);
                            }
                       }else{
                          if(this.AuxTimer<this.clock.getSecond())
                              this.SpecialPush= false;
                      }
           }else if(NumSp==3){//To take off the speed to the another player
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

       }//We go out to the specials
             
       if((this.TimeCombo==this.clock.getSecond())&&(this.booelanBoss)){
           this.booelanBoss= false;
           this.DestroBrutus();
       }
     repaint();
    }
    private boolean NoBite= false;
    
    private void bigerScope(Scope myMira){
              if(this.clock.getSecond()< this.AuxTimer)
                  myMira.ChangeforBigImg(2);
               else{
                       myMira.ChangeforBigImg(0);
                       SpecialPush=false; this.NumSp= 100;//We place false here and nt in sinapsis
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
          }else if(!NoBite){   
                 if(Key==KeyEvent.VK_SPACE){

                          this.CollisionDetector(this.MiraPlayerUno,this.Player);
                         if(!reward)
                            this.Player.OpenMouth();

                }else if(Key== KeyEvent.VK_G){

                      if(!reward )
                      this.PlayerTwo.OpenMouth();
                      this.CollisionDetector(this.MiraPlayerDos,this.PlayerTwo);
                   }
          }
    }
    

    
    private void CollisionDetector(Scope mira, Frog littleFrog){
        Rectangle p1 = mira.Rectangulo();

        for(int x=0;x<this.List_Fly.size();x++){//Just flyies
             Fly littles_fly = (Fly)List_Fly.get(x); 
             Rectangle RectFly = littles_fly.Rectangulo();
             if(RectFly.intersects(p1)){
                 this.List_Fly.remove(x);
                 littleFrog.ScoreFrog++;
               
             }
        }
        if(this.ListSpecial.size()>0){//Specials
            Special Atta = (Special)this.ListSpecial.get(0);
            Rectangle RectSpe = Atta.Rectangulo();
            if(RectSpe.intersects(p1)){
                NumSp = Atta.GetSpecial();
                this.TiggeredSpecial(this.NumSp,mira.numberMi);
                this.ListSpecial.remove(0);
                
               } 
        }
        
        if(this.booelanBoss){
            Rectangle recBoos = this.Brutus.Rectangulo();
            if((p1.intersects(recBoos))&&(!NoBite)){
                this.Brutus.ScorepLiefe-=10;
                
                if(this.Brutus.ScorepLiefe==0){
                     littleFrog.ScoreFrog+=5;
                     this.Combo= true;
                     this.TimeCombo= this.clock.getSecond()+1;
                     NoBite= true;
                }
            }
        }
       
    }
    
    
    private void TiggeredSpecial(int num,int player){
         this.SpecialPush= true;
          
       if(num == 0){//Double Flyies
           doubleFlyes.play();
           if(this.Level==1)
               this.MakeBites(this.List_Fly.size()*2,false);
           else
               this.MakeBites(this.List_Fly.size()*2,true);
           
           AuxTimer = this.clock.getSecond()+3;
       
       }else if(num==1){//Freeze Flyies
           antiGraviti.play();
           this.ChangeSpeedbites();
           AuxTimer = this.clock.getSecond()+3;
        
       }else if(num==2){ //Super  Scope
           superScope.play();
          AuxTimer = this.clock.getSecond()+5;
          this.numPlayerFre = player;
          
        }else if(num==3){//Freeze partner
            freeze.play();
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
    
    private void DestroBrutus(){
        System.err.print("Mensaje");
        combo.play();
        NoBite= false;
    }
    public void mouseClicked(MouseEvent e) {//It calls when the mouse is clicked
    }
    
    public void mousePressed(MouseEvent e) { //It calls when the mouse is pressed
    }
    
    public void mouseReleased(MouseEvent e) {//It calls when the button of the mouse is released
    }
    
    public void mouseEntered(MouseEvent e) { //It calls when the cursor of the mouse is entered 
    }
    
    public void mouseExited(MouseEvent e) {//It calls when the cursor of the mouse is exited
    }
}
