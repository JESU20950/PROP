package Data;
import Data.Pieces.*;


import java.util.*;
import java.lang.*;

public class Table {
    private Cell[][] table;

    public Table (String FEN){
        table = new Cell[8][8];
        int w = 0;
        int j = 0;
        while (FEN.charAt(w) != ' '){
            int i = 0;
            while(FEN.charAt(w) != '/'){
                switch (FEN.charAt(w)){
                    case 'k':
                        King k = new King(true);
                        table[i][j].setpiece(k);
                    case 'q':
                        Queen q = new Queen(true);
                        table[i][j].setpiece(q);
                    case 'r':
                        Rook r = new Rook(true);
                        table[i][j].setpiece(r);
                    case 'b':
                        Bishop b = new Bishop(true);
                        table[i][j].setpiece(b);
                    case 'n':
                         Knight n = new Knight(true);
                        table[i][j].setpiece(n);
                    case 'p':
                        Pawn p = new Pawn(true);
                        table[i][j].setpiece(p);
                    case 'K':
                        King K = new King(false);
                        table[i][j].setpiece(K);

                    case 'Q':
                        Queen Q = new Queen(false);
                        table[i][j].setpiece(Q);
                    case 'R':
                        Rook R = new Rook(false);
                        table[i][j].setpiece(R);
                    case 'B':
                        Bishop B = new Bishop(false);
                        table[i][j].setpiece(B);

                    case 'N':
                        Knight N = new Knight(false);
                        table[i][j].setpiece(N);
                    case 'P':
                        Pawn P = new Pawn(false);
                        table[i][j].setpiece(P);
                    default:
                        int espace = Integer.parseInt(FEN.charAt(w));
                        for (int k = 0; k<espace; ++k) ++i;
                }
                ++i;
                ++w;
            }
            ++w;
            ++j;
        }
    }
}
