package school.coda.remy_axel_ethan.projet_java.tools;

import school.coda.remy_axel_ethan.projet_java.boat.Boat;

public class Case {
    private Boat occupiedBy;
    private boolean touched;
    private final int xPos;
    private final int yPos;

    public Case(int x, int y){
        this.occupiedBy = null;
        this.touched = false;
        this.xPos = x;
        this.yPos = y;
    }

    public Boat getOccupiedBy(){return this.occupiedBy;}
    public boolean getTouched(){return this.touched;}
    public int[] getPos(){
        return new int[]{this.xPos, this.yPos};
    }

    public void setOccupiedBy(Boat boat){this.occupiedBy = boat;}
    public void changeTouched(){this.touched = !this.touched;}
}
