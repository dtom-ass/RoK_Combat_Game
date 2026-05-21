package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import model.Warrior;

/**
 * Componente visual que representa un guerrero.
 */
public class WarriorCard extends VBox {

    // Guerrero asociado
    private final Warrior warrior;

    /*
     * Labels principales
     */

    private final Label nameLabel;
    private final Label typeLabel;
    private final Label originLabel;

    /*
     * Stats
     */

    private final Label weaponLabel;
    private final Label healthLabel;
    private final Label attackLabel;
    private final Label defenceLabel;

    /*
     * Barra de vida
     */

    private final ProgressBar healthBar;

    /*
     * Vida máxima visual
     * (No existe en Warrior)
     */
    private final double maxHealth;

    /**
     * Constructor principal.
     */
    public WarriorCard(Warrior warrior) {

        this.warrior = warrior;

        /*
         * Guardamos vida inicial
         * como vida máxima visual.
         */
        this.maxHealth =
                warrior.getHealth();

        /*
         * Configuración base
         */

        setSpacing(10);

        setAlignment(Pos.CENTER);

        setPrefWidth(200);

        getStyleClass().add(
                "warrior-card");

        /*
         * Crear componentes
         */

        nameLabel = new Label();

        typeLabel = new Label();

        originLabel = new Label();

        weaponLabel = new Label();

        healthLabel = new Label();

        attackLabel = new Label();

        defenceLabel = new Label();

        healthBar = new ProgressBar();

        healthBar.setPrefWidth(160);

        /*
         * CSS
         */

        nameLabel.getStyleClass()
                .add("warrior-name");

        typeLabel.getStyleClass()
                .add("warrior-type");

        originLabel.getStyleClass()
                .add("origin-label");

        weaponLabel.getStyleClass()
                .add("stat-label");

        healthLabel.getStyleClass()
                .add("stat-label");

        attackLabel.getStyleClass()
                .add("stat-label");

        defenceLabel.getStyleClass()
                .add("stat-label");

        /*
         * Agregar elementos
         */

        getChildren().addAll(

                nameLabel,

                typeLabel,

                originLabel,

                weaponLabel,

                healthBar,

                healthLabel,

                attackLabel,

                defenceLabel);
        
        /*
         * Primera actualización
         */

        update();
    }

    /**
     * Actualiza toda la card visual.
     */
    public void update() {

        if (warrior == null) {

            setDisable(true);

            return;
        }

        /*
         * Datos actuales
         */

        double currentHealth =
                warrior.getHealth();

        double progress =
                currentHealth / maxHealth;

        /*
         * Textos
         */

        nameLabel.setText(
                warrior.getName());

        typeLabel.setText(
                warrior.getWarriorType());

        originLabel.setText(
                warrior.getOrigin());

        weaponLabel.setText(
                "⚔ Arma: "
                        + warrior.getWeapon());

        healthLabel.setText(
                "❤ HP: "
                        + (int) currentHealth
                        + " / "
                        + (int) maxHealth);

        attackLabel.setText(
                "⚔ ATK: "
                        + warrior.getAttack());

        defenceLabel.setText(
                "🛡 DEF: "
                        + warrior.getDefence());

        /*
         * Barra de vida
         */

        healthBar.setProgress(progress);

        /*
         * Limpiar estilos previos
         */

        healthBar.getStyleClass().removeAll(
                "hp-high",
                "hp-medium",
                "hp-low",
                "hp-dead");

        getStyleClass().remove(
                "dead-card");

        /*
         * Estados visuales
         */

        if (currentHealth <= 0) {

            healthBar.getStyleClass()
                    .add("hp-dead");

            getStyleClass()
                    .add("dead-card");

        } else if (progress <= 0.30) {

            healthBar.getStyleClass()
                    .add("hp-low");

        } else if (progress <= 0.60) {

            healthBar.getStyleClass()
                    .add("hp-medium");

        } else {

            healthBar.getStyleClass()
                    .add("hp-high");
        }
    }

    /**
     * Retorna guerrero asociado.
     */
    public Warrior getWarrior() {

        return warrior;
    }
}