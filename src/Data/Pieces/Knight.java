package Data.Pieces;

import Data.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

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


    public void updateMovement(Cell[][] t, Cell origen){
        int i = origen.getI();
        int j = origen.getJ();

        super.position = origen;


        List<Cell> resultat = new ArrayList<Cell>();
        if (i-2 < 8 && i-2 >= 0 && j-1 < 8 && j-1 >= 0) resultat.add(t[i-2][j-1]);
        if (i-2 < 8 && i-2 >= 0 && j+1 < 8 && j+1 >= 0) resultat.add(t[i-2][j+1]);
        if (i+2 < 8 && i+2 >= 0 && j-1 < 8 && j-1 >= 0) resultat.add(t[i+2][j-1]);
        if (i+2 < 8 && i+2 >= 0 && j+1 < 8 && j+1 >= 0) resultat.add(t[i+2][j+1]);

        if (i-1 < 8 && i-1 >= 0 && j-2 < 8 && j-2 >= 0) resultat.add(t[i-1][j-2]);
        if (i+1 < 8 && i+1 >= 0 && j-2 < 8 && j-2 >= 0) resultat.add(t[i+1][j-2]);
        if (i-1 < 8 && i-1 >= 0 && j+2 < 8 && j+2 >= 0) resultat.add(t[i-1][j+2]);
        if (i+1 < 8 && i+1 >= 0 && j+2 < 8 && j+2 >= 0) resultat.add(t[i+1][j+2]);
        super.Movement = resultat;
    }

}
