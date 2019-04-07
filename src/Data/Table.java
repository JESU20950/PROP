package Data;
import Data.Pieces.*;



import java.lang.*;
import java.util.Arrays;

public class Table {
    private Cell table[][];

    public Table(String FEN) {
        table = new Cell[8][8];
        for (int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
             Cell aux = new Cell();
             table[i][j] = new Cell();
             table[i][j].setJ(j);
             table[i][j].setI(i);
            }
        }
        int w = 0;
        int i = 0;
        while (w < FEN.length()) {
            int j = 0;
            while (w < FEN.length() && FEN.charAt(w) != '/' ) {
                switch (FEN.charAt(w)) {

                    case 'k':
                        King k = new King(false);
                        table[i][j].setPiece(k);
                        break;
                    case 'q':
                        Queen q = new Queen(false);
                        table[i][j].setPiece(q);
                        break;
                    case 'r':
                        Rook r = new Rook(false);
                        table[i][j].setPiece(r);
                        break;
                    case 'b':
                        Bishop b = new Bishop(false);
                        table[i][j].setPiece(b);
                        break;
                    case 'n':
                        Knight n = new Knight(false);
                        table[i][j].setPiece(n);
                        break;
                    case 'p':
                        Pawn p = new Pawn(false);
                        table[i][j].setPiece(p);
                        break;
                    case 'K':
                        King K = new King(true);
                        table[i][j].setPiece(K);
                        break;
                    case 'Q':
                        Queen Q = new Queen(true);
                        table[i][j].setPiece(Q);
                        break;
                    case 'R':
                        Rook R = new Rook(true);
                        table[i][j].setPiece(R);
                        break;
                    case 'B':
                        Bishop B = new Bishop(true);
                        table[i][j].setPiece(B);
                        break;
                    case 'N':
                        Knight N = new Knight(true);
                        table[i][j].setPiece(N);
                        break;
                    case 'P':
                        Pawn P = new Pawn(true);
                        table[i][j].setPiece(P);
                        break;
                    default:
                        int space = Character.getNumericValue(FEN.charAt(w));

                        for (int t = 0; t < space-1; ++t) ++j;
                        break;
                }
                ++j;
                ++w;
            }
            ++w;
            ++i;
        }
    }


    public Cell[][] gettable() {
        return table;
    }


    public boolean MovePiece(int i_origen, int j_origen, int i_destino, int j_destino) {
        if (i_origen >= 8 || j_origen >= 8 || j_destino >= 8 || j_destino >= 8 || i_origen <= -1 || j_origen <= -1 || j_destino <= -1 || j_destino <= -1) return false;
        if (table[i_origen][j_origen].getPiece() == null) return false;
        if (!table[i_origen][j_origen].getPiece().correct_movement(table[i_origen][j_origen],table[i_destino][j_destino])) return false;
        String pieza = table[i_origen][j_origen].getPiece().getName();
        if (pieza != "n" && pieza !=  "N"){
            int i = i_origen;
            int j = j_origen;
            int unit_vector_i = 0;
            int unit_vector_j = 0;
            if (Math.abs(i_destino - i_origen) != 0)unit_vector_i = (i_destino - i_origen)/Math.abs(i_origen-i_destino);
            if (Math.abs(j_destino - j_origen) != 0) unit_vector_j = (j_destino - j_origen)/Math.abs(j_destino-j_origen);
            i += unit_vector_i;
            j += unit_vector_j;
            while (i != i_destino || j != j_destino){
             if (table[i][j].getPiece() != null) return false;
                i += unit_vector_i;
                j += unit_vector_j;
            }
        }
        //operacio moure
        table[i_destino][j_destino].setPiece(table[i_origen][j_origen].getPiece());
        table[i_origen][j_origen].setPiece(null);
        return true;
    }

    //only you can use it for backtracking
    public void undoMovePiece(int i_origen,int j_origen, int i_destino, int j_destino){
        table[i_destino][j_destino].setPiece(table[i_origen][j_origen].getPiece());
        table[i_origen][j_origen].setPiece(null);
    }




    private Piece getPiece(String piece){
        for (int i = 0; i<8; ++i){
            for (int j = 0; j<8; ++j){
                if (table[i][j].getPiece() != null && piece == table[i][j].getPiece().getName()) return table[i][j].getPiece();
            }
        }
        return null;
    }

    public Cell getnextpieceofcolor(boolean player, int ii, int jj){
        for (int i = ii; i<8; ++i){
            for (int j = jj ; j< 8; ++j){
                if (table[i][j].getPiece() != null && table[i][j].getPiece().getColor() == player) return table[i][j];
            }
        }
        return null;
    }
    public Piece getKing(boolean player){
        if (player) return getPiece("K");
        else return getPiece("k");
    }




}




