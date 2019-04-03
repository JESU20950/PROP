package Data.Pieces;

import Data.Cell;

public class King extends Piece{
    public King(boolean color){
        super.color = color;
        if (color) super.name = "K";
        else super.name = "k";
    }


    public boolean getColor() {
        return super.color;
    }

    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = (Math.abs(i_origen - i_destino) == 1 || Math.abs(j_origen - j_destino) == 1);
        return (movimiento_ok && destino.getPiece() == null) ||
                (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor());


    }

    public String getName() {
        return super.name;
    }
}
