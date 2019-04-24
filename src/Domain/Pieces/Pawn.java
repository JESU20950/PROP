package Domain.Pieces;


import Domain.Cell;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean color){
        super.color = color;
        if (color) super.name = "P";
        else super.name = "p";
    }



    public boolean getColor() {
        return super.color;
    }


    public boolean correct_movement(Cell[][] t, Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        if (origen.getPiece().getColor()) { // White pawn
            if (i_origen == 6) return i_origen - i_destino == 2 && j_destino - j_origen == 0 && Nobody_in_trajectory(t, origen, destino) && destino.getPiece() == null; // Can do double step
            else { // Can't do double step
                if (i_origen - i_destino == 1) { // Only one step in front
                    if (j_destino - j_origen == 0) return destino.getPiece() == null; // Single movement step
                    else if (Math.abs(j_destino - j_origen) == 1) return destino.getPiece() != null && destino.getPiece().getColor() != origen.getPiece().getColor(); // Attack movement
                    else return false;
                }
                else return false;
            }
        }
        else { // Black pawn
            if (i_origen == 1) return i_destino - i_origen == 2 && j_destino - j_origen == 0 && Nobody_in_trajectory(t, origen, destino) && destino.getPiece() == null; // Can do double step
            else {
                if (i_destino - i_origen == 1) { // Only one step in front
                    if (j_destino - j_origen == 0) return destino.getPiece() == null; //Sigle movement step
                    else if (Math.abs(j_destino - j_origen) == 1) return destino.getPiece() != null && destino.getPiece().getColor() != origen.getPiece().getColor(); // Attack movement
                    else return false;
                }
                else return false;
            }
        }
    }



    public String getName() {
        return super.name;
    }

    public void updateMovement(Cell[][] t, Cell origen){
        int i = origen.getI();
        int j = origen.getJ();

        super.position = origen;

        List<Cell> resultat = new ArrayList<Cell>();

        if (super.color){
            if ( i == 6  && Nobody_in_trajectory(t,origen,t[i-2][j])) resultat.add(t[i-2][j]);
            if (i-1 >=0){
               if (Nobody_in_trajectory(t,origen,t[i-1][j])) resultat.add(t[i-1][j]);
               if (j+1 < 8 && Nobody_in_trajectory(t,origen,t[i-1][j+1])) resultat.add(t[i-1][j+1]);
               if (j-1 >= 0 && Nobody_in_trajectory(t,origen,t[i-1][j-1])) resultat.add(t[i-1][j-1]);
            }
        }else{
            if ( i == 1 && Nobody_in_trajectory(t,origen,t[i+2][j])) resultat.add(t[i+2][j]);
            if(i+1 < 8){
                if (Nobody_in_trajectory(t,origen,t[i+1][j])) resultat.add(t[i+1][j]);
                if (j+1 < 8 && Nobody_in_trajectory(t,origen,t[i+1][j+1])) resultat.add(t[i+1][j+1]);
                if (j-1 >= 0 && Nobody_in_trajectory(t,origen,t[i+1][j-1])) resultat.add(t[i+1][j-1]);
            }
        }
        super.Movement = resultat;
    }




}
