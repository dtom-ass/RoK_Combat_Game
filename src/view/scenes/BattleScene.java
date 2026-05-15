package view.scenes;

import controller.PlayerPanel;
import controller.SelectionController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.console.ConsoleLog;

public class BattleScene {
    public static Scene create(Stage stage, PlayerPanel panel, SelectionController controller) {

        Button ButtonBasicAttack = new Button("Ataque Basico");
        Button ButtonSpecialAttack = new Button("Ataque Especial");
        Button ButtonChangeWarrior = new Button("Cambiar Guerrero");

        ButtonBasicAttack.setOnAction(e -> {
            ConsoleLog.Log("Ataque Basico.");
            panel.basicAttack();
        });

        ButtonSpecialAttack.setOnAction(e -> {
            ConsoleLog.Log("Ataque Especial.");
            panel.specialAttack();
        });

        ButtonChangeWarrior.setOnAction( e -> {
            panel.ChangeWarrior();
        });

        Button EnemyButtonAttack = new Button("Ataque Enemigo");
        EnemyButtonAttack.setOnAction( e -> {
            panel.getBattle().enemyTurn();
        });

        VBox root = new VBox(ButtonBasicAttack, ButtonSpecialAttack, ButtonChangeWarrior, EnemyButtonAttack);
        return new Scene(root, 800, 600);
    }
}
