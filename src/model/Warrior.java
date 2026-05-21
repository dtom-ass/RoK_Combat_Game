package model;

import java.util.List;

/**
 * Clase base para todos los guerreros del juego.
 * 
 * Define atributos y comportamiento común.
 */
public abstract class Warrior {

    /*
     * Datos principales
     */

    private String name;

    private double health;

    private double attack;

    private double defence;

    /*
     * Información adicional
     */

    private String weapon;

    private String warriorType;

    private String origin;

    /**
     * Constructor principal.
     */
    public Warrior(
            String name,
            double health,
            double attack,
            double defence,
            String weapon) {

        validateName(name);

        this.name = name;

        this.health = health;

        this.attack = attack;

        this.defence = defence;

        this.weapon = weapon;
    }

    /**
     * Valida nombre.
     */
    private void validateName(String name) {

        if (name == null || name.isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }
    }

    /**
     * Modifica vida.
     */
    public void updateHealth(double amount) {

        health = Math.max(
                0,
                health + amount);
    }

    /**
     * Modifica ataque.
     */
    protected void updateAttack(double amount) {

        attack += amount;
    }

    /**
     * Modifica defensa.
     */
    protected void updateDefence(double amount) {

        defence += amount;
    }

    /**
     * Aplica bonus especial.
     * 
     * 1 = ataque
     * 2 = defensa
     * 3 = vida
     */
    protected void applySpecialBonus(int specialType) {

        switch (specialType) {

            case 1 -> attack *= 1.1;

            case 2 -> defence *= 1.1;

            case 3 -> health += 10;
        }
    }

    /**
     * Define tipo.
     */
    protected void setWarriorType(String warriorType) {

        this.warriorType = warriorType;
    }

    /**
     * Define cultura/origen.
     */
    protected void setOrigin(String origin) {

        this.origin = origin;
    }

    /**
     * Cambia arma.
     */
    public void setWeapon(String weapon) {

        this.weapon = weapon;
    }

    /*
     * Getters
     */

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getWarriorType() {
        return warriorType;
    }

    public String getOrigin() {
        return origin;
    }

    /**
     * Lista de armas disponibles.
     */
    public abstract List<String> getArmsList();

    /**
     * Representación básica.
     */
    @Override
    public String toString() {

        return String.format(
                "%s [%s] HP: %.1f",
                name,
                warriorType,
                health);
    }
}