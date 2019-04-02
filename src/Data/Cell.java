package Data;

import Data.Pieces.*;

public class Cell {

    private Piece piece;

    private int i;

    private int j;

    public Cell(){
        piece = null;
    }

    public boolean isCellOccupied()
    {
        return piece == null;
    }

    public Piece getpiece(){
        return piece;
    }

    public void setpiece(Piece p){
        piece = p;
        if (p != null) p.setCell(this);
    }



}
