package controller;

import java.util.List;
import model.Culture;
import model.Warrior;
import view.console.ConsoleLog;

/**
 * Controla acciones del jugador durante la batalla.
 */
public class PlayerController {

    private static final double SPECIAL_MULTIPLIER = 1.5;

    private Culture playerCulture;
    private Warrior activeWarrior;
    private int activeWarriorIndex;
    private boolean alive;

    /**
     * Inicializa el controlador del jugador.
     */
    public PlayerController(Culture culture) {

        playerCulture = culture;
        activeWarriorIndex = 0;
        alive = true;

        List<Warrior> team = culture.getWarriorList();

        // Validación de equipo vacío
        if (!team.isEmpty()) {
            activeWarrior = team.get(0);
        } else {
            activeWarrior = null;
            alive = false;
        }
    }

    /**
     * Cambia guerrero activo.
     */
    public void switchWarrior(int chosenIndex) {

        List<Warrior> team = playerCulture.getWarriorList();

        if (chosenIndex == activeWarriorIndex)
            return;
        if (chosenIndex < 0 || chosenIndex >= team.size())
            return;

        activeWarriorIndex = chosenIndex;
        activeWarrior = team.get(chosenIndex);
        ConsoleLog.Log("Jugador cambia al guerrero: " + activeWarrior.getName());
    }

    /**
     * Ataque básico.
     */
    public double basicAttack() {
        ConsoleLog.Log("Realizando ataque Basico...");
        return activeWarrior.getAttack();
    }

    /**
     * Ataque especial.
     */
    public double specialAttack() {
        ConsoleLog.Log("Realizando ataque Especial...");
        return activeWarrior.getAttack() * SPECIAL_MULTIPLIER;
    }

    /**
     * Aplica daño recibido.
     */
    public boolean receiveAttack(double damage) {
        activeWarrior.updateLife(-damage);
        ConsoleLog.Log("El guerrero aliado " + activeWarrior.getName() + " recibio " + damage + " puntos de daño.");
        if (activeWarrior.getLife() <= 0) {
            removeDeadWarrior();
            return true; // murió
        }

        return false;
    }

    /**
     * Elimina guerrero derrotado.
     */
    private void removeDeadWarrior() {

        ConsoleLog.Log("Guerrero aliado " + activeWarrior.getName() + " ELIMINADO");
        // FIX: usar método interno de Culture
        playerCulture.removeWarrior(activeWarriorIndex);

        List<Warrior> team = playerCulture.getWarriorList();

        if (team.isEmpty()) {
            alive = false;
            activeWarrior = null;
            ConsoleLog.Log("EQUIPO ALIADO DERROTADO");
            return;
        }

        activeWarriorIndex = 0;
        activeWarrior = team.get(0);
    }

    public Warrior getActiveWarrior() {
        return activeWarrior;
    }

    public List<Warrior> getWarriorTeam() {
        return playerCulture.getWarriorList();
    }

    public Culture getPlayerCulture() {
        return playerCulture;
    }
    public Culture getEnemyCulture() {
        return playerCulture;
    }

    public boolean isAlive() {
        return alive;
    }
}