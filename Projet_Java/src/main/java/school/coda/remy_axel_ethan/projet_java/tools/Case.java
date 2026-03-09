package school.coda.remy_axel_ethan.projet_java.tools;

public class Case {
    private boolean occupied;
    private boolean touched;
    private final int xPos;
    private final int yPos;

    public Case(int x, int y){
        this.occupied = false;
        this.touched = false;
        this.xPos = x;
        this.yPos = y;
    }

    public boolean getOccupied(){return this.occupied;}
    public boolean getTouched(){return this.touched;}
    public int[] getPos(){
        return new int[]{this.xPos, this.yPos};
    }

    public void changeOccupied(){this.occupied = !this.occupied;}
    public void changeTouched(){this.touched = !this.touched;}
}
