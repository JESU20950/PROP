package Data.Pieces;

import Data.Cell;

import java.util.List;


public abstract class Piece {
    protected boolean color;// white == true
    protected String name;
    protected List<Cell> Movement;
    protected Cell position;
    public Piece() {}


    abstract public boolean getColor();
    abstract public boolean correct_movement(Cell destino);

    abstract public String getName();
    abstract public void updateMovement(Cell[][] t, Cell origen);

    public List<Cell> getMovement() {
        return Movement;
    }

    public Cell getPosition() {
        return position;
    }
}