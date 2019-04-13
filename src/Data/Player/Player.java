package Data.Player;

public abstract class Player {
    protected boolean color;

    Player(){};
    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
