package Data;
import Data.Pieces.*;

import java.lang.*;

public class Table {
    private Cell[][] table;

    Table (String FEN){
        table = new Cell[8][8];
        int w = 0;
        int j = 0;
        while (FEN[w] != ' '){
            int i = 0;
            while(FEN[w] != '/'){
                switch (FEN[w]){
                    case 'k':
                        King k = new Knight(true);
                        Cell[i][j].setpiece(k);
                    case 'q':
                        Queen q = new Queen(true);
                        Cell[i][j].setpiece(q);
                    case 'r':
                        Rook r = new Rook(true);
                        Cell[i][j].setpiece(r);
                    case 'b':
                        Bishop b = new Bishop(true);
                        Cell[i][j].setpiece(b);
                    case 'n':
                         Knight n = new Knight(true);
                        Cell[i][j].setpiece(n);
                    case 'p':
                        Pawn p = new Pawn(true);
                        Cell[i][j].setpiece(p);
                    case 'K':
                        King k = new Knight(false);
                        Cell[i][j].setpiece(K);

                    case 'Q':
                        Queen Q = new Queen(false);
                        Cell[i][j].setpiece(Q);
                    case 'R':
                        Rook R = new Rook(false);
                        Cell[i][j].setpiece(R);
                    case 'B':
                        Bishop B = new Bishop(false);
                        Cell[i][j].setpiece(B);

                    case 'N':
                        Knight N = new Knight(false);
                        Cell[i][j].setpiece(N);
                    case 'P':
                        Pawn P = new Pawn(false);
                        Cell[i][j].setpiece(P);
                    default:





                }
                ++i;
                ++w;
            }
            ++w;
            ++j;
        }
    }


}
