package Domain.Pieces;


import Domain.Cell;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(boolean color){
        super.color = color;
        if (color) super.name = "R";
        else super.name = "r";
    }


    public boolean getColor() {
        return super.color;
    }


    public boolean correct_movement(Cell[][] t, Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = (i_origen == i_destino || j_origen == j_destino);
        return ((movimiento_ok && destino.getPiece() == null) ||
                (movimiento_ok && destino.getPiece().getColor() != origen.getPiece().getColor())) && Nobody_in_trajectory(t, origen,destino);


    }




    public String getName() {
        return super.name;
    }


    public void updateMovement(Cell[][] t, Cell origen) {
        int i = origen.getI();
        int j = origen.getJ();
        super.position = origen;
        List<Cell> resultat = new ArrayList<Cell>();

        for (int vector = 0; vector<8; ++vector){
            if (vector != j && Nobody_in_trajectory(t,origen,t[i][vector])) resultat.add(t[i][vector]);
            if (vector != i && Nobody_in_trajectory(t,origen,t[vector][j])) resultat.add(t[vector][j]);
        }
        super.Movement = resultat;
    }
}
