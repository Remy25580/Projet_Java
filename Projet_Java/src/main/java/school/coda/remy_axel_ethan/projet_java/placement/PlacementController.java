package school.coda.remy_axel_ethan.projet_java.placement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_X_POSITION;
import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_Y_POSITION;

public class PlacementController implements Initializable {
    private Case[][] cases;

    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.cases = Grille.createGrid(grid);
        for (Node child : grid.getChildren()) {
            Button button = (Button) child;
            button.setOnMouseClicked(e -> {
                Button targetButton = (Button)e.getTarget();
                int x = (int) targetButton.getProperties().get(CASE_X_POSITION);
                int y = (int) targetButton.getProperties().get(CASE_Y_POSITION);
                Case target = getACase(x, y);
                test(target);
            });
        }
    }


    public Case getACase(int x, int y){
        for(Case[] line : this.cases){
            for(Case c : line){
                int[] coordonnees = {x, y};
                if (Arrays.equals(c.getPos(), coordonnees)){
                    return c;
                }

            }
        }
        return null;
    }

    public void test(Case c){
        IO.println(Arrays.toString(c.getPos()));
    }


}
