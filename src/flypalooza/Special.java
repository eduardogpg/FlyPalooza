
package flypalooza;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Special extends Thread{
    
    private ArrayList ListRuta;
    private Image ImgSpecial;
    private ImageIcon IconSpecial;
    private int posY=-200,posX=0;
    private int Speed= 20;
    private boolean hidde= true;
    
    Random random = new Random();
    private int SpecialNumber = 0;
    public Special(){
        this.ListRuta = new ArrayList();
        this.LoadImg();
        this.LoadSpecial();
        
    }
    private void LoadImg(){
        this.ListRuta.add("Imagenes/Poderes/1.png");//0
        this.ListRuta.add("Imagenes/Poderes/2.png");//1
        this.ListRuta.add("Imagenes/Poderes/3.png");//2
        this.ListRuta.add("Imagenes/Poderes/4.png");//3
        

        
    }
    private void LoadSpecial(){
        this.posX= this.random.nextInt(600)+1;
        
        SpecialNumber = this.random.nextInt(4);
        String path = (String)this.ListRuta.get(SpecialNumber);
        this.IconSpecial = new ImageIcon(path);
        this.ImgSpecial = this.IconSpecial.getImage();
        
    }
    public void run(){
        while(this.hidde){
            try {
                this.Drop();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fly.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void Drop(){
        this.posY += this.Speed;
    }
    public int GetSpecial(){
        return this.SpecialNumber;
    }
    
   public Image getimage(){
     return this.ImgSpecial;
    }

    public int getX(){
        return this.posX;
}
    public int getY(){
        return this.posY;
    }
    public Rectangle Rectangulo(){
     int High;
     int Width;

     High = this.ImgSpecial.getHeight(null);
     Width = this.ImgSpecial.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
    
/*Creo en un solo lenguaje de programación,
"C" Todopoderoso creador de Unix y de Windows
de todo lo visible y lo abstracto.
Creo en un solo "C++", hijo único de "C".
Nacido de "C" antes de "Visual C++".
"C" de "C", Compilador de compiladores
"C" © copyrighted compilado, no creado,
de los mismos programadores que el padre,
por quien todo es programado.
Que por nosotros los ingenieros,
y nuestros chalanes (técnicos) fué desarrollado.
Y por obra del Lenguaje Binario encarno en Ensamblador y se hizo lenguaje.
Y por nuestra causa, es ampliamente aceptado en tiempos de Bill Gates
Decayó y fué olvidado, y se renovó al tercer día, según los usuarios.
Y subieron las ventas, y está ubicado dentro de todo Unix.
Por quien todo es programado.
Y de nuevo vendrá mejorado para depurar a virus y programas.
Y su dominio no tendrá fin.
Creo en el lenguaje binario,
código y base del sistema,
que precede a la motherboard y al Procesador,
que con la motherboard y el Procesador recibe una misma aplicación y memoria
y que transfirió datos con las unidades...
Creo en la arquitectura IBM,
qu es una solida, compacta y compatible.
Confieso que no hay ni un solo "undo" para corrección de los errores.
Espero la resurrección de los bulbos, y la vida en un mundo con Internet...
Enter...

-TheMrkiller*/
}
