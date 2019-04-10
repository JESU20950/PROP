package Data.Pieces;

import Data.Cell;

import java.util.List;


public abstract class Piece {
    protected boolean color;// white == true
    protected String name;
    protected List<Cell> Movement;
    public Piece() {}


    abstract public boolean getColor();
    abstract public boolean correct_movement(Cell origen, Cell destino);

    abstract public String getName();
    abstract public void updateMovement(Cell[][] t, int i, int j);

    public List<Cell> getMovement() {
        return Movement;
    }
}