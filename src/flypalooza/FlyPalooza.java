package flypalooza;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public final class FlyPalooza extends JFrame implements Runnable{
    //Pruebas 
    public static JTextField ip2;
    public static JTextArea recibir;
    public static JTextField enviar;
    public static JButton conectar;
    public static JButton servidorIni;
    
    //Faces
    public static JLabel firstFace;
    public static JLabel singlePlayerFace;
    public static JLabel multiPlayerFace;
    public static JLabel creditsFace;
    public static JLabel credits;
    
    
    //Buttons
    public static JLabel singleButton;
    public static JLabel multiplayerButton;
    public static JLabel rankingButton;
    public static JLabel creditsButton;
    public static JLabel returnCreditsButton;
    public static JLabel returnSinglePlayerButton;
    public static JLabel returnMultiPlayerButton;
    public static JLabel returnMultiPlayerButton2;
    public static JLabel returnM;
    public static JLabel closeRankings;
    public static JLabel joinMatch;
    public static JLabel createMatch;
    public static JLabel tableRankings;
    
    public static JLabel level1;
    public static JLabel level1M;
    
    public static JLabel level2;
    public static JLabel level2M;
    
    //Objects
    public static JTextField ip;
    Cursor cursor = new Cursor(12);
    
    //Images    
    static public ImageIcon background;
    static public ImageIcon backgroundCredits;
    static public ImageIcon singleButtonImg;
    static public ImageIcon multiplayerButtonImg;
    
    static public ImageIcon creditsImg;
    static public ImageIcon backgroundCreditsImg;
    static public ImageIcon returnCreditsIMG;
    static public ImageIcon returnSiglePalyerImg;
    static public ImageIcon returnMultiPalyerImg;
    
    static public ImageIcon singlePlayerImg;
    static public ImageIcon multiPlayerImg;
    
    @Override
    public void run(){
        this.setBounds(270, 70, 800, 600);
    }
    
    @Override
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("FlyPalooza/icono32.png"));
       return retValue;
    }

    //Constructor of the principal window
    public FlyPalooza() throws InterruptedException{
        super("--------------------------------------------------------Fly Palooza------------------------------------");
        
        getIconImage();
        Container container = getContentPane();
        container.setLayout(null);
        this.setIconImage(getIconImage());
        this.setBounds(270,70,799,599);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(container);
        
        firstFace = new JLabel();
        firstFace.setBounds(0, 0, 800, 600);
        firstFace.setVisible(true);
        firstFace.setLayout(null);
        background = new ImageIcon("Menus/Inicio.png");
        firstFace.setIcon(background);
        container.add(firstFace);
        
        
        creditsFace = new JLabel();
        creditsFace.setBounds(0, 0, 800, 600);
        creditsFace.setVisible(false);
        creditsFace.setLayout(null);
        backgroundCreditsImg = new ImageIcon("Menus/Credits.png");
        creditsFace.setIcon(backgroundCreditsImg);
        container.add(creditsFace);
        
        singlePlayerFace = new JLabel();
        singlePlayerFace.setBounds(0, 0, 800, 600);
        singlePlayerFace.setVisible(false);
        singlePlayerFace.setLayout(null);
        singlePlayerImg = new ImageIcon("Menus/Lobby.png");
        singlePlayerFace.setIcon(singlePlayerImg);
        container.add(singlePlayerFace);
        
        multiPlayerFace = new JLabel();
        multiPlayerFace.setBounds(0, 0, 800, 600);
        multiPlayerFace.setVisible(false);
        multiPlayerFace.setLayout(null);
        multiPlayerImg = new ImageIcon("Menus/Lobby.png");
        multiPlayerFace.setIcon(multiPlayerImg);
        container.add(multiPlayerFace);
        
        singleButton = new JLabel();
        singleButton.setBounds(260, 300, 280, 46);
        singleButton.setVisible(true);        
        singleButton.setCursor(cursor);
        singleButtonImg = new ImageIcon("Menus/BSinglePlayer.png");
        singleButton.setIcon(singleButtonImg);
        firstFace.add(singleButton);
        
        multiplayerButton = new JLabel();
        multiplayerButton.setBounds(260, 355, 280, 46);
        multiplayerButton.setVisible(true);
        multiplayerButton.setCursor(cursor);
        multiplayerButtonImg = new ImageIcon("Menus/BMultiPlayer.png");
        multiplayerButton.setIcon(multiplayerButtonImg);
        firstFace.add(multiplayerButton);
        
        creditsButton = new JLabel();
        creditsButton.setBounds(260, 410, 280, 46);
        creditsButton.setVisible(true);
        creditsButton.setCursor(cursor);
        creditsImg = new ImageIcon("Menus/BCredits.png");
        creditsButton.setIcon(creditsImg);
        firstFace.add(creditsButton);
        
        returnCreditsButton = new JLabel();
        returnCreditsButton.setBounds(5, 500, 280, 46);
        returnCreditsButton.setVisible(true);
        returnCreditsButton.setCursor(cursor);
        returnCreditsIMG = new ImageIcon("Menus/BBack.png");
        returnCreditsButton.setIcon(returnCreditsIMG);
        creditsFace.add(returnCreditsButton);
        
        returnSinglePlayerButton = new JLabel();
        returnSinglePlayerButton.setBounds(10, 10, 280, 46);
        returnSinglePlayerButton.setVisible(true);
        returnSinglePlayerButton.setCursor(cursor);
        returnSiglePalyerImg = new ImageIcon("Menus/BBack.png");
        returnSinglePlayerButton.setIcon(returnSiglePalyerImg);
        singlePlayerFace.add(returnSinglePlayerButton);
        
        returnM = new JLabel();
        returnM.setBounds(10, 10, 280, 46);
        returnM.setVisible(true);
        returnM.setCursor(cursor);
        returnMultiPalyerImg = new ImageIcon("Menus/BBack.png");
        returnM.setIcon(returnMultiPalyerImg);
        multiPlayerFace.add(returnM);
        
        level1 = new JLabel();
        level1.setBounds(130,270,266,309);
        level1.setVisible(true);
        level1.setCursor(cursor);
        singlePlayerFace.add(level1);
        
        level1M = new JLabel();
        level1M.setBounds(130,270,266,309);
        level1M.setVisible(true);
        level1M.setCursor(cursor);
        multiPlayerFace.add(level1M);
        
        level2 = new JLabel();
        level2.setBounds(410,270,266,309);
        level2.setVisible(true);
        level2.setCursor(cursor);
        singlePlayerFace.add(level2);
        
        level2M = new JLabel();
        level2M.setBounds(410,270,266,309);
        level2M.setVisible(true);
        level2M.setCursor(cursor);
        multiPlayerFace.add(level2M);
        
        credits = new JLabel();
        credits.setBounds(360, 20, 327, 550);
        credits.setVisible(true);
        credits.setCursor(cursor);
        backgroundCredits = new ImageIcon("Menus/CreditsTexto.png");
        credits.setIcon(backgroundCredits);
        creditsFace.add(credits);
        
    }    
    //Main of the Window
    public static FlyPalooza window;
    public static void main(String[] args) throws FileNotFoundException,  Exception {
        window = new FlyPalooza();
        Events obj = new Events();
        obj.changeFaces();
        Thread thread = new Thread(window);
        thread.start();
    }
    
}
