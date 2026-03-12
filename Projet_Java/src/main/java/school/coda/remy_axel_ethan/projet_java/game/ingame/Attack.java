package school.coda.remy_axel_ethan.projet_java.game.ingame;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import school.coda.remy_axel_ethan.projet_java.game.GameController;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

public class Attack extends GameController {

    public void shoot(Case target){
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        if (target.getTouched()){
            caseTouchedErrorMessage.setText("Cette case a déjà été touchée !!");
            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
            pause.play();
            return;
        }
        target.changeTouched();
        if(target.getOccupiedBy() != null){
            target.getOccupiedBy().receiveDamage();
            getButtonFromACase(target).setStyle("-fx-background-color: red;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 0;");
            caseTouchedErrorMessage.setText(target.getOccupiedBy().getType() + " touché !");
            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
            pause.play();
        }else{
            target.getOccupiedBy().receiveDamage();
            getButtonFromACase(target).setStyle("-fx-background-color: gray;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 0;");
            caseTouchedErrorMessage.setText("Aucune cible touchée . . .");
            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
            pause.play();
        }
    }

}
