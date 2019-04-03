package Data.Pieces;

import Data.Cell;

import javax.swing.text.StyledEditorKit;

public class Rook extends Piece {
    public Rook(boolean color){
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
        return (i_origen == i_destino || j_origen == j_destino)
                && destino.getPiece().getColor() != origen.getPiece().getColor();


    }
}
