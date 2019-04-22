package Data.Player;

import Data.Pieces.Piece;
import Data.Table;
import Data.Cell;
import java.util.List;

public abstract class Player {
    protected boolean color;
    protected String name;
    public Table t;

    public Player() {

    }

    public Table getTable() {
        return t;
    }

    public boolean isMachine() {
        return false;
    }

    protected boolean correct_piece(Table t, int i, int j) {
        Cell[][] c = t.getTable();
        return (i >= 0 && i < 8 && j >= 0 && j < 8) && c[i][j].getPiece() != null && c[i][j].getPiece().getColor() == color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean getColor() {
        return color;
    }

    public abstract void move_piece(Table t);
}
