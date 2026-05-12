// # PROSPECTO A SER ELIMINADO #
package view.console;

import model.Warrior;

public class BattleInConsole {
    private static ConsoleView view = new ConsoleView();

    public static void showWarriorChange(Warrior activeWarrior) {

        if (activeWarrior == null) {
            System.out.println("Error: guerrero no disponible");
            return;
        }

        System.out.printf(
                "# Guerrero seleccionado: %s | %s | vida:%.1f atk:%.2f def:%.2f | arma:%s%n",
                activeWarrior.getWarriorType(),
                activeWarrior.getName(),
                activeWarrior.getLife(),
                activeWarrior.getAttack(),
                activeWarrior.getDefence(),
                activeWarrior.getWeapon());
    }

    public static void attackSelector() {
        System.out.println("- Lanzar ataque -");
        System.out.println("[1]: Basico");
        System.out.println("[2]: Especial");
    }

    public static void showAttack(Warrior attacker, Warrior target, double damage) {

        if (attacker == null || target == null) {
            System.out.println("Error: ataque inválido");
            return;
        }

        String line1 = String.format("%s ataca a %s con %s", attacker.getName(), target.getName(),
                attacker.getWeapon());
        String line2 = String.format("Daño causado: %5.1f", damage);
        String line3 = String.format("Vida restante de %s: %5.1f", target.getName(), target.getLife());

        view.printBox(line1, line2, line3);
    }
}