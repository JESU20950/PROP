package Data.Pieces;

import Data.Cell;

public class Queen extends Piece {
    public Queen(boolean color){
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
        return (Math.abs(i_origen - i_destino) == Math.abs(j_origen - j_destino) ||
                (i_origen == i_destino || j_origen == j_destino))
                && destino.getPiece().getColor() != origen.getPiece().getColor();



    }
}
