package Data.Player;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Table;

import java.util.Scanner;

public class Human extends Player{

    public Human(String name) {
        super.name = name;
    }

    public Human() {

    }

    private int[] getPosition(String s) {
        int[] pos = new int[2];
        pos[0] = pos[1] = -1;
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') ++index; // erase spaces
        if (index < s.length()) {
            pos[1] = s.charAt(index) - 'A';
            ++index;
            while (index < s.length() && s.charAt(index) == ' ') ++index; // erase spaces
            if (index < s.length()) {
                pos[0] = 8 - Character.getNumericValue(s.charAt(index));
                ++index;
                while (index < s.length() && s.charAt(index) == ' ') ++index; // erase spaces
                if (index < s.length()) pos[0] = pos[1] = -1;
            }
        }
        return pos;
    }

    public void move_piece(Table t) {
        Scanner sc = new Scanner(System.in);
        boolean error = true;
        while (error) {
            t.print_table();
            if (super.color) System.out.println("White Team turn");
            else System.out.println("Black Team turn");
            System.out.print("Choose the piece to move: ");
            String s = sc.nextLine();
            int[] origen = getPosition(s);
            error = !correct_piece(t, origen[0], origen[1]);
            if (!error) {
                System.out.println("Choose the destination: ");
                s = sc.nextLine();
                int[] destino = getPosition(s);
                error = !t.CorrectMove(origen[0], origen[1], destino[0], destino[1]);
                if (!error) t.MovePiece(origen[0], origen[1], destino[0], destino[1]);
                else System.out.println("Wrong move, try again!");
            }
            else System.out.println("Wrong piece, try again!");
        }
        t.print_table();
    }

        /*
        boolean error = false;

        error = correct_piece(t, io, jo);
        if (!error) {
            System.out.print("Choose the destination: ");
            s = sc.nextLine();

        }



        int index = 0;
        int io, jo;
        io = jo = -1;
        while (index < move.length() && move.charAt(index) == ' ') ++index;
        if (index >= move.length()) error = true;
        else {
            jo = move.charAt(index) - 'A';
            ++index;
            while (index < move.length() && move.charAt(index) == ' ') ++index;
            if (index >= move.length()) error = true;
            else {
                io = 8 - Character.getNumericValue(move.charAt(index));
                ++index;
                while (index < move.length() && move.charAt(index) == ' ') ++index;
                if (index < move.length()) error = true;
            }
        }
        if (io < 0 || io >= 8  || jo < 0 || jo >= 8) error = true;
        else {
            System.out.print("Chose the destination: ");
            move = sc.nextLine();
            index = 0;
            int id, jd;
            id = jd = -1;
            while (index < move.length() && move.charAt(index) == ' ') ++index;
            if (index >= move.length()) error = true;
            else {
                jd = move.charAt(index) - 'A';
                ++index;
                while (index < move.length() && move.charAt(index) == ' ') ++index;
                if (index >= move.length()) error = true;
                else {
                    id = 8 - Character.getNumericValue(move.charAt(index));
                    ++index;
                    while (index < move.length() && move.charAt(index) == ' ') ++index;
                    if (index < move.length()) error = true;
                }
            }
            if (id < 0 || id >= 8 || jd < 0 || jd >= 8) error = true;
            else error = g.getTable().CorrectMove(io, jo, id, jd);
        }


        boolean error = correct_piece(t, io, id);
    }*/

    /*public boolean movementCorrect(Table t, Piece p, Cell origen, Cell destino) {
        if (super.color != p.getColor()) return false;
        else return p.correct_movement(t.getTable(), origen, destino);
    }*/

    /*public void movePiece(Table t, Piece p, Cell origen, Cell destino){
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

    }*/
}
