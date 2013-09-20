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
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Eduardo
 */
public class Screen extends JPanel implements ActionListener,KeyListener{
    private Image BackGround;
    private ImageIcon myBackground;
    private boolean GameLife;
    
    private int Score=0;
    
    private final Color MyColor=new Color( 14, 60, 180);
    Font font;
    
    public Screen(){
    
    myBackground = new ImageIcon("Imagenes/FondoPrueba.jpg");
    BackGround = myBackground.getImage();
    this.font = new Font("SansSerif",Font.BOLD,24);    

    }
     public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g.drawImage(this.BackGround,0,0, this);
     
          g2.setFont(font);
          g2.setColor(this.MyColor);
          g2.drawString("Score: "+this.Score,160,100);
          
     } 
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
