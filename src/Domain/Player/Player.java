package Domain.Player;


import Domain.Cell;
import Domain.Game;

public abstract class Player {
    protected boolean color;
    protected String name;

    public Player() {

    }

    protected boolean correct_piece(Game g, int i, int j) {
        Cell[][] c = g.getTable().getTable();
        return (i >= 0 && i < 8 && j >= 0 && j < 8) && c[i][j].getPiece() != null && c[i][j].getPiece().getColor() == color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean getColor() {
        return color;
    }

    public abstract void move_piece(Game g);
}
