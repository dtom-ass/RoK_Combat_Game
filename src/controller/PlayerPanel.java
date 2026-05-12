package controller;

import controller.SelectionController;
import model.Culture;
import view.console.ConsoleLog;

public class PlayerPanel {
    private PlayerController player;
    private BattleController battle;

    public void newGame(){
        Culture playerCulture = SelectionController.getSelectedCulture();
        this.player = new PlayerController(playerCulture);
        EnemyBot enemy = new EnemyBot();
        this.battle = new BattleController(player, enemy);
        // battleLoop();
    }
    int selectedIndex = -1;

    public void ChangeWarrior() {
        selectedIndex += 1;
        if (selectedIndex >= player.getWarriorTeam().size() ){
            selectedIndex = -1;
        }
    }

    public void basicAttack(){
        battle.playerTurn(selectedIndex, 1);
    }
    public void specialAttack(){
        battle.playerTurn(selectedIndex, 2);
    }
    /**
     * TURNO ENEMIGO SE COMPRUEBA DESPUES DEL ATAQUE DEL JUGADOR ##
            *if (battle.isEnemyAlive()) {
            *    battle.enemyTurn();
            *
            *}
            * 
            * Aun no lo defino, el juego fue planeado para que los ataques sean por turnos, pero podria hacerlo en combate mas tradicional.
            * Si se ejecuta el ataque enemigo de manera seudoaleatoria y no se limitan ataques del jugador.
            */

}
