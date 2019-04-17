package Data.Player;

import Data.Table;

public abstract class Player {
    protected boolean color;
    protected String name;
    protected Table table;

    public Player(){};

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isColor() {
        return color;
    }

    public Table getTable() {
        return table;
    }
    abstract public boolean isMachine();
}
