package Domain.driversDomain.driverPieces;

import Domain.Cell;
import Domain.Pieces.*;
import Domain.Problem;
import Domain.Table;


import java.util.List;
import java.util.Scanner;

public class driverPiece {

    private static void test_Nobody_in_trajectory(Piece p, Cell[][] c, Cell co, Cell cd) {
        boolean b = p.Nobody_in_trajectory(c, co, cd);
        System.out.println(b);
    }

    private static void test_setPosition(Piece p, Cell c) {
        p.setPosition(c);
    }

    private static void test_getPosition(Piece p) {
        Cell aux = new Cell();
        aux = p.getPosition();
        System.out.println(aux.getI() + " " + aux.getJ());
    }

    private static void test_getMovement(Piece p) {
        List<Cell> movements = p.getMovement();
        Problem.print_list_of_movements(movements, p.getName());
    }

    private static void error_choice() {
        System.out.println("Error");
        System.exit(1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your piece: pawn, king, queen, knight, bishop or rook.");
        String s = sc.nextLine();
        Piece p;
        System.out.println("Choose your team true for white, false for black.");
        boolean b = sc.nextBoolean();
        if (s.equals("pawn")) {
            p = new Pawn(b);
        } else if (s.equals("king")) {
            p = new King(b);
        } else if (s.equals("queen")) {
            p = new Queen(b);
        } else if (s.equals("knight")) {
            p = new Knight(b);
        } else if (s.equals("bishop")) {
            p = new Bishop(b);
        } else if (s.equals("rook")) {
            p = new Rook(b);
        } else {
            error_choice();
            p = new Pawn(false);
        }
        sc.nextLine();
        System.out.print("Introduce your FEN: ");
        s = sc.nextLine();
        Table t = new Table(s);
        t.print_table();
        System.out.println("Choose the piece, first rows then columns.");
        int i = sc.nextInt();
        int j = sc.nextInt();
        while ((i < 0 || i > 7) || (j < 0 || j > 7)) {
            System.out.println("Wrong position, has to be between 0 and 7");
            System.out.println("Set the position of the cell, first rows then columns.");
            i = sc.nextInt();
            j = sc.nextInt();
        }
        Cell[][] c = t.getTable();
        b = c[i][j].getPiece() == null;
        while (b || !c[i][j].getPiece().getName().equals(p.getName())) {
            if (b) System.out.println("Wrong position, there's no piece in " + i + " " + j);
            else System.out.println("Wrong piece, there's no " + p.getName() + " in " + i + " " + j);
            System.out.println("Set the position of the cell, first rows then columns.");
            i = sc.nextInt();
            j = sc.nextInt();
        }
        Cell co = c[i][j];
        test_setPosition(p, co);
        test_getPosition(p);
        p.updateMovement(c, co);
        test_getMovement(p);
        System.out.println("Choose destination, first rows then columns.");
        i = sc.nextInt();
        j = sc.nextInt();
        while ((i < 0 || i > 7) || (j < 0 || j > 7)) {
            System.out.println("Wrong position, has to be between 0 and 7");
            System.out.println("Choose destination, first rows then columns.");
            i = sc.nextInt();
            j = sc.nextInt();
        }
        c = t.getTable();
        Cell cd = c[i][j];
        test_Nobody_in_trajectory(p, c, co, cd);
    }
}
