package Domain.driversDomain;


import Domain.Cell;
import Domain.Pieces.*;

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

    private static void test_setI(Cell c, int i){
        c.setI(i);
    }
    private static void test_setJ(Cell c, int j){
        c.setJ(j);
    }
    private static void test_getI(Cell c){
        System.out.println("Row " + c.getI());
    }
    private static void test_getJ(Cell c){
        System.out.println("Column " + c.getJ());
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your piece: pawn, king, queen, knight, bishop or rook.");
        String s = sc.nextLine();
        Piece p;
        System.out.println("Choose your team true for white, false for black.");
        boolean b = sc.nextBoolean();
        if (s.equals("pawn")) {
            p = new Pawn(b);
        }
        else if (s.equals("king")) {
            p = new King(b);
        }
        else if (s.equals("queen")) {
            p = new Queen(b);
        }
        else if (s.equals("knight")) {
            p = new Knight(b);
        }
        else if (s.equals("bishop")) {
            p = new Bishop(b);
        }
        else if (s.equals("rook")) {
            p = new Rook(b);
        }
        else {
            error_choice();
            p = new Pawn(false);
        }
        Cell c = new Cell();
        test_setPiece(c, p);
        test_getPiece(c);
        System.out.println("Set the position of the cell, first rows then columns.");
        int i = sc.nextInt();
        int j = sc.nextInt();
        while((i< 0 || i >7 ) || (j<0 || j > 7)){
            System.out.println("Wrong position, has to be between 0 and 7");
            System.out.println("Set the position of the cell, first rows then columns.");
            i = sc.nextInt();
            j = sc.nextInt();
        }
        test_setI(c,i);
        test_setJ(c,j);
        test_getI(c);
        test_getJ(c);
    }
}
