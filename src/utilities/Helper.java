package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class Helper {
    
    
    
    public static void crearEstilo(Pane root){
    root.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+"bg1.png');"
                + "-fx-background-repeat: stretch;"
            +"-fx-background-size: "+CONSTANTES.WINDOW_WIDTH+" "+CONSTANTES.WINDOW_HEIGHT+";"
            + "-fx-background-position: center center;");
    
    }
    
    
    public static void crearEstilo2(Pane root){
    root.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+"bg2.png');"
                + "-fx-background-repeat: stretch;"
            +"-fx-background-size: "+CONSTANTES.WINDOW_WIDTH+" "+CONSTANTES.WINDOW_HEIGHT+";"
            + "-fx-background-position: center center;");
    
    }
    
    
    public static ImageView createPic100x100(String nompic){
        Image img = new Image(CONSTANTES.RUTA_ANIMATION+nompic+".png",
                100,
                100,
                true,
                true);
             return  new ImageView(img);
    }
    
    public static ImageView createPic300x300(String nompic){
        Image img = new Image(CONSTANTES.RUTA_ANIMATION+nompic+".png",
                300,
                300,
                false,
                false);
             return  new ImageView(img);
    }
    
    public static ImageView createPensando(String nompic){
        Image img = new Image(CONSTANTES.RUTA_ANIMATION+nompic+".png",
                300,
                300,
                false,
                false);
             return  new ImageView(img);
        
    }
    
    public static void stylePreguntas(VBox root){
    root.setStyle("-fx-background-image: url('"+CONSTANTES.RUTA_IMAGENES+"preguntas.png');"
                + "-fx-background-repeat: stretch;"
            +"-fx-background-size: "+350+" "+350+";"
            + "-fx-background-position: center center;");
    
    }
    
    public static ImageView create200x100(String nompic){
        Image img = new Image(CONSTANTES.RUTA_IMAGENES+nompic+".png",
                400,
                200,
                false,
                false);
             return  new ImageView(img);
    }
    
    public static ImageView createBienvenido(){
        Image img = new Image(CONSTANTES.RUTA_IMAGENES+"bienvenido.png",
                350,
                200,
                false,
                false);
             return  new ImageView(img);
    }
    
     public static ImageView createSpark(String nompic){
        Image img = new Image(CONSTANTES.RUTA_ANIMATION+nompic+".png",
                200,
                120,
                false,
                false);
             return  new ImageView(img);
    }
     
     public static StackPane crearBoton(String name,String nompic) throws FileNotFoundException{
        Image bg = new Image(CONSTANTES.RUTA_IMAGENES+nompic+".png",
                150,
                100,
                true,
                true);
        StackPane sp = new StackPane();
        Label text = new Label(name);
        Font f = Font.loadFont(new FileInputStream(new File("src/recursos/myFonts/Font1.ttf")), 32);
        text.setFont(f);
        text.setTextFill(Color.WHITE);
        ImageView botonView =new ImageView(bg);
        sp.getChildren().addAll(botonView,text);
        text.setTranslateY(-2);
        return sp;
    
    }
     
     public static StackPane crearBotonP(String name,String nompic) throws FileNotFoundException{
        Image bg = new Image(CONSTANTES.RUTA_IMAGENES+nompic+".png",
                150,
                100,
                true,
                true);
        StackPane sp = new StackPane();
        Label text = new Label(name);
        Font f = Font.loadFont(new FileInputStream(new File("src/recursos/myFonts/Font1.ttf")), 18);
        text.setFont(f);
        text.setTextFill(Color.WHITE);
        ImageView botonView =new ImageView(bg);
        sp.getChildren().addAll(botonView,text);
        text.setTranslateY(-2);
        

        return sp;
    
    }
     
    
    public static StackPane createRoundButton(String name,String nompic) throws FileNotFoundException{
        Image bg = new Image(CONSTANTES.RUTA_IMAGENES+nompic+".png",
                50,
                50,
                true,
                true);
        StackPane sp = new StackPane();
        Label text = new Label(name);
        Font f = Font.loadFont(new FileInputStream(new File("src/recursos/myFonts/Font1.ttf")), 25);
        text.setFont(f);
        text.setTextFill(Color.WHITE);
        ImageView botonView =new ImageView(bg);
        sp.getChildren().addAll(botonView,text);
        text.setAlignment(Pos.CENTER);
        
        return sp;
    
    }
    
    public static  void notifyError(String msg){
        Alert t = new Alert(Alert.AlertType.ERROR);
        t.setHeaderText("Lo sentimos :(");
        t.setContentText(msg);
        t.show();
    
    }
    
    
    //ANIMACIONES
    public static Group createSparks(){
    final ImageView genieT0= createSpark("SFrame1");
    final ImageView genieT1= createSpark("SFrame2");
    final ImageView genieT2= createSpark("SFrame3");
    final ImageView genieT3= createSpark("SFrame4");
    final ImageView genieT4= createSpark("SFrame5");
    final ImageView genieT5= createSpark("SFrame6");
    final ImageView genieT6= createSpark("SFrame7");
    final ImageView genieT7= createSpark("SFrame8");
    Group sparks = new Group(genieT0);
    
    Timeline t2 = new Timeline();
    t2.setCycleCount(Timeline.INDEFINITE);
    //AGREGANDO IMAGENES A TIMELINE
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT1);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT2);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT3);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(500), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT4);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(600), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT5);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(700), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT6);
                }
        ));
        t2.getKeyFrames().add(new KeyFrame(
                Duration.millis(800), (ActionEvent event) -> {
                    sparks.getChildren().setAll(genieT7);
                }
        ));
        t2.play();
    
        return sparks;
    }
    
    public static Group createLamp(){
    final ImageView lamp0= createPic100x100("LFrame0");
    final ImageView lamp1= createPic100x100("LFrame1");
    final ImageView lamp2= createPic100x100("LFrame2");
    final ImageView lamp3= createPic100x100("LFrame3");
    final ImageView lamp4= createPic100x100("LFrame4");
    final ImageView lamp5= createPic100x100("LFrame5");
    final ImageView lamp6= createPic100x100("LFrame6");
    
    Group lamp = new Group(lamp0);
    
    //Creando timeline para animar
    Timeline t = new Timeline();
    t.setCycleCount(Timeline.INDEFINITE);
    //AGREGANDO IMAGENES A TIMELINE
    
    //Desestabilizar a izq 
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(100), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp1);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(150), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp2);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp3);
                }
        ));
        
        //en equilibrio
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(250), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp0);
                }
        ));
        
        //DESESTABILIZANDO DER
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp4);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(350), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp5);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    lamp.getChildren().setAll(lamp6);
                }
        ));
        t.play();
    
    return lamp;
    
    }
    
     public static Group createGenie(){
    final ImageView frame0= createPic300x300("GFrame0");
    final ImageView frame1= createPic300x300("GFrame1");
    final ImageView frame2= createPic300x300("GFrame2");
    final ImageView frame3= createPic300x300("GFrame3");
    final ImageView frame4= createPic300x300("GFrame4");
    final ImageView frame5= createPic300x300("GFrame5");
    final ImageView frame6= createPic300x300("GFrame6");
    final ImageView frame7= createPic300x300("GFrame7");
    final ImageView frame8= createPic300x300("GFrame8");
    final ImageView frame9= createPic300x300("GFrame9");
    final ImageView frame10= createPic300x300("GFrame10");
    final ImageView frame11= createPic300x300("GFrame11");
    final ImageView frame12= createPic300x300("GFrame12");
    final ImageView frame13= createPic300x300("GFrame13");
    final ImageView frame14= createPic300x300("GFrame14");
    final ImageView frame15= createPic300x300("GFrame15");
    final ImageView frame16= createPic300x300("GFrame16");
    final ImageView frame17= createPic300x300("GFrame17");
    final ImageView frame18= createPic300x300("GFrame18");
    
    
    Group genie = new Group(frame0);
    Timeline tl = new Timeline();
    
    //mov lampara derecha
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(100), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame1);
                    
                    
                }
        ));
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame2);
                }
        ));
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame3);
                }
        ));
        
        //retorno a equilibrio
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame0);
                    ControladorAudio.playMagic();
                }
        ));
        
        //movimiento a izq
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(500), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame4);
                }
        ));
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(600), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame5);
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(700), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame6);
                }
        ));
        
        //movimiento de regreso
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(800), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame5);
                }
        ));
        
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(900), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame4);
                }
        ));
         
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame0);
                }
                 
        //saliendo de lampara
        ));
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1100), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame7);
                }
        ));
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1200), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame8);
                    
                }
        ));
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1300), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame9);
                    
                }
        ));
         tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1400), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame10);
                }
        ));
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1500), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame11);
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1600), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame12);
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1700), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame13);
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1800), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame14);
                    ControladorAudio.getMediaPlayer().stop();
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(1900), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame15);
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(2000), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame16);
                    ControladorAudio.playSoundtrack(new File(CONSTANTES.RUTA_SONIDO+"vista2.mp3").getAbsolutePath());
                }
        ));
        
        tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(2100), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame17);
                }
        ));
        
       tl.getKeyFrames().add(new KeyFrame(
                Duration.millis(2200), (ActionEvent event) -> {
                    genie.getChildren().setAll(frame18);
                }
        ));
        
        tl.play();
        
    return genie;
     
     }
     
    public static Group createPensando(){
    final ImageView frame0= createPensando("PTFrame0");
    final ImageView frame1= createPensando("PTFrame1");
    final ImageView frame2= createPensando("PTFrame2");
    final ImageView frame3= createPensando("PTFrame3");
    
    Group tt = new Group(frame0);
    
    //Creando timeline para animar
    Timeline t = new Timeline();
    t.setCycleCount(Timeline.INDEFINITE);
    //AGREGANDO IMAGENES A TIMELINE
    
    //Desestabilizar a izq 
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame1);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(600), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame2);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(1200), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame3);
                }
        ));
        
        t.play();
    return tt;
    
    }
    
    
    public static Pane createTriste(){
    Pane triste = new Pane();
    final ImageView frame0= createPensando("STFrame0");
    final ImageView frame1= createPensando("STFrame1");
    final ImageView frame2= createPensando("STFrame2");
    final ImageView frame3= createPensando("STFrame3");
   
    
    Group tt = new Group(frame0);
    
    //Creando timeline para animar
    Timeline t = new Timeline();
    t.setCycleCount(-1);
    //AGREGANDO IMAGENES A TIMELINE
    
    //Desestabilizar a izq 
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame1);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame2);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame3);
                }
        ));
        
        t.play();
        triste.getChildren().add(tt);
        
    return triste;
    
    }
    
    public static Pane createCelebrate() throws FileNotFoundException{
    Pane celebrate= new Pane();
    final ImageView frame0= createPensando("CTFrame0");
    final ImageView frame1= createPensando("CTFrame1");
    final ImageView frame2= createPensando("CTFrame2");
    final ImageView frame3= createPensando("CTFrame3");
    final ImageView frame4= createPensando("CTFrame4");

    
    
    Group tt = new Group(frame0);
    
    //Creando timeline para animar
    Timeline t = new Timeline();
    t.setCycleCount(1);
    //AGREGANDO IMAGENES A TIMELINE
    
    //Desestabilizar a izq 
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame1);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame2);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame3);
                }
        ));
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) -> {
                    tt.getChildren().setAll(frame4);
                }
        ));
        
        t.play();
        
        celebrate.getChildren().addAll(tt);
      
      
      
    return  celebrate;
    
    }
    
    
}