package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.game.placement.AiPlacement;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

public class AiGrid extends Grille {
    private Case[][] aiCases;
    private Case[][] playerCases;
    private final GridPane aiGrid;
    private final GridPane grid;
    private final Attack attack;
    private AiAttack aiAttack;
    private GameController controller;
    boolean attackSuccessed;
    private AiPlacement placement;
    private boolean isItYourTurn;

    PauseTransition pause = new PauseTransition(Duration.seconds(1));


    public AiGrid(GridPane aiGrid,GridPane grid ,GameController controller, Case[][] playerCases){
        this.aiGrid = aiGrid;
        this.grid = grid;
        this.controller = controller;
        attack = new Attack(controller);
        this.playerCases = playerCases;
        this.isItYourTurn = true;
    }

    public Case[][] createAiGrid(){
        aiCases = createGrid(aiGrid, "AI");
        aiAttack = new AiAttack(controller, playerCases);
        placement = new AiPlacement(aiCases);
        placement.placeAllBoats();
        for (Node child : aiGrid.getChildren()){
            Button btn = (Button) child;
            btn.setOnMouseClicked(_ -> {
                if (isItYourTurn) {attackSuccessed = shootOnAi(btn, aiGrid);}

                if (attackSuccessed) {
                    isItYourTurn = false;
                    pause.setOnFinished(_ -> {
                        aiAttack.aiTurn(grid);
                        isItYourTurn = true;
                    });
                    pause.play();
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