package Data.Player;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Table;
import Data.Game;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;


public class Machine1 extends Player{
    public Machine1() {
        super.name = "Machine1";
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
                            System.out.println("Joang10");
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



    public void move_piece(Game g) {
        int n = g.getNumber_of_play();
        Cell[] c = new Cell[2];
        for (int i  = 0; i<2 ; ++i) c[i] = new Cell();
        System.out.println("HOLA");
        int e = evaluate(g.getTable());
        System.out.println(e);
        //System.exit(0);
        int num = move_piece_machine(g.getTable(), min(n, 2), min(n, 2), g.getPlayer_who_plays(), c);
        System.out.println("HOLA");
        g.getTable().MovePiece(c[0].getI(), c[0].getJ(), c[1].getI(), c[1].getJ());
        g.getTable().print_table();
    }

    /*public List<List<Cell>> todosMovimientosPosibles(Table t){
        List<Piece> misPiezas = t.getPieces(super.getColor());
        List<List<Cell> > aux = new ArrayList<> ();
        for(int i = 0; i < misPiezas.size(); ++i){
            List<Cell> aux2;
            aux2 = misPiezas.get(i).getMovement();
            aux2.add(0,misPiezas.get(i).getPosition());
            aux.add(aux2);
        }
        return aux;
    }

    public int minimax(int depth){
        int val = 0;
        if(super.getColor())
            val = Max(depth); // white moves first
        else
            val = Min(depth); // black moves first
        return val;
    }

    /**
     * The search algorithm for max (White player)
     * @param depth - The current number of levels in the tree to be searched.
     * @return the best evaluation
     */
    /*private int Max(int depth){
        if(depth == 0) return evaluate();
        int best = -900;
        List<List<Cell>> l = todosMovimientosPosibles(getTable());
        if(l.size() != 0){
            int siz = l.size();
            while(siz > 0){
                int siz2 = l.get(siz-1).size();
                Cell c =  l.get(siz-1).remove(siz2-1);
                Piece p = l.get(siz-1).get(0).getPiece();
                p.setPosition(c);
                int val = -Min(depth -1);
                if ( val > best){
                    best = val;

                }
                p.setPosition(l.get(siz-1).get(0));
                if(l.get(siz-1).size() == 0){
                    l.remove(siz-1);
                }
            }
        }
        return best;
    }

    private int Min(int depth){
        if(depth == 0) return 0;
        int best = -900;
        List<List<Cell>> l = todosMovimientosPosibles(getTable());
        if(l.size() != 0){
            int siz = l.size();
            while(siz > 0){
                int siz2 = l.get(siz-1).size();
                Cell c =  l.get(siz-1).remove(siz2-1);
                Piece p = l.get(siz-1).get(0).getPiece();
                p.setPosition(c);
                int val = -Max(depth -1);
                if ( val > best){
                    best = val;

                }
                p.setPosition(l.get(siz-1).get(0));
                if(l.get(siz-1).size() == 0){
                    l.remove(siz-1);
                }
            }
        }
        return best;
    }*/
    /* Lo de abajo es un minmax de interné
    private int Max(int depth) {
        if (depth == 0)
            return estimate();
        int best = -900;
        Vector v = successors(true);
        if (v!=null) {
            int siz = v.size();
            while(v.size()>0) {
                Move mv = (Move)v.remove(0);
                mv.perform(board);
                int val = -Min(depth-1);
                if (val>best) {
                    best = val;
                    if (this.white) {
                        mm = mv; // Current choice of move
                    }
                }
                mv.undo(board);
            }
        }
        return best;
    }

     * The search algorithm for min (Black player)
     * @param depth - The current depth
     * @return the best evaluation

    private int Min(int depth) {
        if (depth == 0)
            return estimate();
        int best = -900;
        Vector v = successors(false);
        if (v!=null) {
            int siz = v.size();
            while(v.size()>0) {
                Move mv = (Move)v.remove(0);
                mv.perform(board);
                int val = -Max(depth-1);
                if (val>best) {
                    best = val;
                    if (!this.white) {
                        mm = mv; // Current choice of move
                    }
                }
                mv.undo(board);
            }
        }
        return best;
    }
    */

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
        return M - E;
    }
}
