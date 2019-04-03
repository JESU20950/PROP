package Data.Pieces;

import Data.Cell;

public class Queen extends Piece {
    public Queen(boolean color){
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
