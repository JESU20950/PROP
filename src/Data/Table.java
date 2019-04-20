package Data;
import Data.Pieces.*;
import Data.Problem;

import java.util.ArrayList;
import java.util.List;

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

    public void print_fen() {
        int space = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() == null) ++space;
                else{
                    if (space != 0) System.out.print(space);
                    System.out.print(table[i][j].getPiece().getName());
                    space = 0;
                }

            }
            if (space != 0) System.out.print(space);
            space = 0;
            if (i != 7) System.out.print("/");
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

    public boolean CorrectMove(int i_origen, int j_origen, int i_destino, int j_destino) {
        if (i_origen >= 8 || j_origen >= 8 || i_destino >= 8 || j_destino >= 8 || i_origen < 0 || j_origen < 0 || i_destino < 0 || j_destino < 0) return false;
        if (table[i_origen][j_origen].getPiece() == null) return false;
        if (!table[i_origen][j_origen].getPiece().correct_movement(table, table[i_origen][j_origen], table[i_destino][j_destino])) return false;
        return true;
    }

    public void MovePiece1(int i_origen, int j_origen, int i_destino, int j_destino) {
        table[i_destino][j_destino].setPiece(table[i_origen][j_origen].getPiece());
        table[i_origen][j_origen].setPiece(null);
        update_all_pieces_movement();
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


    public void update_all_pieces_movement() {
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
                if (this.table[i][j].getPiece() != null && player == this.table[i][j].getPiece().getColor()) {
                    resultat.add(this.table[i][j].getPiece());
                    //System.out.println(i + " " + j);
                    //Problem.print_list_of_pieces(resultat);
                }
            }
        }
        //print_table();

        return resultat;
    }

    public Cell king_position(boolean player) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() != null && player == table[i][j].getPiece().getColor()
                        && (table[i][j].getPiece().getName().equals("k") || table[i][j].getPiece().getName().equals("K")))
                    return table[i][j];
            }
        }
        return null;
    }

    public boolean check(boolean player) throws CloneNotSupportedException {
        int i_king = king_position(!player).getI();
        int j_king = king_position(!player).getJ();
        List<Piece> pieces = getPieces(player);
        //Problem.print_list_of_pieces(pieces);
        //System.out.println(i_king + " " + j_king);
        boolean b = false;
        int i = 0;
        while (!b && i < pieces.size()) {
            List<Cell> movements = pieces.get(i).getMovement();
            //Problem.print_list_of_movements(movements, pieces.get(i).getName());
            int j = 0;
            while (!b && j < movements.size()) {
                int i_piece = movements.get(j).getI();
                int j_piece = movements.get(j).getJ();
                b = b || (i_king == i_piece && j_king == j_piece);
                //System.out.println(i_piece + " " + j_piece + ": " + b);
                ++j;
            }
            ++i;
        }
        return b;
    }

    public boolean checkmate(boolean player) throws CloneNotSupportedException {
        List<Piece> pieces = getPieces(!player);
        //Problem.print_list_of_pieces(pieces);
        boolean b = true;
        int i = 0;
        while (b && i < pieces.size()) {
            int i_origen = pieces.get(i).getPosition().getI();
            int j_origen = pieces.get(i).getPosition().getJ();
            List<Cell> movements = pieces.get(i).getMovement();
            //Problem.print_list_of_movements(movements, pieces.get(i).getName());
            int j = 0;
            while (b && j < movements.size()) {
                int i_destino = movements.get(j).getI();
                int j_destino = movements.get(j).getJ();
                Table aux = new Table();
                aux.setTable(this.table);
                if (aux.CorrectMove(i_origen, j_origen, i_destino, j_destino)) {
                    aux.MovePiece(i_origen, j_origen, i_destino, j_destino);
                    b = b && aux.check(player);
                }
                //System.out.println(i_destino + " " + j_destino + ": " + b);
                //aux.print_table();
                ++j;
            }
            ++i;
        }
        return b;
    }

    /*public boolean checkmate_to(boolean player) throws CloneNotSupportedException {
        List<Piece> l = getPieces(player);
        if (this.king_position(true) == null || this.king_position(false) == null) return false;
        boolean b = false;
        for (int i = 0; i < l.size() && b; ++i) {
            List<Cell> movements = l.get(i).getMovement();
            int origen_i = l.get(i).getPosition().getI();
            int origen_j = l.get(i).getPosition().getJ();
            for (int j = 0; j < movements.size() && b; ++j) {
                int destino_i = movements.get(j).getI();
                int destino_j = movements.get(j).getJ();
                Table T = new Table();
                T.setTable(this.table);
                if (T.MovePiece(origen_i,origen_j,destino_i,destino_j) && T.king_position(player) == null) b = true;
            }
        }
        if (!b) return false;

        l = getPieces(player);
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
    }*/
}


