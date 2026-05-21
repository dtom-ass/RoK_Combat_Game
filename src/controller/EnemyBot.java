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
 * Controla el comportamiento del equipo enemigo.
 * 
 * Administra:
 * - generación del equipo
 * - IA básica
 * - ataques
 * - cambios de guerrero
 * - estado del enemigo
 */
public class EnemyBot {

    // Generador aleatorio
    private final Random random;

    // Cultura enemiga
    private Culture enemyCulture;

    // Guerrero enemigo activo
    private Warrior activeWarrior;

    // Índice del guerrero activo
    private int activeWarriorIndex;

    // Estado del equipo enemigo
    private boolean alive;

    /**
     * Inicializa el bot enemigo.
     */
    public EnemyBot() {

        this.random = new Random();

        generateEnemyTeam();

        initializeTeam();
    }

    /**
     * Inicializa el primer guerrero enemigo.
     */
    private void initializeTeam() {

        List<Warrior> team = enemyCulture.getWarriorList();

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
                "Enemigo inicia con: "
                        + activeWarrior.getName());
    }

    /**
     * Genera la cultura y el equipo enemigo.
     */
    private void generateEnemyTeam() {

        ConsoleLog.Log(
                "Generando equipo enemigo...");

        setRandomCulture();

        ConsoleLog.Log(
                "Cultura enemiga: "
                        + enemyCulture.getName());

        List<String> names = enemyCulture.getWarriorNameList();

        List<Integer> indices = new ArrayList<>();

        // Genera índices únicos
        for (int i = 0; i < names.size(); i++) {
            indices.add(i);
        }

        Collections.shuffle(indices);

        // Genera máximo 3 guerreros
        for (int i = 0; i < Math.min(3, names.size()); i++) {

            String name = names.get(indices.get(i));

            Warrior warrior = createRandomWarrior(name);

            assignRandomWeapon(warrior);

            enemyCulture.addWarrior(warrior);

            ConsoleLog.Log(
                    "Nuevo enemigo: "
                            + warrior.getName()
                            + " ["
                            + warrior.getWarriorType()
                            + "]");
        }
    }

    /**
     * Asigna un arma aleatoria al guerrero.
     */
    private void assignRandomWeapon(Warrior warrior) {

        List<String> weapons = warrior.getArmsList();

        warrior.setWeapon(
                weapons.get(
                        random.nextInt(weapons.size())));
    }

    /**
     * Selecciona una cultura aleatoria.
     */
    private void setRandomCulture() {

        switch (random.nextInt(4)) {

            case 0 -> enemyCulture = new AztecaCulture();

            case 1 -> enemyCulture = new IncaCulture();

            case 2 -> enemyCulture = new MayaCulture();

            default -> enemyCulture = new MuiscaCulture();
        }
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
     * Ejecuta el turno enemigo.
     * 
     * Puede:
     * - cambiar guerrero
     * - usar ataque básico
     * - usar ataque especial
     */
    public double playTurn() {

        // Posible cambio de guerrero
        if (random.nextBoolean()) {
            switchWarrior();
        }

        // Selección de ataque
        return random.nextBoolean()
                ? basicAttack()
                : specialAttack();
    }

    /**
     * Cambia el guerrero enemigo activo.
     */
    private void switchWarrior() {

        List<Warrior> team = enemyCulture.getWarriorList();

        if (team.size() <= 1) {
            return;
        }

        int nextIndex;

        // Evita repetir el mismo guerrero
        do {

            nextIndex = random.nextInt(team.size());

        } while (nextIndex == activeWarriorIndex);

        activeWarriorIndex = nextIndex;
        activeWarrior = team.get(nextIndex);

        ConsoleLog.Log(
                "El enemigo cambia a: "
                        + activeWarrior.getName());
    }

    /**
     * Ejecuta ataque básico.
     * 
     * Daño:
     * 2 a 5
     */
    public double basicAttack() {

        double damage = 2 + random.nextInt(4);

        ConsoleLog.Log(
                activeWarrior.getName()
                        + " realiza ataque básico.");

        return damage;
    }

    /**
     * Ejecuta ataque especial.
     * 
     * 35% probabilidad:
     * daño crítico entre 5 y 10
     * 
     * Si falla:
     * ataque básico.
     */
    public double specialAttack() {

        ConsoleLog.Log(
                activeWarrior.getName()
                        + " intenta ataque especial.");

        // Crítico exitoso
        if (random.nextDouble() <= 0.35) {

            double criticalDamage = 5 + random.nextInt(6);

            ConsoleLog.Log(
                    "¡Ataque crítico enemigo!");

            return criticalDamage;
        }

        ConsoleLog.Log(
                "Ataque especial enemigo fallido.");

        return basicAttack();
    }

    /**
     * Aplica daño al guerrero enemigo activo.
     * 
     * @return true si el guerrero murió
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
     * Elimina un guerrero derrotado.
     */
    private void removeDeadWarrior() {

        ConsoleLog.Log(
                activeWarrior.getName()
                        + " ha sido eliminado.");

        enemyCulture.removeWarrior(activeWarriorIndex);

        List<Warrior> team = enemyCulture.getWarriorList();

        // Equipo derrotado
        if (team.isEmpty()) {

            alive = false;
            activeWarrior = null;
            activeWarriorIndex = -1;

            ConsoleLog.Log(
                    "El equipo enemigo fue derrotado.");

            return;
        }

        // Nuevo guerrero activo
        activeWarriorIndex = 0;
        activeWarrior = team.get(0);

        ConsoleLog.Log(
                "Nuevo enemigo activo: "
                        + activeWarrior.getName());
    }

    /**
     * Retorna la cultura enemiga.
     */
    public Culture getEnemyCulture() {
        return enemyCulture;
    }

    /**
     * Retorna el guerrero enemigo activo.
     */
    public Warrior getEnemyWarrior() {
        return activeWarrior;
    }

    /**
     * Retorna el equipo enemigo completo.
     */
    public List<Warrior> getEnemyTeam() {
        return enemyCulture.getWarriorList();
    }

    /**
     * Indica si el enemigo sigue vivo.
     */
    public boolean isAlive() {
        return alive;
    }
}