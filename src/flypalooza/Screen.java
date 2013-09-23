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
    private boolean GameLife= true;
    private ArrayList List_Fly;
    private final Color MyColor=new Color( 14, 60, 180);
    private int Score=0;
    private boolean scope= false;
    
    Clock clock = new Clock();
    Font font;
    Timer time;
    Point MyMouse = MouseInfo.getPointerInfo().getLocation();
    
    Random random = new Random();
    Frog Player = new Frog(this.GameLife);
    Scope MiraPlayerUno = new Scope(0,0);
    
    public Screen(){
    
    myBackground = new ImageIcon("Imagenes/Escenario.png");
    BackGround = myBackground.getImage();
    this.font = new Font("SansSerif",Font.BOLD,24);   
    List_Fly = new ArrayList();
    this.Player.start();
    this.MiraPlayerUno.start();
    
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
        
    }
     public void paint(Graphics g){
     
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g; //Pasamos G a Graphics 2D
     g.drawImage(this.BackGround,0,0, this);
     
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     
     g2.drawImage(this.Player.getimage(), this.Player.getX(),this.Player.getY() ,null);//Dibujamos al Jugador 
     
     g2.drawImage(this.MiraPlayerUno.getimage(),this.MiraPlayerUno.getX(), MiraPlayerUno.getY(),null);
     
     g2.drawString("Score : "+this.List_Fly.size(),160,40);
     g2.drawString("Timer : "+this.clock.getSecond(),300,40);  
      
     if(this.List_Fly.size()>0)
         for(int x=0;x<this.List_Fly.size();x++){
             Fly LittleMosca = (Fly)this.List_Fly.get(x);
             g2.drawImage(LittleMosca.getimage(),LittleMosca.getX(), LittleMosca.getY(),null);
         }

     }  
     
    
     public void actionPerformed(ActionEvent e) {
        repaint();

    }


    public void keyTyped(KeyEvent e) { 
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
          }else if(Key==KeyEvent.VK_SPACE){
             this.CollisionDetector();
          }
    }

    public void keyReleased(KeyEvent e) {
        
    }
    
    private void CollisionDetector(){
        
        for(int x=0;x<this.List_Fly.size();x++){
             Fly littles_fly = (Fly)List_Fly.get(x); 
             Rectangle RectFly = littles_fly.Rectangulo();
             Rectangle M = this.MiraPlayerUno.Rectangulo();
              
             if(RectFly.intersects(M)){
                 this.List_Fly.remove(x);
                 
             }
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
