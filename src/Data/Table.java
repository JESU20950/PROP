package Data;
import Data.Pieces.*;



import java.lang.*;

public class Table {
    private Cell table[][];

    public Table(String FEN) {
        table = new Cell[8][8];
        for (int i = 0; i<8; ++i){
            for(int j = 0; j<8; ++j){
             table[i][j] =new Cell();
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
                        for (int t = 0; t < space; ++t) ++j;
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


    void MovePiece(int i_origen, int j_origen, int i_destino, int j_destino) {


    }
}




