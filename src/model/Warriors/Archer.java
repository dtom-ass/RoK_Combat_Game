package model.Warriors;

import java.util.List;

import model.Warrior;

/**
 * Guerrero especializado en ataques rápidos
 * y ofensiva a distancia.
 */
public class Archer extends Warrior {

    // Armas disponibles para el arquero
    private static final List<String> WEAPONS = List.of(
            "Estólica",
            "Waraka",
            "Cerbatana",
            "Arco y Flecha");

    /**
     * Constructor principal del arquero.
     * 
     * @param name Nombre del guerrero
     */
    public Archer(String name) {

        super(
                name,
                55,
                5,
                1,
                WEAPONS.get(0));

        setWarriorType("Arquero");

        // Bonus ofensivo
        applySpecialBonus(1);
    }

    /**
     * Retorna las armas disponibles
     * para este tipo de guerrero.
     */
    @Override
    public List<String> getArmsList() {

        return WEAPONS;
    }
}