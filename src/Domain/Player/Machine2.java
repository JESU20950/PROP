package Domain.Player;


import Domain.Cell;
import Domain.Game;
import Domain.Table;

public class Machine2 extends Player{
    public Machine2() {
        super.name = "Machine2";
    }

    public boolean isMachine() {
        return true;
    }

    public Cell[] move_piece(Game g){
        Cell[ ] c = null;
        return c;
    }

}
