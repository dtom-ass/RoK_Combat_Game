package controller;

import model.Warrior;
import view.console.ConsoleLog;

/**
 * Controla el flujo principal de la batalla.
 * 
 * Administra:
 * - turnos
 * - daño
 * - estado del combate
 * - resultado final
 */
public class BattleController {

    // Tipo de ataque especial
    private static final int SPECIAL_ATTACK = 2;

    // Controladores principales
    private final PlayerController player;
    private final EnemyBot enemy;

    /**
     * Inicializa la batalla.
     */
    public BattleController(
            PlayerController player,
            EnemyBot enemy) {

        this.player = player;
        this.enemy = enemy;

        ConsoleLog.Log(
                "=== INICIANDO BATALLA ===");

        ConsoleLog.Log(
                "Jugador inicia con: "
                        + player.getActiveWarrior().getName());

        ConsoleLog.Log(
                "Enemigo inicia con: "
                        + enemy.getEnemyWarrior().getName());
    }

    /**
     * Ejecuta el turno del jugador.
     */
    public void playerTurn(
            int selectedWarriorIndex,
            int attackType) {

        ConsoleLog.Log(
                "===== TURNO DEL JUGADOR =====");

        // Validación de combate
        if (!isCombatValid()) {
            return;
        }

        // Cambio de guerrero
        if (selectedWarriorIndex >= 0) {

            player.switchWarrior(
                    selectedWarriorIndex);
        }

        Warrior attacker =
                player.getActiveWarrior();

        Warrior target =
                enemy.getEnemyWarrior();

        String attackerName =
                attacker.getName();

        String targetName =
                target.getName();

        ConsoleLog.Log(
                attackerName
                        + " ataca a "
                        + targetName);

        ConsoleLog.Log(
                "Arma equipada: "
                        + attacker.getWeapon());

        // Selección de ataque
        double rawDamage =
                (attackType == SPECIAL_ATTACK)
                        ? player.specialAttack()
                        : player.basicAttack();

        // Cálculo final
        double finalDamage =
                calculateDamage(
                        rawDamage,
                        target.getDefence());

        ConsoleLog.Log(
                targetName
                        + " recibe "
                        + finalDamage
                        + " puntos de daño.");

        // Aplicar daño
        boolean killed =
                enemy.receiveAttack(finalDamage);

        // Validar eliminación
        if (killed) {

            ConsoleLog.Log(
                    targetName
                            + " fue eliminado.");

            if (enemy.getEnemyWarrior() != null) {

                ConsoleLog.Log(
                        "Nuevo enemigo activo: "
                                + enemy.getEnemyWarrior().getName());
            }
        }

        getBattleStatus();
    }

    /**
     * Ejecuta el turno del enemigo.
     */
    public void enemyTurn() {

        ConsoleLog.Log(
                "===== TURNO DEL ENEMIGO =====");

        // Validación de combate
        if (!isCombatValid()) {
            return;
        }

        Warrior attacker =
                enemy.getEnemyWarrior();

        Warrior target =
                player.getActiveWarrior();

        String attackerName =
                attacker.getName();

        String targetName =
                target.getName();

        ConsoleLog.Log(
                attackerName
                        + " ataca a "
                        + targetName);

        ConsoleLog.Log(
                "Arma equipada: "
                        + attacker.getWeapon());

        // Ataque enemigo
        double rawDamage =
                enemy.playTurn();

        // Cálculo final
        double finalDamage =
                calculateDamage(
                        rawDamage,
                        target.getDefence());

        ConsoleLog.Log(
                targetName
                        + " recibe "
                        + finalDamage
                        + " puntos de daño.");

        // Aplicar daño
        boolean killed =
                player.receiveAttack(finalDamage);

        // Validar eliminación
        if (killed) {

            ConsoleLog.Log(
                    targetName
                            + " fue eliminado.");

            if (player.getActiveWarrior() != null) {

                ConsoleLog.Log(
                        "Nuevo aliado activo: "
                                + player.getActiveWarrior().getName());
            }
        }

        getBattleStatus();
    }

    /**
     * Verifica si el combate sigue siendo válido.
     */
    private boolean isCombatValid() {

        if (player.getActiveWarrior() == null
                || enemy.getEnemyWarrior() == null) {

            ConsoleLog.Log(
                    "COMBATE FINALIZADO");

            return false;
        }

        return true;
    }

    /**
     * Calcula el daño final.
     * 
     * Usa defensa plana:
     * daño final = ataque - defensa
     */
    private double calculateDamage(
            double rawDamage,
            double defence) {

        ConsoleLog.Log(
                "ATK: "
                        + rawDamage
                        + " | DEF: "
                        + defence);

        double finalDamage =
                rawDamage - defence;

        // Daño mínimo garantizado
        finalDamage =
                Math.max(finalDamage, 1);

        ConsoleLog.Log(
                "Daño final: "
                        + finalDamage);

        return finalDamage;
    }

    /**
     * Muestra estado actual del combate.
     */
    public boolean getBattleStatus() {

        ConsoleLog.Log(
                "===== ESTADO DEL COMBATE =====");

        Warrior playerWarrior =
                player.getActiveWarrior();

        Warrior enemyWarrior =
                enemy.getEnemyWarrior();

        if (playerWarrior == null
                || enemyWarrior == null) {

            ConsoleLog.Log(
                    "BATALLA FINALIZADA");

            return false;
        }

        String allyData =
                String.format(
                        "%s | HP: %.1f | ATK: %.1f | DEF: %.1f",
                        playerWarrior.getName(),
                        playerWarrior.getHealth(),
                        playerWarrior.getAttack(),
                        playerWarrior.getDefence());

        String enemyData =
                String.format(
                        "%s | HP: %.1f | ATK: %.1f | DEF: %.1f",
                        enemyWarrior.getName(),
                        enemyWarrior.getHealth(),
                        enemyWarrior.getAttack(),
                        enemyWarrior.getDefence());

        ConsoleLog.Log(
                "ALIADO  -> "
                        + allyData);

        ConsoleLog.Log(
                "ENEMIGO -> "
                        + enemyData);

        return true;
    }

    /**
     * Muestra resultado final.
     */
    public void getResult() {

        ConsoleLog.Log(
                "===== RESULTADO =====");

        if (!enemy.isAlive()) {

            ConsoleLog.Log(
                    "VICTORIA: enemigos derrotados.");
        }

        if (!player.isAlive()) {

            ConsoleLog.Log(
                    "DERROTA: equipo aliado eliminado.");
        }
    }

    /**
     * Indica si el jugador sigue vivo.
     */
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    /**
     * Indica si el enemigo sigue vivo.
     */
    public boolean isEnemyAlive() {
        return enemy.isAlive();
    }

    /**
     * Indica si la batalla terminó.
     */
    public boolean isBattleOver() {

        return !player.isAlive()
                || !enemy.isAlive();
    }

    /**
     * Retorna el controlador del jugador.
     */
    public PlayerController getPlayer() {
        return player;
    }

    /**
     * Retorna el bot enemigo.
     */
    public EnemyBot getEnemy() {
        return enemy;
    }
}