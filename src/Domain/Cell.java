package Domain;


import Domain.Pieces.Piece;

public class Cell {

    private Piece piece;

    private int i;

    private int j;
    public Cell() {
        piece = null;
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

}
