package Data.driversData.driverPieces;

import Data.Pieces.Bishop;
import java.util.Scanner;

public class driverBishop {

    private static void test_getColor(Bishop p) {
        boolean b = p.getColor();
        if (b) System.out.println("White");
        else System.out.println("Black");
    }

    private static void test_getName(Bishop p) {
        String s = p.getName();
        System.out.println(s);
    }

    private static void test_correct_movement(Bishop p) {

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
        Bishop p = new Bishop(b);
        test_getName(p);
    }
}
