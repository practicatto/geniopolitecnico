package geniopoli;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.Arbol;
import utilities.CONSTANTES;
import vistas.Empezar;

public class GenioPoli extends Application {
    Empezar ventanaEmpezar;
    Arbol tree;

    @Override
    public void start(Stage primaryStage) {
        ventanaEmpezar = new Empezar();
        Pane root = ventanaEmpezar.getRoot();
        Scene sc = new Scene(root, 700, 500);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Genio Politecnico");
        primaryStage.getIcons().add(new Image(CONSTANTES.RUTA_IMAGENES + "ico.png"));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    
    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
    }

    @Override
    public void stop() {
        tree = ventanaEmpezar.getGenio().getArbol();
        tree.guardarArbol("datos-1.txt", tree.getRaiz());
    }

}
