package school.coda.remy_axel_ethan.projet_java.boat;

public class Boat {

    private final BoatType type;
    private int casesTouch = 0;

    public Boat(BoatType type) {
        this.type = type;
    }

    public String getName() { return type.getName(); }
    public int getSize() { return type.getSize(); }

    public boolean isSank(){
        return casesTouch >= type.getSize();
    }
    public boolean receiveDamage(){
        casesTouch++;
        return isSank();
    }
}
