package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

public class Attack {

    private final GameController controller;
    private boolean isSank;

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
            controller.resultShoot("Cette case a déjà été touchée !!", false);
            return false;
        }
        return true;
    }

    private void isOccupied(Case target, GridPane targetGrid) {
        if(target.getOccupiedBy() != null) {
            isSank = target.getOccupiedBy().receiveDamage();
            if(isSank){controller.updateNumberOfBoats(target.getOwner());}
            controller.resultShoot(target.getOccupiedBy().getType() + " touché !", isSank);
            controller.updateColorCase(true, target, targetGrid);
            return;
        }
        controller.resultShoot("Aucune cible touchée . . .", false);
        controller.updateColorCase(false, target, targetGrid);
    }

}
