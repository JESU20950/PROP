package Data;
import Data.Pieces.*;

import java.util.ArrayList;
import java.util.List;

import static Data.driversData.driverTable.print_table;

public class Table implements Cloneable {
    private Cell table[][];

    public Table() {
    }

    public Table(String FEN) {
        table = new Cell[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                table[i][j] = new Cell();
                table[i][j].setJ(j);
                table[i][j].setI(i);
            }
        }
        int w = 0;
        int i = 0;
        while (w < FEN.length()) {
            int j = 0;
            while (w < FEN.length() && FEN.charAt(w) != '/') {
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

                        for (int t = 0; t < space - 1; ++t) ++j;
                        break;
                }
                ++j;
                ++w;
            }
            ++w;
            ++i;
        }
        update_all_pieces_movement();
    }

    public void print_table() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (j == 0) System.out.print(8 - i + " ");
                Cell c = this.table[i][j];
                String s = "null";
                if (c.getPiece() != null) s = c.getPiece().getName();
                if (s == "null") {
                    System.out.print("- ");
                }
                else {
                    System.out.print(s + " ");
                }
            }
            if (i == 7) {
                System.out.println();
                System.out.println("  A B C D E F G H");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Cell[][] getTable() {
        return table;
    }

    public void setTable(Cell[][] table) throws CloneNotSupportedException {
        this.table = new Cell[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                this.table[i][j] = new Cell();
                this.table[i][j] = (Cell) table[i][j].clone();

            }
        }
    }


    public boolean MovePiece(int i_origen, int j_origen, int i_destino, int j_destino) {
        if (i_origen >= 8 || j_origen >= 8 || j_destino >= 8 || j_destino >= 8 || i_origen <= -1 || j_origen <= -1 || j_destino <= -1 || j_destino <= -1) {
            return false;

        }
        if (table[i_origen][j_origen].getPiece() == null){

            return false;
        }
        if (!table[i_origen][j_origen].getPiece().correct_movement(table, table[i_origen][j_origen], table[i_destino][j_destino])){
            return false;

        }
        //operacio moure en el domini
        table[i_destino][j_destino].setPiece(table[i_origen][j_origen].getPiece());
        table[i_origen][j_origen].setPiece(null);
        update_all_pieces_movement();

        //operacio moure graficamente
        return true;
    }


    void update_all_pieces_movement() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() != null) table[i][j].getPiece().updateMovement(table, table[i][j]);
            }
        }
    }


    public List<Piece> getPieces(boolean player) {
        List<Piece> resultat = new ArrayList<Piece>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() != null && player == table[i][j].getPiece().getColor())
                    resultat.add(table[i][j].getPiece());
            }
        }
        return resultat;
    }

    public Cell king_position(boolean player) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() != null && player == table[i][j].getPiece().getColor()
                        && (table[i][j].getPiece().getName() == "k" || table[i][j].getPiece().getName() == "K"))
                    return table[i][j];
            }
        }
        return null;
    }

    public boolean checkmate_to(boolean player) throws CloneNotSupportedException {
        List<Piece> l = getPieces(player);
        for (int i = 0; i < l.size(); ++i) {
            List<Cell> movements = l.get(i).getMovement();
            int origen_i = l.get(i).getPosition().getI();
            int origen_j = l.get(i).getPosition().getJ();
            for (int j = 0; j < movements.size(); ++j) {
                int destino_i = movements.get(j).getI();
                int destino_j = movements.get(j).getJ();
                Table T = new Table();
                T.setTable(this.table);
                if (T.MovePiece(origen_i,origen_j,destino_i,destino_j) && !T.mate_from(!player)) return false;
            }
        }
        return true;
    }


    public boolean mate_from(boolean player) throws CloneNotSupportedException {
        List<Piece> l = getPieces(player);
        for (int i = 0; i < l.size(); ++i) {
            List<Cell> movements = l.get(i).getMovement();
            int origen_i = l.get(i).getPosition().getI();
            int origen_j = l.get(i).getPosition().getJ();
            for (int j = 0; j < movements.size(); ++j) {
                int destino_i = movements.get(j).getI();
                int destino_j = movements.get(j).getJ();
                Table T = new Table();
                T.setTable(this.table);
                if (T.MovePiece(origen_i,origen_j,destino_i,destino_j) && T.king_position(!player) == null) return true;
            }
        }
        return false;
    }
}


