package model.Warriors;

import java.util.List;

import model.Warrior;

/**
 * Guerrero equilibrado enfocado en combate cuerpo a cuerpo.
 */
public class Fighter extends Warrior {

    // Armas disponibles del peleador
    private static final List<String> WEAPONS = List.of(
            "Macuahuitl",
            "Macana",
            "Hacha",
            "Garrote",
            "Cuchillo");

    /**
     * Constructor principal.
     * 
     * @param name Nombre del guerrero
     */
    public Fighter(String name) {

        super(
                name,
                70,
                4,
                2,
                WEAPONS.get(0));

        setWarriorType("Peleador");

        // Bonus defensivo
        applySpecialBonus(2);
    }

    /**
     * Retorna las armas disponibles.
     */
    @Override
    public List<String> getArmsList() {

        return WEAPONS;
    }
}