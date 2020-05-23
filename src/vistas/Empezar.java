/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import logica.GenioPolitecnico;
import utilities.CONSTANTES;
import utilities.ControladorAudio;
import utilities.Helper;

/**
 *
 * @author joangie
 */
public class Empezar{
   private final Pane root; 
   private final Group lamp; 
   private final Group sparks;
   private final Pane box;
   private final StackPane lampbox;
   private final Pane genieTBox;
   private VBox sidebox;
   private Pane genieBox;
   private boolean isFinished=false;
   private GenioPolitecnico gp;
   
    public Pane getRoot() {
        return root;
    }
    
    public boolean isFinished(){
        return isFinished;
    }
    
    public void setFinsihed(){
        isFinished=true;
    }
   
    public GenioPolitecnico getGenio(){
        return gp;
    }
    
   
        
    public Empezar(){
       try {
           gp = new GenioPolitecnico();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Empezar.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        root= new Pane();
           Helper.crearEstilo(root);
           //sonido
           ControladorAudio.playSoundtrack(new File(CONSTANTES.RUTA_SONIDO+"start_music.mp3").getAbsolutePath());
           box= new Pane();
           lampbox= new StackPane();
           genieTBox = new Pane();
           lamp = Helper.createLamp();
           
           //GENIO POLITECNICO SPARKS ANIMATION no se como cargar gifs en javafx por eso hago esto lo 100to
           //Instanciando para el destello
           sparks = Helper.createSparks();
           
            
       try {
           
           //BOTON EMPEZAR
           StackPane startGame = Helper.crearBoton("EMPEZAR","boton1");
           Label gameName= new Label("Genio\nPolitecnico");
           Font g = Font.loadFont(new FileInputStream(new File("src/recursos/myFonts/Title.ttf")), 30);
           gameName.setFont(g);
           gameName.setTextAlignment(TextAlignment.CENTER);
           startGame.setAlignment(Pos.CENTER);
           
           
           //EVENTO PARA EMPEZAR
           startGame.setOnMouseClicked(e->{
               root.getChildren().clear();
               ControladorAudio.getMediaPlayer().stop();
               Helper.crearEstilo2(root);
               ControladorAudio.clicSound();
               HBox supremeRoot= new HBox();
               
               Helper.crearEstilo2(root);
               Group genie = Helper.createGenie();
               genieBox = new Pane();
               genieBox.getChildren().add(genie);
               
               sidebox= new VBox();
               ImageView cloudView= Helper.createBienvenido();
               
               try{
                   //EVENTO PARA JUGAR
                   StackPane jugarBt = Helper.crearBoton("JUGAR","boton2");
                   
                   //JUGAR
                   jugarBt.setOnMouseClicked(clicked->{
                       ControladorAudio.clicSound();
                       supremeRoot.getChildren().clear();
                       supremeRoot.getChildren().add(gp.getRoot());
                           
                       
                   });
                   
                   
                   sidebox.getChildren().addAll(cloudView,jugarBt);
                   
                   supremeRoot.setPadding(new Insets(20));
                   
                   supremeRoot.getChildren().addAll(genieBox,sidebox);
                   
                   
                   genieBox.setTranslateY(130);
                   sidebox.setTranslateX(-140);
                   sidebox.setTranslateY(820);
                   
                   root.getChildren().add(supremeRoot);
                   TranslateTransition tt = new TranslateTransition();
                   tt.setDuration(Duration.seconds(2));
                   tt.setNode(sidebox);
                   tt.setToX(50);
                   tt.setToY(30);
                   tt.play();
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(Empezar.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
           
           //nodo para la lampara para que no se mueva todo xd
           lampbox.getChildren().add(lamp);
           genieTBox.getChildren().addAll(sparks,gameName);
           
           //agregando a contenedor
           box.getChildren().addAll(lampbox,genieTBox,startGame);
           //cambiando posiciones y añadiendo espacios
           lampbox.setTranslateX(50);
           genieTBox.setTranslateY(80);
           startGame.setTranslateY(180);
           startGame.setTranslateX(35);
           root.getChildren().add(box);
           box.setTranslateX(250);
           box.setTranslateY(100);
           box.setPadding(new Insets(30,30,30,30));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Empezar.class.getName()).log(Level.SEVERE, null, ex);
       }
        
      
    }
    
}

