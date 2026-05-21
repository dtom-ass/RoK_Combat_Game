package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndScene {

    public static Scene create(
            Stage stage,
            boolean playerWon) {

        Label result =
                new Label(
                        playerWon
                                ? "VICTORIA"
                                : "DERROTA");

        result.getStyleClass()
                .add("end-title");

        Label subtitle =
                new Label(
                        playerWon
                                ? "El enemigo ha sido derrotado"
                                : "Tu ejército fue eliminado");

        subtitle.getStyleClass()
                .add("end-subtitle");

        Button retry =
                new Button("Volver a jugar");

        Button close =
                new Button("Cerrar juego");

        retry.setOnAction(e -> {

            PlayerPanel panel = new PlayerPanel();
            SelectionController controller = new SelectionController(panel);
            stage.setScene(
                    WelcomeScene.create(stage, panel, controller));
        });

        close.setOnAction(e -> {
            stage.close();
        });

        VBox root =
                new VBox(
                        30,
                        result,
                        subtitle,
                        retry,
                        close);

        root.setAlignment(Pos.CENTER);

        Scene scene =
                new Scene(root, 800, 600);

        String css = EndScene.class
                .getResource("/view/scenes/styles/End.css")
                .toExternalForm();

        scene.getStylesheets().add(css);

        return scene;
    }
}