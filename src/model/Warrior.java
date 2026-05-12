package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase base abstracta para todos los guerreros.
 * Define atributos y comportamiento común.
 */
public abstract class Warrior {
    private String name;
    private double lifePoints;
    private double attack;
    private double defence;
    private String weapon;
    private String origin;
    private String warriorType;
    private static final Set<String> USED_NAMES = new HashSet<>();

    /**
     * Constructor principal.
     * Inicializa atributos básicos del guerrero.
     */
    public Warrior(String name, double lifePoints, double attack, double defence, String weapon) {
        // Validamos nombres disponibles.
        if (USED_NAMES.contains(name)) {
            throw new IllegalArgumentException("Nombre ya usado: " + name);
        }

        USED_NAMES.add(name);

        this.name = name;
        this.lifePoints = lifePoints;
        this.attack = attack;
        this.defence = defence;
        this.weapon = weapon;
    }

    /**
     * Modifica la vida del guerrero.
     * Evita que la vida sea negativa.
     */
    public void updateLife(double amount) {
        this.lifePoints = Math.max(0, this.lifePoints + amount);
    }

    /**
     * Ajusta el ataque.
     * No valida límites.
     */
    protected void updateAttack(double amount) {
        this.attack += amount;
    }

    /**
     * Ajusta la defensa.
     * No valida límites.
     */
    protected void updateDefence(double amount) {
        this.defence += amount;
    }

    /**
     * Define el origen del guerrero.
     */
    protected void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Define el tipo de guerrero.
     */
    protected void setWarriorType(String type) {
        this.warriorType = type;
    }


    /** 
     * Aplica bonificación según tipo.
     * Usa valores numéricos que no son autoexplicativos.
     */
    // ## VALIDAR PARA ESTABILIZAR COMBATE ##
    protected void setSpecial(int special) {
        switch (special) {
            case 1 -> this.attack *= 1.1;
            case 2 -> this.defence *= 1.1;
            case 3 -> this.lifePoints += 10;
        }
    }

    /**
     * Devuelve la vida.
     * Redundante porque ya se controla en updateLife.
     */
    public double getLife() {
        return Math.max(0, lifePoints);
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public String getName() {
        return name;
    }

    public String getWarriorType() {
        return warriorType;
    }

    /**
     * Define el arma actual.
     * Uso de String limita escalabilidad.
     */
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /**
     * Retorna el arma equipada.
     */
    public String getWeapon() {
        return weapon;
    }

    /**
     * Método abstracto que obliga a definir armas disponibles.
     */
    public abstract List<String> getArmsList();

    
    public int hashCode() {
        return name.hashCode();
    }
}