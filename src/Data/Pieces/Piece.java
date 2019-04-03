package Data.Pieces;

import Data.Cell;

public abstract class Piece {
    protected boolean color;// white == true
    protected Cell c;
    public Piece() {}

    abstract public Cell getCell();
    abstract public void setCell(Cell c);
    abstract public boolean getColor();
}