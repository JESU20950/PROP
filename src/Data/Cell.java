package Data;

import Data.Pieces.*;

public class Cell {

    Piece piece;

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

    public void setpiece(Piece piece){
        this.piece = piece;
    }



}
