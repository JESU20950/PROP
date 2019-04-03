package Data.Pieces;

import Data.Cell;

public class Knight extends Piece {
    public Knight(boolean color){
        super.color = color;
        if (color) super.name = "N";
        else super.name = "n";
    }



    public boolean getColor() {
        return super.color;
    }


    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = (Math.abs(i_destino-i_origen) == 2 && Math.abs(j_destino-j_origen) ==1) ||
                (Math.abs(j_destino-j_origen) == 2 && Math.abs(i_destino-i_origen) == 1);
        return movimiento_ok && ( destino.getPiece() == null ||origen.getPiece().getColor() != destino.getPiece().getColor());

    }

    public String getName() {
        return super.name;
    }
}
