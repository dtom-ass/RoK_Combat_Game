package model.Warriors;

import java.util.List;

import model.Warrior;

/**
 * Guerrero de soporte con alta supervivencia.
 */
public class Healer extends Warrior {

    // Herramientas y armas del curandero
    private static final List<String> WEAPONS = List.of(
            "Bolsas de Hierbas",
            "Agujas de Maguey",
            "Báculo Ritual",
            "Totem Espiritual");

    /**
     * Constructor principal.
     * 
     * @param name Nombre del guerrero
     */
    public Healer(String name) {

        super(
                name,
                50,
                3,
                1,
                WEAPONS.get(0));

        setWarriorType("Curandero");

        // Bonus de vida
        applySpecialBonus(3);
    }

    /**
     * Retorna las armas disponibles.
     */
    @Override
    public List<String> getArmsList() {

        return WEAPONS;
    }
}