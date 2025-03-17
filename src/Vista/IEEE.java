package Vista;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Torres & Emerson Vera
 */
public class IEEE extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/IEEE.fxml"));
            Parent root = loader.load();
            // Crear la escena con el contenido cargado
            Scene scene = new Scene(root);
            // Configurar el escenario (stage)
            primaryStage.setTitle("IEEE");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
