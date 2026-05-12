package model.Warriors;

import java.util.List;
import model.Warrior;

public class Archer extends Warrior {
    private static final List<String> WEAPONS = List.of("Estólica", "Waraka", "Cerbatana", "Arco y Flecha");

    public Archer(String name) {
        super(name, 55, 0.7, 0.5, WEAPONS.get(0));
        setWarriorType("Arquero");
        setSpecial(1); // ## VALIDAR PARA ESTABILIZAR COMBATE ##
    }

    @Override
    public List<String> getArmsList() {
        return WEAPONS;
    }
}