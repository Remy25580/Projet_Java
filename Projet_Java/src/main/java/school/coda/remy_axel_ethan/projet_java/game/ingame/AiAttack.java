package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Objects;
import java.util.Random;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.GRID_SIZE;

public class AiAttack extends Attack{

    private final Random rand = new Random();
    private final Case[][] cases;
    private Case target;

    AiAttack(GameController controller, Case[][] cases) {
        super(controller);
        this.cases = cases;
    }

    public void aiTurn(GridPane targetGrid) {
        Case aitarget = getRandomTarget();
        shoot(aitarget, targetGrid);
    }

    private Case getRandomTarget() {
        while(true) {
            int xRandom = rand.nextInt(GRID_SIZE);
            int yRandom = rand.nextInt(GRID_SIZE);
            target = cases[xRandom][yRandom];
            if (!target.getTouched()) {
                return target;
            }
        }
    }
}
