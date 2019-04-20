package Data.driversData;

import Data.Cell;
import Data.Pieces.*;
import Data.Pieces.Piece;

import java.util.Scanner;

public class driverCell {

    private static void test_setPiece(Cell c, Piece p) {
        c.setPiece(p);
    }

    private static void test_getPiece(Cell c) {
        System.out.println(c.getPiece().getName() + ": " + c.getPiece().getColor());
    }

    private static void error_choice() {
        System.out.println("Error");
        System.exit(1);
    }

    private static void choice_color(boolean b) {
        Scanner sc = new Scanner(System.in);
        b = sc.nextBoolean();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Piece p;
        boolean b = false;
        if (s.equals("pawn")) {
            choice_color(b);
            p = new Pawn(b);
        }
        else if (s.equals("king")) {
            choice_color(b);
            p = new King(b);
        }
        else if (s.equals("queen")) {
            choice_color(b);
            p = new Queen(b);
        }
        else if (s.equals("knight")) {
            choice_color(b);
            p = new Knight(b);
        }
        else if (s.equals("bishop")) {
            choice_color(b);
            p = new Bishop(b);
        }
        else if (s.equals("rook")) {
            choice_color(b);
            p = new Rook(b);
        }
        else {
            error_choice();
            p = new Pawn(false);
        }
        Cell c = new Cell();
        test_setPiece(c, p);
        test_getPiece(c);
    }
}
