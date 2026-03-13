package school.coda.remy_axel_ethan.projet_java.game.ingame;

import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

public class Attack {

    private final GameController controller;

    public Attack(GameController controller) {
        this.controller = controller;
    }

    public void shoot(Case target) {
        if (!isCaseValide(target)) {
            return;
        }
        target.changeTouched();
        isOccupied(target);
        target.getOccupiedBy().receiveDamage();
    }

    private boolean isCaseValide(Case target) {
        if (target.getTouched()) {
            controller.resultShoot("Cette case a déjà été touchée !!");
            return false;
        }
        return true;
    }

    private void isOccupied(Case target) {
        if(target.getOccupiedBy() != null) {
            controller.resultShoot(target.getOccupiedBy().getType() + " touché !");
            controller.updateColorCase(true, target);
            return;
        }
        controller.resultShoot("Aucune cible touchée . . .");
        controller.updateColorCase(false, target);
    }
}
