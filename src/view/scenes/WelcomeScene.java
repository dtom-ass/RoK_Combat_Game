package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Escena de bienvenida.
 */
public class WelcomeScene {

    /**
     * Crea la pantalla principal.
     */
    public static Scene create(
            Stage stage,
            PlayerPanel panel,
            SelectionController controller) {

        /*
         * Título
         */

        Label title =
                new Label(
                        "RoK Combat Game");

        title.getStyleClass()
                .add("title-label");

        /*
         * Botón iniciar
         */

        Button startButton =
                new Button(
                        "Iniciar Juego");

        startButton.setOnAction(e -> {

            stage.setScene(
                    SelectionScene.create(
                            stage,
                            panel,
                            controller));
        });

        /*
         * Botón salir
         */

        Button exitButton =
                new Button(
                        "Salir");

        exitButton.setOnAction(e -> {

            stage.close();
        });

        /*
         * Panel botones
         */

        VBox menuBox =
                new VBox(
                        20,
                        title,
                        startButton,
                        exitButton);

        menuBox.setAlignment(
                Pos.CENTER);

        /*
         * Root
         */

        StackPane root =
                new StackPane(menuBox);

        /*
         * IMPORTANTE:
         * Clase CSS del fondo
         */

        root.getStyleClass()
                .add("welcome-root");

        /*
         * Scene
         */

        Scene scene =
                new Scene(
                        root,
                        800,
                        600);

        /*
         * CSS
         */

        String css =
                WelcomeScene.class
                        .getResource(
                                "/view/styles/Welcome.css")
                        .toExternalForm();

        scene.getStylesheets()
                .add(css);

        return scene;
    }
}