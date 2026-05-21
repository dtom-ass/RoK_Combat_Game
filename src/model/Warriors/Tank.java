package model.Warriors;

import java.util.List;

import model.Warrior;

/**
 * Guerrero resistente especializado en defensa.
 */
public class Tank extends Warrior {

    // Armas disponibles del tanque
    private static final List<String> WEAPONS = List.of(
            "Ichcahuipilli",
            "Chimalli",
            "Escudo de Madera",
            "Cascos de Metal");

    /**
     * Constructor principal.
     * 
     * @param name Nombre del guerrero
     */
    public Tank(String name) {

        super(
                name,
                90,
                2,
                4,
                WEAPONS.get(0));

        setWarriorType("Tanque");

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