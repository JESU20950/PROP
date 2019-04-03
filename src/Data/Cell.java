package Data;

import Data.Pieces.*;

public class Cell {

    private Piece piece;

    private int i; // Posicion i del tablero

    private int j; // Posicion j del tablero

    public Cell() { // Constructora por defecto
        piece = null;
    }

    public boolean isCellOccupied() { // funcion para consultar si hay alguna pieza en la celda i j
        return piece == null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        piece = p;
        if (p != null) p.setCell(this);
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getJ() {
        return j;
    }

}
