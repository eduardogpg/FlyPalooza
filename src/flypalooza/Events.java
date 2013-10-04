/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.awt.Color;
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
    Audio audio;
    Audio audio2;
    Audio audio3;
    public static Audio audio4;
    
    ImageIcon buttonSinglePlayerPressed;
    ImageIcon buttonMultiPlayerPressed;
    ImageIcon buttonCreditsPressed;
    ImageIcon buttonRankingsPressed;
    ImageIcon buttonReturnCreditsPressed;
    ImageIcon buttonReturnSinglePlayerPressed;
    ImageIcon buttonReturnMultiPlayerPressed;
    ImageIcon closeRankingsImgPressed;
    
    public Events() throws Exception{
        audio = new Audio("Audio/Intro.wav");
        audio2 = new Audio("Audio/Credits.wav");
        audio3 = new Audio("Audio/lobi.wav");
        audio4 = new Audio("Audio/Nivel1.wav");
        audio.play();
    }
    
    public void changeFaces() throws InterruptedException{
        FlyPalooza.singleButton.addMouseListener(new MouseAdapter(){
            
            public void mousePressed(MouseEvent e){
                buttonSinglePlayerPressed = new ImageIcon("Menus/BSinglePlayer2.png");
                FlyPalooza.singleButton.setIcon(buttonSinglePlayerPressed);
            }
            public void mouseReleased(MouseEvent e){
                FlyPalooza.firstFace.setVisible(false);
                FlyPalooza.singlePlayerFace.setVisible(true);
                audio.stop();
                audio3.play();
            }
        });
        FlyPalooza.multiplayerButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                FlyPalooza.firstFace.setVisible(false);
                FlyPalooza.multiPlayerFace.setVisible(true);
                FlyPalooza.returnM.setIcon(FlyPalooza.returnMultiPalyerImg);
            }
             public void mouseReleased(MouseEvent e){
                buttonMultiPlayerPressed = new ImageIcon("Menus/BMultiPlayer2.png");
                FlyPalooza.multiplayerButton.setIcon(buttonMultiPlayerPressed);
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
        FlyPalooza.level1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                JFrame ventana = new JFrame();
                Screen screen = null;
                try {
                    screen = new Screen();
                } catch (Exception ex) {
                    Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
                }
                ventana.add(screen);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FlyPalooza.window.setVisible(false);
                ventana.setBounds(270,70,800,600);
                ventana.setVisible(true);
                audio4.play();
                audio3.stop();
            }
        });
    }
}
