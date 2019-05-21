package Domain.Player;



import Domain.Cell;
import Domain.Game;
import Domain.Pieces.Piece;
import Domain.Problem;
import Domain.Table;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;


public class Machine1 extends Player{
    public Machine1() {
        super.name = "Machine1";
    }

    public boolean isMachine() {
        return true;
    }

    private void update_good_movements(Table t, List<Cell> movement, Cell origen,boolean turn) {

        for (int i = 0; i < movement.size(); ++i) {

            if (movement.get(i).getPiece() != null && movement.get(i).getPiece().getColor() == origen.getPiece().getColor()) {
                movement.remove(i);
            }

        }
    }

    private int move_piece_machine(Table t, int depth, int n, boolean turn, Cell[] result) {
        if (depth == 0) {
            return evaluate(t);
        }
        else {
            if (turn == super.color) {
                List<Piece> pieces = t.getPieces(turn);
                int best = -9999;
                for (int i = 0; i < pieces.size(); ++i) {
                    List<Cell> movement = pieces.get(i).getMovement();
                    update_good_movements(t,movement, pieces.get(i).getPosition(),turn);
                    int io = pieces.get(i).getPosition().getI();
                    int jo = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j < movement.size(); ++j) {
                        int id = movement.get(j).getI();
                        int jd = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(io, jo, id, jd)) {
                            aux.MovePiece(io, jo, id, jd);
                        }
                        int value = move_piece_machine(aux, depth-1, n, !turn, result);
                        if (n == depth && best < value) {
                            result[0].setI(io);
                            result[0].setJ(jo);
                            result[1].setI(id);
                            result[1].setJ(jd);
                        }
                        best = max(value, best);
                    }
                }
                return best;
            }
            else {
                List<Piece> pieces = t.getPieces(turn);
                int best = 9999;
                for (int i = 0; i < pieces.size(); ++i) {
                    List<Cell> movement = pieces.get(i).getMovement();
                    update_good_movements(t,movement, pieces.get(i).getPosition(),turn);
                    int io = pieces.get(i).getPosition().getI();
                    int jo = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j < movement.size(); ++j) {
                        int id = movement.get(j).getI();
                        int jd = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(io, jo, id, jd)) {
                            aux.MovePiece(io, jo, id, jd);
                        }
                        int value = move_piece_machine(aux, depth-1, n, !turn, result);
                        best = min(value, best);
                    }
                }
                return best;
            }
        }
    }



    public Cell[] move_piece(Game g) {
        int n = g.getNumber_of_play();
        Cell[] c = new Cell[2];
        for (int i  = 0; i<2 ; ++i) c[i] = new Cell();
        //System.exit(0);
        int num = move_piece_machine(g.getTable(), min(n, 2), min(n, 2), g.getPlayer_who_plays(), c);
        g.getTable().MovePiece(c[0].getI(), c[0].getJ(), c[1].getI(), c[1].getJ());
        return c;
    }

    private int piece_value(String s) {
        if (s.equals("k") || s.equals("K")) return 900;
        else if (s.equals("q") || s.equals("Q")) return 9;
        else if (s.equals("r") || s.equals("R")) return 5;
        else if (s.equals("b") || s.equals("B")) return 3;
        else if (s.equals("n") || s.equals("N")) return 3;
        else if (s.equals("p") || s.equals("P")) return 1;
        return 0;
    }

    private int evaluate_pieces(List<Piece> pieces) {
        int value = 0;
        for (int i = 0; i < pieces.size(); ++i) {
            value += piece_value(pieces.get(i).getName());
        }
        return value;
    }

    private int evaluate(Table t){
        int M = evaluate_pieces(t.getPieces(super.color));
        int E = evaluate_pieces(t.getPieces(!super.color));
        //if (t.checkmate(super.color, true) || t.checkmate(super.color, false)) return 1000;
        //if (t.checkmate(!super.color, true) || t.checkmate(!super.color, false)) return -1000;
        return M - E;
    }
}
