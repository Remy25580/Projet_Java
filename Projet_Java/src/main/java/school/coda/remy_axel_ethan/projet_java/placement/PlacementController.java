package school.coda.remy_axel_ethan.projet_java.placement;

import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Arrays;

public class PlacementController {
    private Case[][] grille;

    public void setGrille(Case[][] grille){
        this.grille = grille;
    }

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
        IO.println(c.getPos());
    }
}
