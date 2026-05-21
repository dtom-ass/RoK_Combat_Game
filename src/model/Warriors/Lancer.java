package model.Warriors;

import java.util.List;

import model.Warrior;

/**
 * Guerrero ofensivo especializado en armas largas.
 */
public class Lancer extends Warrior {

    // Armas disponibles del lancero
    private static final List<String> WEAPONS = List.of(
            "Tepoztopilli",
            "Lanza de Chonta",
            "Nab'te",
            "Pica de Bronce");

    /**
     * Constructor principal.
     * 
     * @param name Nombre del guerrero
     */
    public Lancer(String name) {

        super(
                name,
                60,
                5,
                2,
                WEAPONS.get(0));

        setWarriorType("Lancero");

        // Bonus ofensivo
        applySpecialBonus(1);
    }

    /**
     * Retorna las armas disponibles.
     */
    @Override
    public List<String> getArmsList() {

        return WEAPONS;
    }
}