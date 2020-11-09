package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.application.Platform.exit;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import utilities.Helper;
import static utilities.Helper.crearBotonP;


public final class GenioPolitecnico {
    private HBox rootOnRoot;
    private Arbol arbol;
    private Nodo actual;
    private Nodo respuestaC;
    private String es_userAnimal;
    private boolean jugando;
    private Label label; // simula el label o text box donde se mostraran las preguntas del arbol, o
                         // mensajes
    private StackPane textBox;
    private Font f;
    private Nodo lastAnimal;

    public HBox getRoot() {
        return rootOnRoot;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public GenioPolitecnico() throws FileNotFoundException {

        rootOnRoot = new HBox();
        this.arbol = new Arbol();
        this.jugando = true;
        arbol.cargarArbol("datos-1.txt");
        actual = arbol.getRaiz();
        createGraficos();
    }

    public GenioPolitecnico(Arbol a) throws FileNotFoundException {
        rootOnRoot = new HBox();
        this.arbol = a;
        this.jugando = true;
        actual = a.getRaiz();
        createGraficos();
        a.posOrden();

    }

    public void createGraficos() throws FileNotFoundException {
        Group thoughtfulGenie = Helper.createPensando();

        // Parte grafica //
        VBox side = new VBox();

        // CONTENEDORES
        // TEXTO
        textBox = new StackPane();
        ImageView pergamino = Helper.create200x100("perg");
        textBox.getChildren().add(pergamino);
        textBox.setPrefSize(400, 200);
        label = new Label();
        textBox.getChildren().add(label);

        // BOTONES
        StackPane yes = Helper.createRoundButton("SI", "botonsi");
        StackPane no = Helper.createRoundButton("NO", "botonno");
        HBox botones = new HBox(yes, no);
        botones.setSpacing(50);
        side.getChildren().addAll(textBox, botones);
        botones.setAlignment(Pos.CENTER);

        String text = arbol.getRaiz().getInfo();
        // se escribe la pregunta con efecto
        efecto_maquina(label, text, textBox);

        yes.setOnMouseClicked(e -> {
            try {
                respuestaSi();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GenioPolitecnico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        no.setOnMouseClicked(e -> {
            try {
                respuestaNo();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GenioPolitecnico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        rootOnRoot.getChildren().addAll(thoughtfulGenie, side);
        rootOnRoot.getChildren().get(0).setTranslateY(130); // moviendo un poquito
        rootOnRoot.getChildren().get(1).setTranslateX(-30);

    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    
    public void respuestaSi() throws FileNotFoundException {

        // si se esta jugando y el nodo no es nulo se desplaza al nodo siguiente
        if (jugando && actual != null) {
            actual = actual.getSi();
            // si el nodo SI no es nulo, verifica si es pregunta y la muestra, o si se
            // dirige a una hoja, pregunta si es el animal que esta pensando
            if (actual != null) {
                if (actual.esPregunta()) {
                    efecto_maquina(label, actual.getInfo(), textBox);

                } else {
                    efecto_maquina(label, "Estas pensando en ".concat(actual.getInfo()).concat("?"), textBox);
                    lastAnimal = actual;
                }

                // si es hoja previamente, significa que gano
            } else {
                cambiarCelebrar();
            }
        }

    }

    // Metodo para celebrar que hemos adivinado
    public void cambiarCelebrar() throws FileNotFoundException {
        rootOnRoot.getChildren().clear();
        Pane celebrate = Helper.createCelebrate();
        celebrate.setTranslateX(200);
        celebrate.setTranslateY(150);
        StackPane volver = crearBotonP("Volver a jugar", "boton2");

        rootOnRoot.getChildren().addAll(celebrate, volver);

        volver.setOnMouseClicked(e -> {
            try {
                rootOnRoot.getChildren().clear();
                GenioPolitecnico ngp = new GenioPolitecnico(arbol);
                rootOnRoot.getChildren().add(ngp.getRoot());
            } catch (FileNotFoundException ex) {
                Helper.notifyError("Algo salio mal");
                exit();

            }
        });
    }

    // metodo del boton NO
    public void respuestaNo() throws FileNotFoundException {
        // si se esta jugando y el nodo no es nulo se desplaza al nodo siguiente
        if (jugando && actual != null) {
            actual = actual.getNo();
            // si el nodo NO no es nulo, verifica si es pregunta y la muestra, o si se
            // dirige a una hoja, pregunta si es el animal que esta pensando
            if (actual != null) {
                if (actual.esPregunta()) {
                    efecto_maquina(label, actual.getInfo(), textBox);
                } else {
                    efecto_maquina(label, "Estas pensando en ".concat(actual.getInfo()).concat("?"), textBox);
                    lastAnimal = actual;
                }
                // si es hoja previamente, significa que perdio y procede a agregar una pregunta
            } else {
                pedirRespuestas();
            }
        }
    }

    // Metodo para pedir respuestas
    private void pedirRespuestas() throws FileNotFoundException {
        rootOnRoot.getChildren().clear();
        Pane triste = Helper.createTriste();

        // PARTE PARA AGREGAR
        Font f = Font.loadFont(new FileInputStream(new File("src/recursos/myfonts/Font1.ttf")), 20);

        Label m0 = new Label("Ayudame a aprender más");
        Label m1 = new Label("Por favor, ingresa: ");

        Label m2 = new Label("Animal en el que pensabas: ");
        TextField animalUser = new TextField();
        animalUser.setStyle("-fx-background-color: #fdbe80 ;-fx-text-fill: white; -fx-font-size: 16px;");
        animalUser.setPrefWidth(200);
        animalUser.setMaxWidth(200);

        VBox m2box = new VBox(m2, animalUser);
        m2box.setSpacing(10);

        Label m3 = new Label("Una pregunta\nque me permita diferenciarlo: ");
        TextField preguntaUser = new TextField();

        preguntaUser.setStyle("-fx-background-color: #fdbe80 ;-fx-text-fill: white; -fx-font-size: 16px;");
        preguntaUser.setPrefWidth(200);
        preguntaUser.setMaxWidth(200);

        VBox m3box = new VBox(m3, preguntaUser);
        m3box.setSpacing(10);

        VBox preguntas = new VBox(m0, m1, m2box, m3box);
        m2box.setAlignment(Pos.CENTER);
        m3box.setAlignment(Pos.CENTER);

        Label m4 = new Label("Este animal responde a si o no");

        HBox botones = new HBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton rb_yes = new RadioButton("Si");
        rb_yes.setFont(f);

        RadioButton rb_no = new RadioButton("No");
        rb_no.setFont(f);

        rb_yes.setToggleGroup(toggleGroup);
        rb_no.setToggleGroup(toggleGroup);
        botones.getChildren().addAll(rb_yes, rb_no);
        botones.setSpacing(10);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                es_userAnimal = chk.getText();

            }
        });

        // setting font to los labels
        m0.setFont(f);
        m1.setFont(f);
        m2.setFont(f);
        m3.setFont(f);
        m4.setFont(f);

        StackPane enviar = Helper.crearBoton("Enviar", "boton2");
        VBox rootPR = new VBox(preguntas);

        VBox siOnoBox = new VBox(m4, botones, enviar);
        siOnoBox.setSpacing(15);
        siOnoBox.setAlignment(Pos.CENTER);
        enviar.setTranslateX(-80);
        m4.setTranslateX(-90);

        // evento jaja

        enviar.setOnMouseClicked(e -> {
            if (animalUser.getText() != null && preguntaUser.getText() != null) {

                arbol.addArbol(lastAnimal, preguntaUser.getText(), respuestaC, es_userAnimal);

                try {
                    cambiarCelebrar();
                } catch (FileNotFoundException ex) {
                    Helper.notifyError("Ocurrio un error al momento agregar tu pregunta");
                    exit();
                }
            } else {
                System.out.println("Ingrese datos");
            }

        });

        preguntaUser.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    preguntaUser.getText();
                    respuestaC = new Nodo("#R " + animalUser.getText());

                    rootPR.getChildren().remove(preguntas);
                    rootPR.getChildren().add(siOnoBox);
                    siOnoBox.setTranslateX(100);

                }
            }
        });

        rootPR.setAlignment(Pos.CENTER);
        System.out.println(lastAnimal);
        System.out.println("ventana");

        rootOnRoot.getChildren().addAll(triste, rootPR);
        triste.setTranslateX(10);
        triste.setTranslateY(150);
        rootPR.setTranslateX(-2);
        rootPR.setTranslateY(70);

        rootPR.setPrefSize(400, 400);
        Helper.stylePreguntas(rootPR);

        m1.setFont(f);

        preguntas.setAlignment(Pos.CENTER);
        rootPR.setAlignment(Pos.CENTER);

    }

    // EFECTOS LETRAS

    public void efecto_maquina(Label newtext, String text, StackPane textBox) throws FileNotFoundException {
        f = Font.loadFont(new FileInputStream(new File("src/recursos/myfonts/Perg.ttf")), 20);
        newtext.setFont(f);
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), event -> {
            if (i.get() > text.length()) {
                timeline.stop();
            } else {
                newtext.setText(text.substring(0, i.get()));
                i.set(i.get() + 1);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

}