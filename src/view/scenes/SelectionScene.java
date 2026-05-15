package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.console.ConsoleLog;

public class SelectionScene {
    public static Scene create(Stage stage, PlayerPanel panel, SelectionController controller) {

        ConsoleLog.Log("INICIANDO ESCENA: SelectionScene");
        Button ButtonCultureAzteca = new Button("Azteca");
        ButtonCultureAzteca.setOnAction(e -> {
            ConsoleLog.Log("Selección cultura Azteca.");
            controller.selectAzteca();
            stage.setScene(BattleScene.create(stage, panel, controller));
            
        });
        Button ButtonCultureMaya = new Button("Maya");
        ButtonCultureMaya.setOnAction(e -> {
            ConsoleLog.Log("Selección cultura Maya.");
            controller.selectMaya();
            stage.setScene(BattleScene.create(stage, panel, controller));
            
        });
        Button ButtonCultureInca = new Button("Inca");
        ButtonCultureInca.setOnAction(e -> {
            ConsoleLog.Log("Selección cultura Inca.");
            controller.selectInca();
            stage.setScene(BattleScene.create(stage, panel, controller));
            
        });
        Button ButtonCultureMuisca = new Button("Muisca");
        ButtonCultureMuisca.setOnAction(e -> {
            ConsoleLog.Log("Selección cultura Muisca.");
            controller.selectMuisca();
            stage.setScene(BattleScene.create(stage, panel, controller));
            
        });

        VBox root = new VBox(
                20,
                ButtonCultureAzteca,ButtonCultureMaya,ButtonCultureInca,ButtonCultureMuisca);

        return new Scene(root, 800, 600);
    }
}
