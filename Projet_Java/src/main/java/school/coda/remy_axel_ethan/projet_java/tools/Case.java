package school.coda.remy_axel_ethan.projet_java.tools;

import school.coda.remy_axel_ethan.projet_java.boat.Boat;

public class Case {
    private final int x;
    private final int y;
    private final String owner;
    private Boat occupiedBy;
    private boolean touched;

    public Case(int x, int y, String owner) {
        this.occupiedBy = null;
        this.touched = false;
        this.x = x;
        this.y = y;
        this.owner = owner;
    }

    public Boat getOccupiedBy() {
        return this.occupiedBy;
    }

    public void setOccupiedBy(Boat boat) {
        this.occupiedBy = boat;
    }

    public boolean getTouched() {
        return this.touched;
    }

    public int[] getPos() {
        return new int[]{this.x, this.y};
    }

    public String getOwner() {
        return this.owner;
    }

    public void changeTouched() {
        this.touched = !this.touched;
    }
}
