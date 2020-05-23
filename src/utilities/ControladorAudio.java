package utilities;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class ControladorAudio {
      private static MediaPlayer mediaPlayer;
      private static Scene sc;
  
      public ControladorAudio(){}
      
      public static MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
      
     public static void setMediaPlayer(MediaPlayer mediaPlayer){
        ControladorAudio.mediaPlayer=mediaPlayer;
    }
     
     public static void clicSound(){
     String pathClic =  new File(CONSTANTES.RUTA_SONIDO+"clic_sound.mp3").getAbsolutePath();
     Media clic = new Media(new File(pathClic).toURI().toString()); 
     ControladorAudio.setMediaPlayer(new MediaPlayer(clic));
     ControladorAudio.getMediaPlayer().setAutoPlay(true);
     ControladorAudio.getMediaPlayer().setVolume(1);
     ControladorAudio.getMediaPlayer().setCycleCount(1);
     
     }
     
     public static void playSoundtrack(String path){
         
     Media musicFile2 = new Media(new File(path).toURI().toString());
     ControladorAudio.setMediaPlayer(new MediaPlayer(musicFile2));
     ControladorAudio.getMediaPlayer().setAutoPlay(true);
     ControladorAudio.getMediaPlayer().setVolume(0.6);
     ControladorAudio.getMediaPlayer().setCycleCount(-1);
     
     }
     
     public static void playMagic(){
     String pathMagic= new File(CONSTANTES.RUTA_SONIDO+"magic_sound.mp3").getAbsolutePath();
     Media magic = new Media(new File(pathMagic).toURI().toString()); 
     ControladorAudio.setMediaPlayer(new MediaPlayer(magic));
     ControladorAudio.getMediaPlayer().setAutoPlay(true);
     ControladorAudio.getMediaPlayer().setVolume(0.6);
     ControladorAudio.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
     
     }
      
      
}
