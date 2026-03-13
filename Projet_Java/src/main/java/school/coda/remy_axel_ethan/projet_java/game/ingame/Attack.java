package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

public class Attack {

    private final GameController controller;

    public Attack(GameController controller) {
        this.controller = controller;
    }

    public boolean shoot(Case target, GridPane targetGrid) {
        if (!isCaseValide(target)) {
            return false;
        }
        target.changeTouched();
        IO.println(target.getOwner());
        isOccupied(target, targetGrid);
        return true;
    }

    private boolean isCaseValide(Case target) {
        if (target.getTouched()) {
            controller.resultShoot("Cette case a déjà été touchée !!");
            return false;
        }
        return true;
    }

    private void isOccupied(Case target, GridPane targetGrid) {
        if(target.getOccupiedBy() != null) {
            controller.resultShoot(target.getOccupiedBy().getType() + " touché !");
            controller.updateColorCase(true, target, targetGrid);
            target.getOccupiedBy().receiveDamage();
            return;
        }
        controller.resultShoot("Aucune cible touchée . . .");
        controller.updateColorCase(false, target, targetGrid);
    }
}
