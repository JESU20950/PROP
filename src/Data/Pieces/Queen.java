package Data.Pieces;

import Data.Cell;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.min;

public class Queen extends Piece {
    public Queen(boolean color){
        super.color = color;
        if (color) super.name = "Q";
        else super.name = "q";
    }


    public boolean getColor() {
        return super.color;
    }
    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = (Math.abs(i_origen - i_destino) == Math.abs(j_origen - j_destino) ||
                (i_origen == i_destino || j_origen == j_destino));
        return (movimiento_ok && destino.getPiece() == null) ||
                (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor());



    }


    public String getName() {
        return super.name;
    }



    public void updateMovement(Cell[][] t, int i, int j) {
        List<Cell> resultat = new ArrayList<Cell>();

        for (int vector = 0; vector<8; ++vector){
            if (vector != j) resultat.add(t[i][vector]);
            if (vector != i) resultat.add(t[vector][j]);
        }
        int diagonal1i = i-min(i,j);
        int diagonal1j = j-min(i,j);
        while (diagonal1i < 8 && diagonal1j<8){
            if (i != diagonal1i && diagonal1j != j) resultat.add(t[diagonal1i][diagonal1j]);
            ++diagonal1i;
            ++diagonal1j;
        }
        diagonal1i = i-min(i,j);
        diagonal1j = j+min(i,j);
        while (diagonal1i >= 0 && diagonal1j < 8){
            if (i!= diagonal1i && j != diagonal1j) resultat.add(t[diagonal1i][diagonal1j]);
            --diagonal1i;
            ++diagonal1j;
        }
        super.Movement = resultat;
    }

}
