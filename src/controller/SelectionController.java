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
import view.console.MainMenuView;

/**
 * Controla selección de cultura
 * y generación del equipo inicial.
 */
public class SelectionController {
    private static Culture selectedCulture;

    private final Scanner scanner;
    private final Random random;

    private final List<Culture> availableCultures = List.of(
            new AztecaCulture(),
            new IncaCulture(),
            new MayaCulture(),
            new MuiscaCulture());

    private void selectAzteca(){
        Culture selectedCulture = new AztecaCulture();
    }
    private void selectInca(){
        Culture selectedCulture = new IncaCulture();
    }
    private void selectMaya(){
        Culture selectedCulture = new MayaCulture();
    }
    private void selectMuisca(){
        Culture selectedCulture = new MuiscaCulture();
    }

    public SelectionController(Scanner scanner) {
        this.scanner = scanner;
        this.random = new Random();
    }

    public List<Culture> getAvailableCultures() {
        return availableCultures;
    }

    /**
     * Ejecuta proceso de selección.
     */
    public Culture selectCulture() {

        MainMenuView.showAvailableCultures(availableCultures);

        Culture selectedCulture = readCultureChoice();

        generateRandomTeam(selectedCulture);
        
        return selectedCulture;
    }

    /**
     * Lee la elección de cultura con validación.
     */
    private Culture readCultureChoice() {
        int choice = -1;

        while (choice < 1 || choice > availableCultures.size()) {
            ConsoleLog.Log("SELECCIONE UNA CULTURA: "); // ## MODIFICAR PARA SELECCIÓN POR EVENTO DE CLICK ##

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // limpia buffer
            } else {
                scanner.next(); // descarta inválido
            }
        }

        return availableCultures.get(choice - 1);
    }

    /**
     * Genera equipo aleatorio.
     */
    private void generateRandomTeam(Culture culture) {
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
    public static Culture getSelectedCulture(){
        return selectedCulture;
    }
}