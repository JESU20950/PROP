package Data.Pieces;

import Data.Cell;

import javax.swing.text.StyledEditorKit;

public class Rook extends Piece {
    public Rook(boolean color){
        super.color = color;
        if (color) super.name = 'R';
        else super.name = 'r';
    }


    public boolean getColor() {
        return super.color;
    }

    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getJ();
        int j_origen = origen.getJ();
        boolean movimiento_ok = (i_origen == i_destino || j_origen == j_destino);
        return (movimiento_ok && destino.getPiece() == null) ||
                (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor());


    }

    public char getName() {
        return super.name;
    }
}
