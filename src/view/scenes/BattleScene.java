package view.scenes;

import controller.BattleController;
import controller.PlayerPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Warrior;
import view.components.WarriorCard;
import view.console.ConsoleLog;

public class BattleScene {

    public static Scene create(
            Stage stage,
            PlayerPanel panel) {

        BattleController battle = panel.getBattle();

        /*
         * =========================================
         * GUERREROS DEL JUGADOR
         * =========================================
         */
        Warrior allyWarrior1 =
                battle.getPlayer()
                        .getWarriorTeam()
                        .get(0);

        Warrior allyWarrior2 =
                battle.getPlayer()
                        .getWarriorTeam()
                        .get(1);

        Warrior allyWarrior3 =
                battle.getPlayer()
                        .getWarriorTeam()
                        .get(2);

        WarriorCard ally1 =
                new WarriorCard(allyWarrior1);

        WarriorCard ally2 =
                new WarriorCard(allyWarrior2);

        WarriorCard ally3 =
                new WarriorCard(allyWarrior3);

        /*
         * =========================================
         * GUERREROS ENEMIGOS
         * =========================================
         */
        Warrior enemyWarrior1 =
                battle.getEnemy()
                        .getEnemyCulture()
                        .getWarriorList()
                        .get(0);

        Warrior enemyWarrior2 =
                battle.getEnemy()
                        .getEnemyCulture()
                        .getWarriorList()
                        .get(1);

        Warrior enemyWarrior3 =
                battle.getEnemy()
                        .getEnemyCulture()
                        .getWarriorList()
                        .get(2);

        WarriorCard enemy1 =
                new WarriorCard(enemyWarrior1);

        WarriorCard enemy2 =
                new WarriorCard(enemyWarrior2);

        WarriorCard enemy3 =
                new WarriorCard(enemyWarrior3);

        /*
         * =========================================
         * BOTONES
         * =========================================
         */
        Button ButtonBasicAttack =
                new Button("Ataque Basico");

        Button ButtonSpecialAttack =
                new Button("Ataque Especial");

        Button ButtonChangeWarrior =
                new Button("Cambiar Guerrero");

        /*
         * =========================================
         * IDs CSS
         * =========================================
         */
        ButtonBasicAttack.setId("ButtonBasicAttack");

        ButtonSpecialAttack.setId("ButtonSpecialAttack");

        ButtonChangeWarrior.setId("ButtonChangeWarrior");

        /*
         * =========================================
         * EVENTOS
         * =========================================
         */
        ButtonBasicAttack.setOnAction(e -> {

            ConsoleLog.Log("Ataque Basico");

            panel.basicAttack();

            updateAll(
                    ally1, ally2, ally3,
                    enemy1, enemy2, enemy3);

            checkBattleEnd(stage, battle);

    if (!battle.isBattleOver()) {

        battle.enemyTurn();

        updateAll(
                ally1, ally2, ally3,
                enemy1, enemy2, enemy3);

        checkBattleEnd(stage, battle);
    }
        });

        ButtonSpecialAttack.setOnAction(e -> {

            ConsoleLog.Log("Ataque Especial");

            panel.specialAttack();

            updateAll(
                    ally1, ally2, ally3,
                    enemy1, enemy2, enemy3);

            checkBattleEnd(stage, battle);

    if (!battle.isBattleOver()) {

        battle.enemyTurn();

        updateAll(
                ally1, ally2, ally3,
                enemy1, enemy2, enemy3);

        checkBattleEnd(stage, battle);
    }
        });

        ButtonChangeWarrior.setOnAction(e -> {

            ConsoleLog.Log("Cambio de guerrero");

            panel.ChangeWarrior();

            updateAll(
                    ally1, ally2, ally3,
                    enemy1, enemy2, enemy3);
        });

        /*
         * =========================================
         * EQUIPO ENEMIGO
         * =========================================
         */
        HBox enemyTeam = new HBox(
                20,
                enemy1,
                enemy2,
                enemy3);

        enemyTeam.setAlignment(Pos.CENTER);

        /*
         * =========================================
         * EQUIPO ALIADO
         * =========================================
         */
        HBox allyTeam = new HBox(
                20,
                ally1,
                ally2,
                ally3);

        allyTeam.setAlignment(Pos.CENTER);

        /*
         * =========================================
         * PANEL CENTRAL DE ACCIONES
         * =========================================
         */
        VBox actions = new VBox(
                20,
                ButtonBasicAttack,
                ButtonSpecialAttack,
                ButtonChangeWarrior);

        actions.setAlignment(Pos.CENTER);

        /*
         * =========================================
         * ROOT PRINCIPAL
         * =========================================
         */
        BorderPane root = new BorderPane();

        root.setTop(enemyTeam);

        root.setCenter(actions);

        root.setBottom(allyTeam);

        BorderPane.setAlignment(enemyTeam, Pos.CENTER);
        BorderPane.setAlignment(actions, Pos.CENTER);
        BorderPane.setAlignment(allyTeam, Pos.CENTER);

        root.setPadding(new Insets(40));

        /*
         * =========================================
         * ESCENA
         * =========================================
         */
        Scene scene = new Scene(root, 1000, 700);

        String css = BattleScene.class
                .getResource("/view/styles/Battle.css")
                .toExternalForm();

        scene.getStylesheets().add(css);

        return scene;
    }

    /*
     * =========================================
     * ACTUALIZA TODAS LAS CARDS
     * =========================================
     */
    private static void updateAll(
            WarriorCard... cards) {

        for (WarriorCard card : cards) {

            if (card != null) {
                card.update();
            }
        }
        
    }
    private static void checkBattleEnd(
        Stage stage,
        BattleController battle) {

    if (battle.isBattleOver()) {

        boolean playerWon =
                battle.isPlayerAlive();

        stage.setScene(
                EndScene.create(
                        stage,
                        playerWon));
    }
}
    
}