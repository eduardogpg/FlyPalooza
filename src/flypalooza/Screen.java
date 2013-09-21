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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Eduardo
 */
public class Screen extends JPanel implements ActionListener,KeyListener{
    private Image BackGround;
    private ImageIcon myBackground;
    private boolean GameLife;
    private ArrayList List_Fly;
    private final Color MyColor=new Color( 14, 60, 180);
    private int Score=0;
    Clock clock = new Clock();
    Font font;
    Timer time;
    Point MyMouse = MouseInfo.getPointerInfo().getLocation();
    
    
    public Screen(){
    
    myBackground = new ImageIcon("Imagenes/FondoPrueba.jpg");
    BackGround = myBackground.getImage();
    this.font = new Font("SansSerif",Font.BOLD,24);   
    List_Fly = new ArrayList();
        time = new Timer(4,this); 
        time.start();
        this.clock.start();
        setFocusable(true);
        addKeyListener(this);
    }
    
    public void MakeBites(){
        Fly moscas = new Fly(200,400);
        moscas.start();
        this.List_Fly.add(moscas);
    }
     public void paint(Graphics g){
     
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g; //Pasamos G a Graphics 2D
     g.drawImage(this.BackGround,0,0, this);
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     
     //g2.drawString("Score : "+this.Score,160,40);
     g2.drawString("Score : "+this.List_Fly.size(),160,40);
     g2.drawString("Timer : "+this.clock.getSecond(),300,40);  
      
     MyMouse = MouseInfo.getPointerInfo().getLocation();
     g.fillRect(this.MyMouse.x-30, this.MyMouse.y-60, 50, 60);
     
     for(int x=0;x<this.List_Fly.size();x++){
        Fly littles_fly = (Fly)List_Fly.get(x); 
        g2.drawImage(littles_fly.getimage(), littles_fly.getX(), littles_fly.getY(), null); 
     }
     }  
     
    
     public void actionPerformed(ActionEvent e) {
        repaint();
        if(this.clock.getSecond()%2 == 0)
            this.MakeBites();
        
    }


    public void keyTyped(KeyEvent e) {
        
        
    }

    public void keyPressed(KeyEvent e) {
    int Key = e.getKeyCode();
    
        if(Key==KeyEvent.VK_S){
            }
    }


    public void keyReleased(KeyEvent e) {
        
    }
    
    private void CollisionDetector(){
        
        for(int x=0;x<this.List_Fly.size();x++){
             Fly littles_fly = (Fly)List_Fly.get(x); 
             Rectangle RectFly = littles_fly.Rectangulo();
             
             //if(RectFly.intersects()){
        }
    }
    
}
