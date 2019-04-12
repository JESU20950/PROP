package Data;

import Data.Pieces.*;

public class Cell implements Cloneable{

    private Piece piece;

    private int i;

    private int j;
    public Cell() {
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
