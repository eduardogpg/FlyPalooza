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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
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
    Timer time;
    private int Score=0;
    
    private final Color MyColor=new Color( 14, 60, 180);
    Font font;
    
    Fly moscas = new Fly(100,200);
    
    public Screen(){
    
    myBackground = new ImageIcon("Imagenes/FondoPrueba.jpg");
    BackGround = myBackground.getImage();
    this.font = new Font("SansSerif",Font.BOLD,24);   
        time = new Timer(4,this); 
        time.start();
        setFocusable(true);
        addKeyListener(this);
    }
    
     public void paint(Graphics g){
     
     super.paint(g);
     Graphics2D g2 = (Graphics2D)g;
     g.drawImage(this.BackGround,0,0, this);
     
     g2.setFont(font);
     g2.setColor(this.MyColor);
     g2.drawString("Score: "+this.Score,160,100);
     
     g2.drawImage(this.moscas.getimage(),this.moscas.getX(), this.moscas.getY(),null);
     } 
    public void actionPerformed(ActionEvent e) {
        repaint();
        
    }


    public void keyTyped(KeyEvent e) {
        
        
    }

    public void keyPressed(KeyEvent e) {
    int Key = e.getKeyCode();
    
    if(Key==KeyEvent.VK_S){
        System.err.println("lalal");
        this.moscas.start();
      }
    }


    public void keyReleased(KeyEvent e) {
        
    }
    
}
