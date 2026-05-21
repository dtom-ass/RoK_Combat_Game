package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una cultura del juego.
 * 
 * Cada cultura administra su propio equipo
 * de guerreros y una lista de nombres únicos.
 */
public abstract class Culture {

    // Nombre de la cultura
    private String name;

    // Equipo de guerreros pertenecientes a la cultura
    private final List<Warrior> warriorList;

    /**
     * Constructor principal.
     * 
     * @param name Nombre de la cultura
     */
    public Culture(String name) {

        this.name = name;
        this.warriorList = new ArrayList<>();
    }

    /**
     * Agrega un guerrero al equipo.
     * 
     * Evita:
     * - valores null
     * - guerreros duplicados
     */
    public void addWarrior(Warrior warrior) {

        if (warrior == null) {
            return;
        }

        if (!warriorList.contains(warrior)) {
            warriorList.add(warrior);
        }
    }

    /**
     * Elimina un guerrero por índice.
     */
    public void removeWarrior(int index) {

        if (index < 0 || index >= warriorList.size()) {
            return;
        }

        warriorList.remove(index);
    }

    /**
     * Retorna el equipo actual.
     * 
     * Se devuelve una lista inmodificable
     * para proteger el estado interno.
     */
    public List<Warrior> getWarriorList() {
        return Collections.unmodifiableList(warriorList);
    }

    /**
     * Retorna el nombre de la cultura.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de la cultura.
     */
    protected void setName(String name) {

        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    /**
     * Retorna los nombres disponibles
     * para generar guerreros.
     */
    public abstract List<String> getWarriorNameList();
}