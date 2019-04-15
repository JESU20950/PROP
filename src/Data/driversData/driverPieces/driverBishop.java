package Data.driversData.driverPieces;

import Data.Pieces.Bishop;
import Data.Cell;
import Data.Table;
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

    /*private static void test_correct_movement(Bishop p) {
        Scanner sc = new Scanner(System.in);
        int posio = sc.nextInt();
        int posjo = sc.nextInt();
        Cell cO = new Cell();
        cO.setI(posio);
        cO.setJ(posjo);
        int posid = sc.nextInt();
        int posjd = sc.nextInt();
        Cell cD = new Cell();
        cD.setI(posid);
        cD.setJ(posjd);
        String s = sc.nextLine();
        Table t = new Table(s);
        System.out.println(p.correct_movement(t, cO, cD));
    }*/

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
        test_getColor(p);
        test_getName(p);
    }
}
