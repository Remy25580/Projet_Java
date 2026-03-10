package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Grille {
    private Grille() {
        /* This utility class should not be instantiated */
    }

    public static final int GRID_SIZE = 10;
    public static final String CASE_X_POSITION = "x";
    public static final String CASE_Y_POSITION = "y";

    public static Case[][] createGrid(GridPane grid){
        Case[][] grille = new Case[GRID_SIZE][GRID_SIZE];
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y < GRID_SIZE; y++){
                Button button = new Button();
                button.setStyle("-fx-background-color: blue;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 0;");
                button.getProperties().put(CASE_X_POSITION,x);
                button.getProperties().put(CASE_Y_POSITION,y);
                grid.add(button, x, y);

                grille[x][y] = new Case(x,y);
            }
        }
        return grille;
    }
}
