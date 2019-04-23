package Data.Player;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Table;
import Data.Game;

import java.util.ArrayList;
import java.util.List;


public class Machine1 extends Player{
    public Machine1() {
        super.name = "Machine1";
    }

    private Cell[] move_piece_machine(Game g, int n) {
        Table aux = new Table();
        aux.setTable(g.getTable().getTable());
        boolean b = super.color;
        if (n == 0) {

        }


        List<Piece> pieces2 = t.getPieces(player_turn);
        pieces2 = t.getPieces(player_turn);
        //print_list_of_pieces(pieces2);
        if (number_of_play == 0) return t.checkmate(player_who_has_to_win, true) && t.checkmate(player_who_has_to_win, false);
        if (number_of_play > 0) {
            if (t.checkmate(player_who_has_to_win, player_turn)) {
                return true;
            }
            //print_list_of_pieces(pieces2);
            if (t.checkmate(!player_who_has_to_win, player_turn)) return false;
            //print_list_of_pieces(pieces2);
            if (player_who_has_to_win == player_turn){
                List<Piece> pieces = t.getPieces(player_turn);
                //print_list_of_pieces(pieces);
                for (int i = 0; i<pieces.size(); ++i){
                    List<Cell> movement = pieces.get(i).getMovement();
                    int i_origen = pieces.get(i).getPosition().getI();
                    int j_origen = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j<movement.size(); ++j){
                        int i_destino = movement.get(j).getI();
                        int j_destino = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(i_origen, j_origen, i_destino, j_destino)){
                            aux.MovePiece(i_origen, j_origen, i_destino, j_destino);
                            if (achieve_the_goal(aux,!player_turn,number_of_play-1,player_who_has_to_win)) return true;
                        }
                    }
                }
                return false;
            }else{
                List<Piece> pieces = t.getPieces(player_turn);
                for (int i = 0; i<pieces.size(); ++i){
                    List<Cell> movement = pieces.get(i).getMovement();
                    int i_origen = pieces.get(i).getPosition().getI();
                    int j_origen = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j<movement.size(); ++j){
                        int i_destino = movement.get(j).getI();
                        int j_destino = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(i_origen, j_origen, i_destino, j_destino)){
                            aux.MovePiece(i_origen, j_origen, i_destino, j_destino);
                            if (!achieve_the_goal(aux,!player_turn,number_of_play-1,player_who_has_to_win)) return false;
                        }
                    }
                }
                return true;
            }

        }
        return false;
    }

    private int max(int x, int y) {
        if (x < y) return y;
        else return x;
    }

    private int min(int x, int y) {
        if (x < y) return x;
        else return y;
    }

    public void move_piece(Game g) {
        int n = g.getNumber_of_play();
        move_piece_machine(g, min(n, 6));
    }

    public List<List<Cell>> todosMovimientosPosibles(Table t){
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
    private int Max(int depth){
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
    }
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

    private int evaluate(Table t1, Table t2){
        int Mt1 = evaluate_pieces(t1.getPieces(super.color));
        int Et1 = evaluate_pieces(t1.getPieces(!super.color));
        int Mt2 = evaluate_pieces(t2.getPieces(super.color));
        int Et2 = evaluate_pieces(t2.getPieces(!super.color));
        int M = Mt2 - Mt1;
        int E = Et2 - Et1;
        return M - E;
    }
}
