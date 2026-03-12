package school.coda.remy_axel_ethan.projet_java.tools;

import school.coda.remy_axel_ethan.projet_java.boat.Boat;

public class Case {
    private Boat occupiedBy;
    private boolean touched;
    private final int x;
    private final int y;

    public Case(int x, int y){
        this.occupiedBy = null;
        this.touched = false;
        this.x = x;
        this.y = y;
    }

    public Boat getOccupiedBy(){return this.occupiedBy;}
    public boolean getTouched(){return this.touched;}
    public int[] getPos(){
        return new int[]{this.x, this.y};
    }

    public void setOccupiedBy(Boat boat){this.occupiedBy = boat;}
    public void changeTouched(){this.touched = !this.touched;}
}
