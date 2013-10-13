/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flypalooza;

import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.audio.AudioStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.AudioClip;
import javax.swing.JApplet;
import java.io.File;
import java.net.URL;
/**
 *
 * @author Christian
 */

public class Audio {
 
    private AudioClip clip=null;
    private String dir=null;
 
    public Audio(AudioClip ac) {
        clip=ac;
    }
    public Audio(URL u){
        this(JApplet.newAudioClip(u));
        dir=u.getPath()+" "+u.getFile();
    }
    public Audio(File f)throws Exception {
        this(f.toURL());
    }
    public Audio(String d)throws Exception{
        this(new File(d));
    }
    public static Audio getAudioClip(String d){
        try {
            return new Audio(d);
        }catch (Exception ex) {
            System.out.println (ex);
        }
        return null;
    }
    public void play(){
        clip.play();
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop();
    }
    public String toString(){
        return dir+"\n"+clip.toString();
    }
}