package Domain.Pieces;


import Domain.Cell;

import java.util.List;

public abstract class Piece {
    protected boolean color;// white == true
    protected String name;
    protected List<Cell> Movement;
    protected Cell position;
    public Piece() {}


    abstract public boolean getColor();

    abstract public boolean correct_movement(Cell [][] t, Cell origen, Cell destino);

    abstract public String getName();
    abstract public void updateMovement(Cell[][] t, Cell origen);

    public List<Cell> getMovement() {
        return Movement;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean Nobody_in_trajectory(Cell [][] t, Cell origen, Cell destino){
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i = i_origen;
        int j = j_origen;
        int unit_vector_i = 0;
        int unit_vector_j = 0;
        if (Math.abs(i_destino - i_origen) != 0)unit_vector_i = (i_destino - i_origen)/Math.abs(i_origen-i_destino);
        if (Math.abs(j_destino - j_origen) != 0) unit_vector_j = (j_destino - j_origen)/Math.abs(j_destino-j_origen);
        i += unit_vector_i;
        j += unit_vector_j;
        while (i != i_destino || j != j_destino){
            if (t[i][j].getPiece() != null){
                return false;
            }
            i += unit_vector_i;
            j += unit_vector_j;
        }
        return true;
    }

}