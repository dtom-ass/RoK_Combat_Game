package controller;

import view.*;
import view.console.ConsoleView;
import view.console.MainMenuView;

import java.util.Scanner;

public class StartMenu {

    private MainMenuView view = new MainMenuView();
    private Scanner sc = new Scanner(System.in);

    private PlayerController player;
    private BattleController battle;

    public void start() {

        view.showTitle();
        view.showMenu();

        if (!sc.hasNextInt()) {
            System.out.println("Entrada inválida");
            sc.next();
            return;
        }

        int menu = sc.nextInt();

        if (menu == 1) {
            SelectionController selector = new SelectionController(sc);
            var playerCulture = selector.selectCulture();

            this.player = new PlayerController(playerCulture);
            EnemyBot enemy = new EnemyBot();

            this.battle = new BattleController(player, enemy);

            view.clear();
            view.showNewBattle(player, enemy);

            battleLoop(); // flujo único

        } else if (menu == 0) {

            view.showExit();
            return;

        } else {
            System.out.println("Opción inválida");
        }
    }

    private void waitInput() {
        System.out.println("\n[Enter para continuar]");
        sc.nextLine();
    }

    /**
     * Loop principal de combate.
     */
    public void battleLoop() {

        while (!battle.isBattleOver()) {

            view.clear();
            battle.getBattleStatus();

            System.out.println("1. Atacar");
            System.out.println("2. Cambiar guerrero");
            System.out.print("Opción: ");

            int action = sc.nextInt();
            sc.nextLine(); // limpiar

            int selectedIndex = -1;

            if (action == 2) {
                new ConsoleView().showWarriors(player.getPlayerCulture());
                System.out.print("Índice: ");
                selectedIndex = sc.nextInt();
                sc.nextLine(); // limpiar
            }

            System.out.println("[1] Básico | [2] Especial");
            int atk = sc.nextInt();
            sc.nextLine(); // limpiar

            // Turno jugador
            battle.playerTurn(selectedIndex, atk);
            waitInput();

            // Turno enemigo
            if (battle.isEnemyAlive()) {
                battle.enemyTurn();
                waitInput();
            }

            view.clear(); // ahora sí limpia después de ver todo
        }

        battle.getResult();
    }
}