package school.coda.remy_axel_ethan.projet_java.placement;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Arrays;

public class PlacementController {
    private Case[][] grille;

    @FXML
    private GridPane grid;

    public void setGrille(Case[][] grille){
        this.grille = grille;
    }

    public GridPane getGrid(){return this.grid;}

    public Case getACase(int x, int y){
        for(Case[] line : this.grille){
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
