package school.coda.remy_axel_ethan.projet_java.boat;

public class Boat {

    private final String name;
    private final int size;
    private int casesTouch = 0;

    public Boat(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public int getSize() { return size; }

    public boolean isSank(){
        return casesTouch >= size;
    }
    public boolean receiveDamage(){
        casesTouch++;
        return isSank();
    }
}
