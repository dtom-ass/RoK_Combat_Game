package view.console;

import java.util.ArrayList;
import java.util.List;

import model.Culture;
import model.Warrior;

/*
 * Vista para interacción por consola.
 */
public class ConsoleView {

    public void showWarriors(Culture culture) {

        List<String> lines = new ArrayList<>();

        // Título
        lines.add("EQUIPO: " + culture.getName().toUpperCase());
        lines.add("=".repeat(50));

        // Encabezado
        String header = String.format("%-3s | %-12s | %-6s | %-6s | %-6s",
                "ID", "NOMBRE", "VIDA", "ATK", "DEF");

        lines.add(header);
        lines.add("-".repeat(50));

        List<Warrior> team = culture.getWarriorList();

        for (int i = 0; i < team.size(); i++) {
            Warrior w = team.get(i);

            // Limitar nombre a 12 caracteres
            String name = w.getName().length() > 12
                    ? w.getName().substring(0, 12)
                    : w.getName();

            String row = String.format("%-3d | %-12s | %6.1f | %6.2f | %6.2f",
                    i,
                    name,
                    w.getLife(),
                    w.getAttack(),
                    w.getDefence());

            lines.add(row);
        }

        // Imprimir usando tu caja
        printBox(lines.toArray(new String[0]));
    }

    /**
     * Imprime una lista de mensajes dentro de un cuadro con ancho fijo.
     */
    public void printBox(String... lines) {
        int width = 60;
        String border = "+" + "=".repeat(width - 2) + "+";

        System.out.println(border);
        for (String line : lines) {
            // Ajuste de padding para mantener el cuadro alineado
            int padding = width - line.length() - 4;
            // El formato %s%ns asegura que el borde derecho '|' sea uniforme
            System.out.printf("| %s%" + (padding > 0 ? padding : "") + "s |%n", line, "");
        }
        System.out.println(border);
    }
}