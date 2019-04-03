package Data.Pieces;

import Data.Cell;

public abstract class Piece {
    protected boolean color;// white == true
    char name;

    public Piece() {}


    abstract public boolean getColor();
    abstract public boolean correct_movement(Cell origen, Cell destino);

    abstract public char getName();

}