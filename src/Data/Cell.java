package Data;
import Data.Pieces.Piece;

public class Cell {
    Piece piece;


    Cell(){
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
