package school.coda.remy_axel_ethan.projet_java.game.placement;

import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.boat.BoatType;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.List;
import java.util.Random;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.GRID_SIZE;

public class AiPlacement {
    private final Case[][] aiCases;
    private final Random rand = new Random();

    public AiPlacement(Case[][] aiCases) {
        this.aiCases = aiCases;
    }

    public void placeAllBoats() {
        List<BoatType> boatsToPlace = List.of(BoatType.values());
        boatsToPlace.forEach(this::placeABoat);
    }

    private void placeABoat(BoatType type) {
        Boat boat = new Boat(type);
        boolean placed = false;

        while (!placed) {
            placed = tryRandomPlacement(boat);
        }
    }

    private boolean tryRandomPlacement(Boat boat) {
        int x = rand.nextInt(GRID_SIZE);
        int y = rand.nextInt(GRID_SIZE);
        boolean isHorizontal = rand.nextBoolean();

        CreationBoat creation = new CreationBoat(boat, isHorizontal, aiCases);
        return creation.tryPlacement(aiCases[x][y]);
    }
}