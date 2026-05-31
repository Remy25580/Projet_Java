package school.coda.remy_axel_ethan.projet_java.boat;

public class Boat {

    private final BoatType type;
    private int touchedCases = 0;

    public Boat(BoatType type) {
        this.type = type;
    }

    public int getSize() {
        return type.getSize();
    }

    public String getType() {
        return type.getType();
    }

    // Attention aux fautes d'orthographe
    public boolean isSunk() {
        return touchedCases >= type.getSize();
    }

    public boolean receiveDamage() {
        touchedCases++;
        return isSunk();
    }
}
