package view.console;

import javax.swing.*;
import java.awt.*;

public class ConsoleLog {

    private static JTextArea textArea;

    // Bloque static: se ejecuta una sola vez
    static {

        JFrame frame = new JFrame("Consola del Juego");

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void Log(String message){

        textArea.append("|| LOG ||: " + message + "\n");

        // Auto-scroll
        textArea.setCaretPosition(
                textArea.getDocument().getLength()
        );
    }
}