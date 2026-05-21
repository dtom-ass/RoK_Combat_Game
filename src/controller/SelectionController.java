package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
 * Controla:
 * 
 * - selección de cultura
 * - generación del equipo inicial
 * - inicio de partida
 */
public class SelectionController {

    // Tamaño máximo del equipo
    private static final int TEAM_SIZE = 3;

    // Panel principal del juego
    private final PlayerPanel panel;

    // Generador aleatorio
    private final Random random;

    // Cultura seleccionada
    private Culture selectedCulture;

    /**
     * Culturas disponibles.
     */
    private final List<Culture> availableCultures = List.of(
            new AztecaCulture(),
            new IncaCulture(),
            new MayaCulture(),
            new MuiscaCulture());

    /**
     * Constructor principal.
     */
    public SelectionController(PlayerPanel panel) {

        this.panel = panel;
        this.random = new Random();
    }

    /**
     * Selecciona cultura Azteca.
     */
    public void selectAzteca() {

        startNewGame(
                new AztecaCulture());
    }

    /**
     * Selecciona cultura Inca.
     */
    public void selectInca() {

        startNewGame(
                new IncaCulture());
    }

    /**
     * Selecciona cultura Maya.
     */
    public void selectMaya() {

        startNewGame(
                new MayaCulture());
    }

    /**
     * Selecciona cultura Muisca.
     */
    public void selectMuisca() {

        startNewGame(
                new MuiscaCulture());
    }

    /**
     * Inicializa una nueva partida.
     */
    private void startNewGame(Culture culture) {

        selectedCulture = culture;

        ConsoleLog.Log(
                "Cultura seleccionada: "
                        + culture.getName());

        generateRandomTeam(culture);

        panel.newGame(culture);
    }

    /**
     * Genera el equipo inicial.
     */
    private void generateRandomTeam(Culture culture) {

        ConsoleLog.Log(
                "Generando equipo aliado...");

        List<String> names =
                new ArrayList<>(
                        culture.getWarriorNameList());

        // Mezcla nombres
        Collections.shuffle(names);

        // Limita tamaño del equipo
        int teamSize =
                Math.min(
                        TEAM_SIZE,
                        names.size());

        for (int i = 0; i < teamSize; i++) {

            Warrior warrior =
                    createRandomWarrior(
                            names.get(i));

            assignRandomWeapon(warrior);

            culture.addWarrior(warrior);

            ConsoleLog.Log(
                    "Nuevo aliado: "
                            + warrior.getName()
                            + " ["
                            + warrior.getWarriorType()
                            + "]");
        }
    }

    /**
     * Asigna arma aleatoria.
     */
    private void assignRandomWeapon(Warrior warrior) {

        List<String> weapons =
                warrior.getArmsList();

        warrior.setWeapon(
                weapons.get(
                        random.nextInt(
                                weapons.size())));
    }

    /**
     * Genera un guerrero aleatorio.
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

    /**
     * Retorna culturas disponibles.
     */
    public List<Culture> getAvailableCultures() {
        return availableCultures;
    }

    /**
     * Retorna cultura seleccionada.
     */
    public Culture getSelectedCulture() {
        return selectedCulture;
    }
}