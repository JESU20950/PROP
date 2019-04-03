package Data.Pieces;

import Data.Cell;

public class Knight extends Piece {
    public Knight(boolean color){
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
        return (Math.abs(i_origen - i_destino) == 1 || Math.abs(j_origen - j_destino) == 1)
                && destino.getPiece().getColor() != origen.getPiece().getColor();

    }
}
