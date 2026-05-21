package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import model.Warrior;

public class WarriorCard extends VBox {

    private Label nameLabel;
    private ProgressBar lifeBar;
    private Label hpLabel;

    private Warrior warrior;

    public WarriorCard(Warrior warrior) {

        this.warrior = warrior;

        nameLabel = new Label(warrior.getName());

        lifeBar = new ProgressBar();

        hpLabel = new Label();

        update();

        setSpacing(10);
        setAlignment(Pos.CENTER);

        getChildren().addAll(
                nameLabel,
                lifeBar,
                hpLabel);
    }

    public void update() {

        double maxLife = 100.0;

        double currentLife = warrior.getLife();

        lifeBar.setProgress(currentLife / maxLife);

        hpLabel.setText(
                "HP: " + String.format("%.1f", currentLife));
    }
}