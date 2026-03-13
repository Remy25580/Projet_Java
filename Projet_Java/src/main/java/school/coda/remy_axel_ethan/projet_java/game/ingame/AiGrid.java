package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

public class AiGrid extends Grille {
    private Case[][] aiCases;
    private GridPane aiGrid;
    private final Attack attack;

    public AiGrid(GridPane aiGrid, GameController controller){
        this.aiGrid = aiGrid;
        attack = new Attack(controller);
    }

    public void createAiGrid(){
        aiCases = createGrid(aiGrid);
        for (Node child : aiGrid.getChildren()){
            Button btn = (Button) child;
            btn.setOnMouseClicked(_ -> shootOnAi(btn));
        }
    }

    public void shootOnAi(Button btn){
        int x = (int) btn.getProperties().get(CASE_X_POSITION);
        int y = (int) btn.getProperties().get(CASE_Y_POSITION);
        Case target = aiCases[x][y];
        attack.shoot(target);
    }
}
