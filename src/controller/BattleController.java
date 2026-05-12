package controller;

import model.Warrior;
import view.console.BattleInConsole;
import view.console.ConsoleLog;
import view.console.ConsoleView;

/**
 * Controla el flujo de la batalla.
 */
public class BattleController {

    private static final int SPECIAL_ATTACK = 2;

    private final PlayerController player;
    private final EnemyBot enemy;
    private final ConsoleView view = new ConsoleView();

    public BattleController(PlayerController player, EnemyBot enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * Turno del jugador.
     */
    public void playerTurn(int selectedWarriorIndex, int attackType) {
        ConsoleLog.Log("TURNO DEL JUGADOR");
        if (player.getActiveWarrior() == null || enemy.getEnemyWarrior() == null) {
            ConsoleLog.Log("COMBATE NO VALIDO");
        }

        if (selectedWarriorIndex >= 0) {
            player.switchWarrior(selectedWarriorIndex);
        }

        Warrior attacker = player.getActiveWarrior();
        Warrior target = enemy.getEnemyWarrior();

        String targetName = target.getName();

        ConsoleLog.Log("Guerrero aliado " + attacker.getName() + " ataca a " + targetName);

        double rawDamage = (attackType == 2)
                ? player.specialAttack()
                : player.basicAttack();

        double finalDamage = calculateDamage(rawDamage, target.getDefence());
        ConsoleLog.Log("Calculando daño realizado...");
        ConsoleLog.Log("El enemigo " + targetName + " Recibe un daño de " + finalDamage + " puntos.");

        boolean killed = enemy.receiveAttack(finalDamage);

        if (killed) {
            ConsoleLog.Log("Enemigo " + targetName + " fue ELIMINADO");;

            if (enemy.getEnemyWarrior() != null) {
                ConsoleLog.Log("Cambia al turno del Enemigo");
            }
        }
    }

    /**
     * Turno del enemigo.
     */
    public void enemyTurn() {
        ConsoleLog.Log("TURNO DEL ENEMIGO");

        if (player.getActiveWarrior() == null || enemy.getEnemyWarrior() == null) {
            ConsoleLog.Log("COMBATE NO VALIDO");
        }

        Warrior attacker = enemy.getEnemyWarrior();
        Warrior target = player.getActiveWarrior();

        String targetName = target.getName(); // guardar antes

        ConsoleLog.Log("Guerrero Enemigo " + attacker.getName() + " ataca al aliado " + targetName);

        double rawDamage = enemy.playTurn();
        double finalDamage = calculateDamage(rawDamage, target.getDefence());

        
        ConsoleLog.Log("Calculando daño realizado...");
        ConsoleLog.Log("El aliado " + targetName + " recibe un daño de " + finalDamage + " puntos por parte del enemigo.");

        boolean killed = player.receiveAttack(finalDamage);

        if (killed) {
            ConsoleLog.Log("Aliado " + targetName + " fue ELIMINADO");;

            // Mostrar nuevo guerrero activo automáticamente
            if (player.getActiveWarrior() != null) {
                ConsoleLog.Log("Cambia al turno del Jugador");
            }
        }
    }

    /**
     * Cálculo de daño.
     */
    private double calculateDamage(double attack, double defence) {
        ConsoleLog.Log("Operando ataque " + attack + " Y defensa " + defence);
        // Normaliza defensa para evitar valores fuera de rango
        double def = Math.max(0, Math.min(defence, 1));
        double damageGen = Math.max((attack * 30) * (1 - def), 1.0);
        ConsoleLog.Log("Calcalulo de defensa " + def + " Genera un total de " + damageGen + " puntos de daño."); // ## AJUSTAR ##
        
        return damageGen;
    }

    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    public boolean isEnemyAlive() {
        return enemy.isAlive();
    }

    public boolean isBattleOver() {
        return !player.isAlive() || !enemy.isAlive();
    }

    /**
     * Estado del combate.
     */
    public void getBattleStatus() {
        ConsoleLog.Log("ESTADO DEL COMBATE");
        Warrior pw = player.getActiveWarrior();
        Warrior ew = enemy.getEnemyWarrior();

        if (pw == null || ew == null) {
            ConsoleLog.Log("ESTADO DE LA BATALLA: FINALIZADA");
            return;
        }

        String pData = String.format("%-12s | HP: %5.1f",
                pw.getName(), pw.getLife(), pw.getAttack());


        String eData = String.format("%-12s | HP: %5.1f",
                ew.getName(), ew.getLife(), ew.getAttack());
        
        ConsoleLog.Log("ALIADO: " + pData);
        ConsoleLog.Log("ENEMIGO: " + eData);
    }

    /**
     * Resultado final.
     */
    public void getResult() {
        ConsoleLog.Log("ESTADO DEL JUEGO");
        if (!enemy.isAlive()) {
            ConsoleLog.Log("VICTORIA, ENEMIGOS DERROTADOS");
        }

        if (!player.isAlive()) {
            ConsoleLog.Log("DERROTA, ALIADOS VENCIDOS.");
        }

    }
}