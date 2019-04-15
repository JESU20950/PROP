package Data.driversData.driverPieces;

import Data.Pieces.Queen;
import java.util.Scanner;

public class driverQueen {
    private static void test_getColor(Queen p) {
        boolean b = p.getColor();
        if (b) System.out.println("White");
        else System.out.println("Black");
    }

    private static void test_getName(Queen p) {
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
        Queen p = new Queen(b);
        test_getName(p);
    }
}
