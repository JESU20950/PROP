package Data.Pieces;

import Data.Cell;
import java.lang.Math;

public class Bishop extends Piece {
    public Bishop(boolean color){
        super.color = color;
    }



    public boolean getColor() {
        return super.color;
    }
    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getJ();
        int j_origen = origen.getJ();
        if (Math.abs(i_origen - i_destino) == Math.abs(j_origen - j_destino) && destino.getPiece() == null)
        return Math.abs(i_origen - i_destino) == Math.abs(j_origen - j_destino)
                && destino.getPiece().getColor() != origen.getPiece().getColor();


    }


}
