package Data.Pieces;

import Data.Cell;

public class Pawn extends Piece {
    public Pawn(boolean color){
        super.color = color;
        if (color) super.name = 'B';
        else super.name = 'b';
    }



    public boolean getColor() {
        return super.color;
    }


    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getJ();
        int j_origen = origen.getJ();
        return true;


    }


    public char getName() {
        return super.name;
    }
}
