package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

public class AiGrid extends Grille {
    private Case[][] aiCases;
    private Case[][] playerCases;
    private final GridPane aiGrid;
    private final GridPane grid;
    private final Attack attack;
    private AiAttack aiAttack;
    GameController controller;
    boolean attackSuccessed;


    public AiGrid(GridPane aiGrid,GridPane grid ,GameController controller, Case[][] playerCases){
        this.aiGrid = aiGrid;
        this.grid = grid;
        this.controller = controller;
        attack = new Attack(controller);
        this.playerCases = playerCases;
    }

    public Case[][] createAiGrid(){
        aiCases = createGrid(aiGrid, "AI");
        aiAttack = new AiAttack(controller, playerCases); //je dois passer la grille du joueur
        for (Node child : aiGrid.getChildren()){
            Button btn = (Button) child;
            btn.setOnMouseClicked(_ -> {
                attackSuccessed = shootOnAi(btn, aiGrid);
                if (attackSuccessed) {
                    aiAttack.aiTurn(grid);
                }
            });
        }
        return aiCases;
    }

    public boolean shootOnAi(Button btn, GridPane targetGrid){
        int x = (int) btn.getProperties().get(CASE_X_POSITION);
        int y = (int) btn.getProperties().get(CASE_Y_POSITION);
        Case target = aiCases[x][y];
        return attack.shoot(target, targetGrid);
    }
}