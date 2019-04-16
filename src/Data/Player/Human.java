package Data.Player;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Table;

public class Human extends Player{
    public Human(String name){
        super.name = name;
    }

    public boolean movementCorrect(Table t, Piece p, Cell origen, Cell destino) {
        if (super.color != p.getColor()) return false;
        else return p.correct_movement(t.getTable(), origen, destino);
    }

    public void movePiece(Table t, Piece p, Cell origen, Cell destino){
        if(super.color != p.getColor()){
            System.out.println("Not your piece");
        }
        else{
            if(p.correct_movement(t.getTable(),origen, destino)){
                p.setPosition(destino);
                System.out.println("Has movido la pieza " + p.getName() + " a la posicion " + destino.getI() + " " + destino.getJ());
            }
            else{
                System.out.println("Movement is imposible");
            }
        }

    }
}
