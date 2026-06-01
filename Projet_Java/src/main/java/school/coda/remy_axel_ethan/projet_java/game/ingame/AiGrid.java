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
    private final Case[][] playerCases;
    private final GridPane gridAi;
    private final GridPane grid;
    private final Attack attack;
    private final GameController controller;
    private final PauseTransition pause = new PauseTransition(Duration.seconds(1));
    boolean attackSuccess;
    private Case[][] aiCases;
    private boolean isItYourTurn;


    public AiGrid(GridPane gridAi, GridPane grid, GameController controller, Case[][] playerCases) {
        this.gridAi = gridAi;
        this.grid = grid;
        this.controller = controller;
        attack = new Attack(controller);
        this.playerCases = playerCases;
        this.isItYourTurn = true;
    }

    public Case[][] createAiGrid() {

        AiAttack attackAi;
        aiCases = createGrid(gridAi, "AI");
        attackAi = new AiAttack(controller, playerCases);
        AiPlacement placement = new AiPlacement(aiCases);
        placement.placeAllBoats();
        for (Node child : gridAi.getChildren()) {
            Button btn = (Button) child;
            btn.setOnMouseClicked(_ -> {
                if (isItYourTurn) {
                    attackSuccess = shootOnAi(btn, gridAi);
                    controller.addPlayerShot();
                }


                if (attackSuccess) {
                    isItYourTurn = false;
                    pause.setOnFinished(_ -> {
                        attackAi.aiTurn(grid);
                        controller.addIaShots();
                        isItYourTurn = true;
                    });
                    pause.play();
                }
            });
        }
        return aiCases;
    }

    public boolean shootOnAi(Button btn, GridPane targetGrid) {
        int x = (int) btn.getProperties().get(CASE_X_POSITION);
        int y = (int) btn.getProperties().get(CASE_Y_POSITION);
        Case target = aiCases[x][y];
        return attack.shoot(target, targetGrid);
    }
}