package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.scenes.*;
import view.console.ConsoleLog;

public class WelcomeScene {

    public static Scene create(Stage stage, PlayerPanel panel, SelectionController controller) {
        ConsoleLog.Log("INICIANDO ESCENA: WelcomeScene");
        Button startBtn = new Button("Iniciar");

        Button exitBtn = new Button("Salir");

         startBtn.setOnAction(e -> {
            // Hacemos pruebas con  guitest
            ConsoleLog.Log("CAMBIO DE ESCENA: GUI TEST");
            stage.setScene(
                SelectionScene.create(stage, panel, controller)
            );
        });
        exitBtn.setOnAction(e -> {
            stage.close();
        });

        VBox root = new VBox(
                20,
                startBtn,
                exitBtn);

        return new Scene(root, 800, 600);
    }
}