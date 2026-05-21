package controller;

import java.util.List;
import java.util.Random;

import model.Culture;
import model.Warrior;
import view.console.ConsoleLog;

/**
 * Controla al jugador durante la batalla.
 */
public class PlayerController {

    // Generador aleatorio
    private final Random random;

    // Cultura del jugador
    private final Culture playerCulture;

    // Guerrero activo
    private Warrior activeWarrior;

    // Índice actual
    private int activeWarriorIndex;

    // Estado del equipo
    private boolean alive;

    /**
     * Constructor principal.
     */
    public PlayerController(Culture culture) {

        this.random = new Random();

        this.playerCulture = culture;

        initializeTeam();
    }

    /**
     * Inicializa el equipo.
     */
    private void initializeTeam() {

        List<Warrior> team =
                playerCulture.getWarriorList();

        if (team.isEmpty()) {

            alive = false;

            activeWarrior = null;

            activeWarriorIndex = -1;

            return;
        }

        alive = true;

        activeWarriorIndex = 0;

        activeWarrior = team.get(0);

        ConsoleLog.Log(
                "Jugador inicia con: "
                        + activeWarrior.getName());
    }

    /**
     * Cambia el guerrero activo.
     */
    public void switchWarrior(int chosenIndex) {

        List<Warrior> team =
                playerCulture.getWarriorList();

        // Índice inválido
        if (chosenIndex < 0
                || chosenIndex >= team.size()) {

            return;
        }

        // Evita cambiar al mismo
        if (chosenIndex == activeWarriorIndex) {

            return;
        }

        activeWarriorIndex = chosenIndex;

        activeWarrior = team.get(chosenIndex);

        ConsoleLog.Log(
                "Jugador cambia a: "
                        + activeWarrior.getName());
    }

    /**
     * Ejecuta ataque básico.
     * 
     * Daño:
     * 2 a 5
     */
    public double basicAttack() {

        return 2 + random.nextInt(4);
    }

    /**
     * Ejecuta ataque especial.
     * 
     * 35% crítico:
     * daño entre 5 y 10.
     * 
     * Si falla:
     * ataque básico.
     */
    public double specialAttack() {

        if (random.nextDouble() <= 0.35) {

            ConsoleLog.Log(
                    "¡Golpe crítico aliado!");

            return 5 + random.nextInt(6);
        }

        return basicAttack();
    }

    /**
     * Aplica daño al guerrero activo.
     * 
     * @return true si murió
     */
    public boolean receiveAttack(double damage) {

        if (activeWarrior == null) {

            return false;
        }

        activeWarrior.updateHealth(-damage);

        ConsoleLog.Log(
                activeWarrior.getName()
                        + " recibe "
                        + damage
                        + " puntos de daño.");

        if (activeWarrior.getHealth() <= 0) {

            removeDeadWarrior();

            return true;
        }

        return false;
    }

    /**
     * Elimina guerrero derrotado.
     */
    private void removeDeadWarrior() {

        ConsoleLog.Log(
                activeWarrior.getName()
                        + " ha sido eliminado.");

        playerCulture.removeWarrior(
                activeWarriorIndex);

        List<Warrior> team =
                playerCulture.getWarriorList();

        // Equipo derrotado
        if (team.isEmpty()) {

            alive = false;

            activeWarrior = null;

            activeWarriorIndex = -1;

            ConsoleLog.Log(
                    "Equipo aliado derrotado.");

            return;
        }

        // Nuevo guerrero activo
        activeWarriorIndex = 0;

        activeWarrior = team.get(0);

        ConsoleLog.Log(
                "Nuevo aliado activo: "
                        + activeWarrior.getName());
    }

    /**
     * Retorna guerrero activo.
     */
    public Warrior getActiveWarrior() {
        return activeWarrior;
    }

    /**
     * Retorna equipo del jugador.
     */
    public List<Warrior> getWarriorTeam() {

        return playerCulture.getWarriorList();
    }

    /**
     * Retorna cultura del jugador.
     */
    public Culture getPlayerCulture() {
        return playerCulture;
    }

    /**
     * Indica si el jugador sigue vivo.
     */
    public boolean isAlive() {
        return alive;
    }
}