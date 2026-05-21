package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.console.ConsoleLog;

public class WelcomeScene {

    public static Scene create(Stage stage, PlayerPanel panel, SelectionController controller) {
        ConsoleLog.Log("INICIANDO ESCENA: WelcomeScene");
        Button startBtn = new Button("Iniciar");

        Button exitBtn = new Button("Salir");

        startBtn.setOnAction(e -> {
            // Hacemos pruebas con guitest
            ConsoleLog.Log("CAMBIO DE ESCENA: SelectionScene");
            stage.setScene(
                    SelectionScene.create(stage, panel, controller));
        });
        exitBtn.setOnAction(e -> {
            stage.close();
        });

        startBtn.getStyleClass().add("welcomeButtons");
        exitBtn.getStyleClass().add("welcomeButtons");

        HBox ButtonsContainer = new HBox(startBtn, exitBtn);
        ButtonsContainer.getStyleClass().add("ButtonsContainer");

        Label title = new Label("RoK Combat Game");
        title.getStyleClass().add("title-label");

        VBox root = new VBox(
                title,
                ButtonsContainer);
        root.getStyleClass().add("main-container");

        ConsoleLog.Log("- PANTALLA DE INICIO -");
        Scene scene = new Scene(root, 800, 600);

        String css = WelcomeScene.class
                .getResource("/view/scenes/styles/welcome.css")
                .toExternalForm();

        scene.getStylesheets().add(css);

        return scene;
    }
}