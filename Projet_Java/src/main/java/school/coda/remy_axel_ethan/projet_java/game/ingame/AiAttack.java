package school.coda.remy_axel_ethan.projet_java.game.ingame;

import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Random;

public class AiAttack extends Attack{
    private final Random rand = new Random();

    private void AiTurn(){
        Case target = null;
        int xRandom;
        int yRandom;

        while (target == null){
            xRandom = rand.nextInt(10);
            yRandom = rand.nextInt(10);
            if(!cases[xRandom][yRandom].getTouched()){
                target = cases[xRandom][yRandom];
            }
        }
        shoot(target);
    }

}
