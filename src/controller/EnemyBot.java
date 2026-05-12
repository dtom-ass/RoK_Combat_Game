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
 * Controla el comportamiento del bot enemigo.
 * Genera cultura, equipo y decisiones de combate.
 */
public class EnemyBot {

    private static final double SPECIAL_MULTIPLIER = 1.5;

    private Culture enemyCulture;
    private Warrior enemyWarrior;
    private int activeWarriorIndex;
    private boolean alive;

    private final Random random = new Random();

    /**
     * Inicializa el bot enemigo.
     */
    public EnemyBot() {

        alive = true;
        activeWarriorIndex = 0;

        generateEnemyTeam();

        // Validación para evitar acceso inválido
        List<Warrior> team = enemyCulture.getWarriorList();
        if (!team.isEmpty()) {
            enemyWarrior = team.get(0);
        }
    }

    /**
     * Genera cultura aleatoria y equipo enemigo.
     */
    private void generateEnemyTeam() {
        ConsoleLog.Log("Creando Equipo Enemigo...");
        setRandomCulture();
        ConsoleLog.Log("Cultura Enemiga: " + getEnemyCulture().getName());

        List<String> names = enemyCulture.getWarriorNameList();
        List<Integer> indices = new ArrayList<>();

        // Genera lista de índices para evitar repetir nombres
        for (int i = 0; i < names.size(); i++) {
            indices.add(i);
        }

        Collections.shuffle(indices);

        // Limita a máximo 3 o al tamaño disponible
        for (int i = 0; i < Math.min(3, names.size()); i++) {

            String name = names.get(indices.get(i));
            Warrior warrior = createRandomWarrior(name);

            // Asigna arma aleatoria del guerrero
            warrior.setWeapon(
                    warrior.getArmsList().get(
                            random.nextInt(warrior.getArmsList().size())));

            enemyCulture.addWarrior(warrior);
            ConsoleLog.Log("Nuevo guerrero enemigo: " + warrior.getName() + " " + warrior.getWarriorType());
        }
    }

    /**
     * Asigna una cultura aleatoria.
     */
    private void setRandomCulture() {
        switch (random.nextInt(4)) {
            case 0 -> this.enemyCulture = new AztecaCulture();
            case 1 -> this.enemyCulture = new IncaCulture();
            case 2 -> this.enemyCulture = new MayaCulture();
            case 3 -> this.enemyCulture = new MuiscaCulture();
        }
    }

    /**
     * Crea un guerrero aleatorio.
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
     * Ejecuta turno del bot.
     */
    public double playTurn() {

        // Puede cambiar de guerrero
        if (random.nextBoolean()) {
            switchWarrior();
        }

        // Decide tipo de ataque
        return random.nextBoolean()
                ? basicAttack()
                : specialAttack();
    }

    /**
     * Cambia el guerrero activo aleatoriamente.
     */
    private void switchWarrior() {

        List<Warrior> team = enemyCulture.getWarriorList();

        if (team.size() <= 1) {
            return;
        }

        int next;

        // Evita seleccionar el mismo guerrero
        do {
            next = random.nextInt(team.size());
        } while (next == activeWarriorIndex);

        activeWarriorIndex = next;
        enemyWarrior = team.get(next);
        ConsoleLog.Log("Equipo enemigo: Cambia al guerrero: " + enemyWarrior.getName());
    }

    /**
     * Ataque básico.
     */
    private double basicAttack() {
        return enemyWarrior.getAttack();
    }

    /**
     * Ataque especial.
     */
    private double specialAttack() {
        return enemyWarrior.getAttack() * SPECIAL_MULTIPLIER;
    }

    /**
     * Aplica daño recibido.
     */
    public boolean receiveAttack(double damage) {

        if (enemyWarrior == null)
            return false;

        enemyWarrior.updateLife(-damage);
        ConsoleLog.Log("Enemigo " + enemyWarrior.getName() + " recibe " + damage + " puntos de daño.");

        if (enemyWarrior.getLife() <= 0) {
            removeDeadWarrior();
            return true; // murió
        }

        return false;
    }

    /**
     * Elimina guerrero muerto y actualiza estado.
     */
    private void removeDeadWarrior() {

        ConsoleLog.Log("El guerrero enemigo " + enemyWarrior.getName() + " fue ELIMINADO");

        // FIX: no modificar lista inmodificable directamente
        enemyCulture.removeWarrior(activeWarriorIndex);

        List<Warrior> team = enemyCulture.getWarriorList();

        if (team.isEmpty()) {
            this.alive = false;
            this.enemyWarrior = null;
            ConsoleLog.Log("EQUIPO ENEMIGO DERROTADO");
            return;
        }

        // Selecciona nuevo guerrero activo
        this.activeWarriorIndex = 0;
        this.enemyWarrior = team.get(0);
    }

    public Culture getEnemyCulture() {
        return enemyCulture;
    }

    public Warrior getEnemyWarrior() {
        return enemyWarrior;
    }

    public boolean isAlive() {
        return alive;
    }

    public List<String> getWarriorNameList() {
        return enemyCulture.getWarriorNameList();
    }
}