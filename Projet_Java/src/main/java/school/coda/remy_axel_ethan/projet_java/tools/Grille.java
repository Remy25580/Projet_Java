package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Grille {
    public static final int GRID_SIZE = 10;
    public static final String CASE_X_POSITION = "x";
    public static final String CASE_Y_POSITION = "y";

    public static Case[][] createGrid(GridPane grid){
        Case[][] grille = new Case[GRID_SIZE][GRID_SIZE];
        for(int x = 1; x <= GRID_SIZE; x++){
            for(int y = 1; y <= GRID_SIZE; y++){

                Button btn = new Button();
                btn.setStyle("-fx-background-color: blue;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 0;");

                btn.getProperties().put(CASE_X_POSITION,x);
                btn.getProperties().put(CASE_Y_POSITION,y);
                grid.add(btn, x, y);

                grille[x-1][y-1] = new Case(x,y);
            }
        }
        return grille;
    }
}
