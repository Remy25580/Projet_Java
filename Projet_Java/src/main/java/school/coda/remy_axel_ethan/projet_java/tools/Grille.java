package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Grille {

    public static final String CASE_X_POSITION = "x";
    public static final String CASE_Y_POSITION = "y";

    public static Case[][] createGrid(GridPane grid){
        Case[][] grille = new Case[10][10];
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){

                Button btn = new Button();
                btn.setStyle("-fx-background-color: blue;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 0;");




                btn.getProperties().put(CASE_X_POSITION,x);
                btn.getProperties().put(CASE_Y_POSITION,y);
                grid.add(btn, x, y);

                grille[x][y] = new Case(x,y);
            }
        }
        return grille;
    }
}
