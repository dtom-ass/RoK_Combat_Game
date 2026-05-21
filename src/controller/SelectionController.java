package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Culture;
import model.Cultures.AztecaCulture;
import model.Cultures.IncaCulture;
import model.Cultures.MayaCulture;
import model.Cultures.MuiscaCulture;
import model.Warrior;
import model.Warriors.Archer;
import model.Warriors.Fighter;
import model.Warriors.Healer;
import model.Warriors.Lancer;
import model.Warriors.Tank;
import view.console.ConsoleLog;

/**
 * Controla selección de cultura
 * y generación del equipo inicial.
 */
public class SelectionController {
    private static Culture selectedCulture;
    private PlayerPanel panel;
    private final Random random = new Random();

    public SelectionController(PlayerPanel panel) {
        this.panel = panel;
    }

    private final List<Culture> availableCultures = List.of(
            new AztecaCulture(),
            new IncaCulture(),
            new MayaCulture(),
            new MuiscaCulture());

    public void selectAzteca() {
        ConsoleLog.Log("Cultura Azteca elegida.");
        Culture selectedCulture = new AztecaCulture();
        generateRandomTeam(selectedCulture);
        panel.newGame(selectedCulture);

    }

    public void selectInca() {
        ConsoleLog.Log("Cultura Inca elegida.");
        Culture selectedCulture = new IncaCulture();
        generateRandomTeam(selectedCulture);
        panel.newGame(selectedCulture);
    }

    public void selectMaya() {
        ConsoleLog.Log("Cultura Maya elegida.");
        Culture selectedCulture = new MayaCulture();
        generateRandomTeam(selectedCulture);
        panel.newGame(selectedCulture);

    }

    public void selectMuisca() {
        ConsoleLog.Log("Cultura Muisca elegida.");
        Culture selectedCulture = new MuiscaCulture();
        generateRandomTeam(selectedCulture);
        panel.newGame(selectedCulture);

    }

    public List<Culture> getAvailableCultures() {
        return availableCultures;
    }

    /**
     * Ejecuta proceso de selección.
     */
    public Culture selectCulture() {
        generateRandomTeam(selectedCulture);

        return selectedCulture;
    }

    /**
     * Lee la elección de cultura con validación.
     */

    /**
     * Genera equipo aleatorio.
     */
    public void generateRandomTeam(Culture culture) {
        ConsoleLog.Log("Creando equipo del jugador...");
        ConsoleLog.Log("Cultura Aliada: " + culture.getName());
        List<String> names = new ArrayList<>(culture.getWarriorNameList());
        Collections.shuffle(names);

        int teamSize = Math.min(3, names.size());

        for (int i = 0; i < teamSize; i++) {

            Warrior warrior = createRandomWarrior(names.get(i));

            // Asigna arma aleatoria (consistencia con EnemyBot)
            warrior.setWeapon(
                    warrior.getArmsList().get(
                            random.nextInt(warrior.getArmsList().size())));

            culture.addWarrior(warrior);
            ConsoleLog.Log("Nuevo guerrero aliado: " + warrior.getName() + " " + warrior.getWarriorType());
        }
    }

    /**
     * Crea guerrero aleatorio.
     */
    private Warrior createRandomWarrior(String name) {
        return switch (random.nextInt(5)) {
            case 0 -> new Archer(name);
            case 1 -> new Fighter(name);
            case 2 -> new Healer(name);
            case 3 -> new Lancer(name);
            default -> new Tank(name);
        };
    }

    public Culture getSelectedCulture() {
        return selectedCulture;
    }
}