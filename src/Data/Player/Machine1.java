package Data.Player;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Table;

import java.util.ArrayList;
import java.util.List;


public class Machine1 extends Player{
    public Machine1(){
        super.name = "Machine1";
    }

    public List<List<Cell>> todosMovimientosPosibles(Table t){
        List<Piece> misPiezas = t.getPieces(super.isColor());
        List<List<Cell> > aux = new ArrayList<> ();
        for(int i = 0; i < misPiezas.size(); ++i){
            List<Cell> aux2;
            aux2 = misPiezas.get(i).getMovement();
            aux2.add(0,misPiezas.get(i).getPosition());
            aux.add(aux2);
        }
        return aux;
    }
    public boolean isMachine(){
        return true;
    }
    public int minimax(int depth){
        int val = 0;
        if(super.isColor())
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
    private int evaluate(Cell c){
        if(c.getPiece().getName() == "Q" || c.getPiece().getName() == "q"){
            return 9;
        }
        else if(c.getPiece().getName() == "R" || c.getPiece().getName() == "r"){
            return 5;
        }
        else if(c.getPiece().getName() == "B" || c.getPiece().getName() == "b"){
            return 3;
        }
        else if(c.getPiece().getName() == "N" || c.getPiece().getName() == "n"){
            return 3;
        }
        else if(c.getPiece().getName() == "P" || c.getPiece().getName() == "p"){
            return 1;
        }
        else if(c.getPiece().getName() == "K" || c.getPiece().getName() == "k"){
            return 900;
        }
        return 0;
    }
}
