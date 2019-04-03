package Data.Pieces;

import Data.Cell;

public class Pawn extends Piece {
    public Pawn(boolean color){
        super.color = color;
    }

    public Cell getCell() {
        return super.c;
    }

    public void setCell(Cell c) {
        super.c = c;
    }

    public boolean getColor() {
        return super.color;
    }
}
