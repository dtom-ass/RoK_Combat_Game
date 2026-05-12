// # PROSPECTO A SER ELIMINADO #
package view.console;

import java.util.ArrayList;
import java.util.List;
import model.Culture;
import model.Warrior;
import controller.*;

public class MainMenuView {

    private static ConsoleView view = new ConsoleView();

    /**
     * Limpia la consola de forma efectiva usando códigos ANSI.
     * Funciona en terminales modernos (CMD de Win 10+, PowerShell, Linux, macOS).
     */
    public static void clear() {
        // \033[H mueve el cursor al inicio, \033[2J limpia la pantalla
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Muestra un mensaje simple en pantalla.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    // Título del juego
    public static void showTitle() {
        clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║.       -------------        .║");
        System.out.println("║.    <| COMBATE - ROK |>     .║");
        System.out.println("║.       -------------        .║");
        System.out.println("╚══════════════════════════════╝");
    }

    // Opciones del menú principal
    public static void showMenu() {
        System.out.println("  [1] Iniciar Juego");
        System.out.println("  [0] Salir");
        System.out.print("\nElige una opción: ");
    }

    // Lista de culturas disponibles
    // El controlador le pasa la lista, la vista solo la imprime.
    /**
     * Muestra la lista de culturas disponibles para que el usuario elija.
     * 
     * @param cultures Lista de objetos de tipo Culture provenientes del
     *                 controlador.
     */
    public static void showAvailableCultures(List<Culture> cultures) {
        List<String> boxLines = new ArrayList<>();
        boxLines.add("JUEGO DE COMBATE RoK: SELECCIÓN");
        boxLines.add("Culturas disponibles:");
        boxLines.add("-".repeat(51)); // Línea divisoria interna

        for (int i = 0; i < cultures.size(); i++) {
            Culture c = cultures.get(i);
            // Usamos getName() y getSkill() definidos en la clase Culture [2]
            boxLines.add(String.format("[%d] %-10s ", (i + 1), c.getName()));
        }

        view.printBox(boxLines.toArray(new String[0]));
        System.out.print("Escriba el número de su elección: ");
    }

    // Equipo enemigo generado por EnemyBot
    // El controlador le pasa la Culture del bot, la vista solo la imprime.
    public static void showEnemyTeam(Culture enemyCulture) {
        clear(); // Limpia la pantalla antes de mostrar el equipo [1]
        List<String> boxLines = new ArrayList<>();
        boxLines.add("EQUIPO ENEMIGO: " + enemyCulture.getName().toUpperCase());
        boxLines.add("-".repeat(51));

        for (Warrior w : enemyCulture.getWarriorList()) {
            // Usamos getName(), getWarriorType() y getArmor() (que retorna el arma) de
            // Warrior [5]
            String warriorInfo = String.format("· %-12s | %-10s | %s",
                    w.getName(), w.getWarriorType(), w.getWeapon());
            boxLines.add(warriorInfo);
        }

        view.printBox(boxLines.toArray(new String[0]));
    }

    // Mensajes de estado
    public static void showInvalidOption() {
        System.out.println("  Opción no válida, intenta de nuevo.");
    }

    public static void showExit() {
        System.out.println("+==========================+");
        System.out.println("¡Hasta la próxima, guerrero!");
        System.out.println("+==========================+");
    }

    public static void showPlayerWarriors(PlayerController player) {

        System.out.println("\n-+== Guerreros del Jugador ==+-\n");
        for (int i = 0; i < player.getWarriorTeam().size(); i++) {
            System.out.printf(
                    "[%d] -> " + player.getWarriorTeam().get(i).getWarriorType()
                            + " | "
                            + player.getWarriorTeam().get(i).getName() + "\n",
                    i + 1);
        }
    }

    public static void showEnemyIniStat(EnemyBot enemy) {
        System.out.println("\n-+== Guerreros del Enemigo ==+-\n");
        for (Warrior warrior : enemy.getEnemyCulture().getWarriorList()) {
            System.out.printf("[+] %s | %s \n", warrior.getWarriorType(), warrior.getName());
        }
    }

    public static void showNewBattle(PlayerController player, EnemyBot enemy) {

        if (player.getActiveWarrior() == null || enemy.getEnemyWarrior() == null) {
            System.out.println("Error: guerrero no inicializado");
            return;
        }

        String linea1 = "NUEVA PARTIDA INICIADA";
        String linea2 = String.format("Jugador %s -< VS >- Enemigo %s",
                player.getPlayerCulture().getName(),
                enemy.getEnemyCulture().getName());

        view.printBox(linea1, linea2);
    }

}