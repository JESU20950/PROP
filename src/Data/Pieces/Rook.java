package Data.Pieces;

import Data.Cell;

import javax.swing.text.StyledEditorKit;

public class Rook extends Piece {
    public Rook(boolean color){
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
