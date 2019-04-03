package Data.Pieces;

import Data.Cell;
import java.lang.Math;

public class Bishop extends Piece {
    public Bishop(boolean color){
        super.color = color;
        if (color) super.name = "B";
        else super.name = "b";
    }



    public boolean getColor() {
        return super.color;
    }

    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = Math.abs(i_origen - i_destino) == Math.abs(j_origen - j_destino);
        return (movimiento_ok && destino.getPiece() == null) || (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor());


    }


    public String getName() {
        return super.name;
    }
}
