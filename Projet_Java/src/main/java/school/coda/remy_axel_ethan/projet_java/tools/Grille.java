package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.function.BiConsumer;

public class Grille {

    public static final String CASE_X_POSITION = "x";
    public static final String CASE_Y_POSITION = "y";

    public static Case[][] createGrid(GridPane grid/*, BiConsumer<Integer, Integer> onClick*/){
        Case[][] grille = new Case[10][10];
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){

                Button btn = new Button();

          btn.getProperties().put(CASE_X_POSITION,x);
          btn.getProperties().put(CASE_Y_POSITION,y);
                grid.add(btn, x, y);

                grille[x][y] = new Case(x,y);

                /*int fx = x;
                int fy = y;

                btn.setOnMouseClicked(_ -> onClick.accept(fx, fy));*/
            }
        }
        return grille;
    }
}
