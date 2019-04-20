package Data.Player;

import Data.Pieces.Piece;
import Data.Table;
import Data.Cell;
import java.util.List;

public abstract class Player {
    protected boolean color;
    protected String name;

    public boolean correct_piece(Table t, int i, int j) {
        Cell[][] c = t.getTable();
        return c[i][j].getPiece().getColor() == color;
    }

    public Player() {

    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean getColor() {
        return color;
    }
}
