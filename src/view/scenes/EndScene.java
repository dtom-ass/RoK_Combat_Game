package view.scenes;

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

        Label result = new Label(
                playerWon
                        ? "VICTORIA"
                        : "DERROTA");

        Button close =
                new Button("Cerrar");

        close.setOnAction(e -> {
            stage.close();
        });

        VBox root =
                new VBox(30, result, close);

        root.setAlignment(Pos.CENTER);

        return new Scene(root, 800, 600);
    }
}