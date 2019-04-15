package Data.driversData.driverPieces;

import Data.Pieces.King;
import java.util.Scanner;

public class driverKing {
    private static void test_getColor(King p) {
        boolean b = p.getColor();
        if (b) System.out.println("White");
        else System.out.println("Black");
    }

    private static void test_getName(King p) {
        String s = p.getName();
        System.out.println(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce your team: ");
        String s = sc.nextLine();
        boolean b;
        //System.out.print(s);
        if (s.equals("white")) b = true;
        else if (s.equals("black")) b = false;
        else {
            b = false;
            System.out.println("Color not valid!");
            System.exit(1);
        }
        King p = new King(b);
        test_getName(p);
    }
}
