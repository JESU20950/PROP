package Data.Pieces;

import Data.Cell;

public class Pawn extends Piece {
    public Pawn(boolean color){
        super.color = color;
    }



    public boolean getColor() {
        return super.color;
    }


    public boolean correct_movement(Cell origen, Cell destino) {
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getJ();
        int j_origen = origen.getJ();


        if (super.color){
          if (i_origen==6){

          }
          else{
              if (i_origen -i_destino == 1 && origen.getPiece() == null)


          }

        }



    }
}
