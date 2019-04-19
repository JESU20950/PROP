package Data.driversData.driverPieces;

import Data.Cell;
import Data.Pieces.King;
import Data.Pieces.Piece;
import Data.Table;

import java.util.Scanner;

public class driverKing {
    private static void test_getColor(Piece p) {
        boolean b = p.getColor();
        if (b) System.out.println("White");
        else System.out.println("Black");
    }

    private static void test_getName(Piece p) {
        String s = p.getName();
        System.out.println(s);
    }

    private static void test_correct_movement() {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce your team: ");
        boolean b = sc.nextBoolean();
        Piece p = new King(b);
        test_getColor(p);
        test_getName(p);


        System.out.println("FEN: ");
        sc.nextLine();
        String s = sc.nextLine();
        Table t = new Table(s);
        boolean stop = false;
        /*while (!stop) {
            t.print_table();
            System.out.println("origen: ");
            int io = sc.nextInt();
            int jo = sc.nextInt();
            System.out.println("destino: ");
            int id = sc.nextInt();
            int jd = sc.nextInt();
            Cell[][] c = t.getTable();
            test_correct_movement(c, p, io, jo, id, jd);
            if (io < 0 ) stop = true;
        }
        //test_update_movement(t, p);*/
    }
}
