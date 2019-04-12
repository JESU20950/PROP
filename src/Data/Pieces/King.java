package Data.Pieces;

import Data.Cell;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.min;

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
        boolean movimiento_ok = (Math.abs(i_destino - i_origen) == 1 && Math.abs(j_destino - j_origen) == 1) ||
                (Math.abs(i_destino - i_origen) == 1 && Math.abs(j_destino - j_origen) == 0)
                || (Math.abs(i_destino - i_origen) == 0 && Math.abs(j_destino - j_origen) == 1);
        return (movimiento_ok && destino.getPiece() == null) ||
                (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor());
    }







    public String getName() {
        return super.name;
    }

    public void updateMovement(Cell[][] t, Cell origen){
        int i = origen.getI();
        int j = origen.getJ();
        super.position = origen;


        List<Cell> resultat = new ArrayList<Cell>();
        int iaux = i-1;
        int jaux = j-1;
        for (int ii = 0; ii <3; ++ii ){
            for (int jj = 0; jj <3; ++jj){
                 if ((iaux + ii) < 8 && (iaux + ii) >= 0 && (jaux + jj) < 8 && (jaux + jj) >= 0 && ((iaux + ii) != i  || (jaux + jj) != j )) resultat.add(t[iaux + ii][jaux+jj] );
            }
        }
        super.Movement = resultat;
    }

}
