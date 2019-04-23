package Data.Player;

import Data.Cell;
import Data.Table;
import Data.Pieces.Piece;
import Data.Game;

import java.util.Scanner;

public class Human extends Player {

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

    public void move_piece(Game g) {
        Scanner sc = new Scanner(System.in);
        boolean error = true;
        while (error) {
            g.getTable().print_table();
            if (super.color) System.out.println("White Team turn");
            else System.out.println("Black Team turn");
            System.out.println(g.getNumber_of_play() + " turns missing!");
            System.out.println("Choose the piece to move: ");
            String s = sc.nextLine();
            int[] origen = getPosition(s);
            error = !correct_piece(g, origen[0], origen[1]);
            if (!error) {
                System.out.println("Choose the destination: ");
                s = sc.nextLine();
                int[] destino = getPosition(s);
                error = !g.getTable().CorrectMove(origen[0], origen[1], destino[0], destino[1]);
                if (!error) g.getTable().MovePiece(origen[0], origen[1], destino[0], destino[1]);
                else System.out.println("Wrong move, try again!");
            } else System.out.println("Wrong piece, try again!");
        }
    }
}
