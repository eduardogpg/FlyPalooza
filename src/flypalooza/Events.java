/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Christian
 */
public class Events{
    boolean estadoBotonServer = false;
    boolean estadoBotonCliente = false;
    boolean Coperativo = false;
    
    Audio audio;
    Audio audio2;
    Audio audio3;
    public static Audio audio4;
    public static Audio audio5;
    
    ImageIcon buttonSinglePlayerPressed;
    ImageIcon buttonMultiPlayerPressed;
    ImageIcon buttonCreditsPressed;
    ImageIcon buttonRankingsPressed;
    ImageIcon buttonReturnCreditsPressed;
    ImageIcon buttonReturnSinglePlayerPressed;
    ImageIcon buttonReturnMultiPlayerPressed;
    ImageIcon closeRankingsImgPressed;
    public static JFrame ventana = new JFrame();
    public static Screen screen = null;
    public Events() throws Exception{
        audio = new Audio("Audio/Intro.wav");
        audio2 = new Audio("Audio/Credits.wav");
        audio3 = new Audio("Audio/lobi.wav");
        audio4 = new Audio("Audio/Nivel1.wav");
        audio5 = new Audio("Audio/startPlay.wav");
        audio.play();
    }
    
    public void changeFaces() throws InterruptedException{
        FlyPalooza.singleButton.addMouseListener(new MouseAdapter(){
            
            public void mousePressed(MouseEvent e){
                buttonSinglePlayerPressed = new ImageIcon("Menus/BMultiPlayer2.png");
                FlyPalooza.singleButton.setIcon(buttonSinglePlayerPressed);
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.firstFace.setVisible(false);
                FlyPalooza.singlePlayerFace.setVisible(true);
                audio.stop();
                audio3.play();
            }
        });
        FlyPalooza.creditsButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                buttonCreditsPressed = new ImageIcon("Menus/BCredits2.png");
                FlyPalooza.creditsButton.setIcon(buttonCreditsPressed);
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.firstFace.setVisible(false);
                FlyPalooza.creditsFace.setVisible(true);
                audio.stop();
                audio2.play();
            }
        });
        FlyPalooza.returnSinglePlayerButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                FlyPalooza.returnSinglePlayerButton.setBackground(Color.cyan);
            }
            public void mouseExited(MouseEvent e){
                FlyPalooza.returnSinglePlayerButton.setBackground(Color.black);
            }
            public void mousePressed(MouseEvent e){
                buttonReturnSinglePlayerPressed = new ImageIcon("Menus/BBack2.png");
                FlyPalooza.returnSinglePlayerButton.setIcon(buttonReturnSinglePlayerPressed);
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.singlePlayerFace.setVisible(false);
                FlyPalooza.firstFace.setVisible(true);
                audio3.stop();
                audio.play();
                FlyPalooza.singleButton.setIcon(FlyPalooza.singleButtonImg);
                FlyPalooza.returnSinglePlayerButton.setIcon(FlyPalooza.returnSiglePalyerImg);
            }
        });
        
        FlyPalooza.returnCreditsButton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                FlyPalooza.returnCreditsButton.setBackground(Color.cyan);
            }
            public void mouseExited(MouseEvent e){
                FlyPalooza.returnCreditsButton.setBackground(Color.black);
            }
            public void mousePressed(MouseEvent e){
                buttonReturnCreditsPressed = new ImageIcon("Menus/BBack2.png");
                FlyPalooza.returnCreditsButton.setIcon(buttonReturnCreditsPressed);
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.creditsFace.setVisible(false);
                FlyPalooza.firstFace.setVisible(true);
                audio2.stop();
                audio.play();
                FlyPalooza.creditsButton.setIcon(FlyPalooza.creditsImg);
                FlyPalooza.returnCreditsButton.setIcon(FlyPalooza.returnCreditsIMG);
            }
        });
        FlyPalooza.returnM.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                buttonReturnMultiPlayerPressed = new ImageIcon("Menus/BBack2.png");
                FlyPalooza.returnM.setIcon(buttonReturnMultiPlayerPressed);
                FlyPalooza.multiplayerButton.setIcon(FlyPalooza.multiplayerButtonImg);
                audio.play();
                audio3.stop();
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.firstFace.setVisible(true);
            }
        });
        FlyPalooza.level1.addMouseListener(new MouseAdapter(){//Levels of the game
            public void mousePressed(MouseEvent e){
                Coperativo= false;
                
                try {
                    screen = new Screen(false,0);
                } catch (Exception ex) {
                    Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
                }
                ventana.add(screen);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FlyPalooza.window.setVisible(false);
                ventana.setBounds(270,70,800,600);
                ventana.setVisible(true);
                if(!Coperativo)
                    audio4.play();
                
                audio5.play();
                audio3.stop();
            }
        });
        FlyPalooza.level2.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){    try {
                    screen = new Screen(true,1);
                } catch (Exception ex) {
                    Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
                }
                ventana.add(screen);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FlyPalooza.window.setVisible(false);
                ventana.setBounds(270,70,800,600);
                ventana.setVisible(true);
                if(Coperativo)
                    audio4.play();
                
                audio5.play();
                audio3.stop();
            }
        });
    }
    
    
}
