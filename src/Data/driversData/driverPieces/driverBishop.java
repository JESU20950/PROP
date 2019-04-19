package Data.driversData.driverPieces;

import Data.Pieces.Bishop;
import Data.Pieces.Piece;
import Data.Cell;
import Data.Problem;
import Data.Table;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class driverBishop {
    private static void test_getColor(Piece p) {
        boolean b = p.getColor();
        if (b) System.out.println("White");
        else System.out.println("Black");
    }

    private static void test_getName(Piece p) {
        String s = p.getName();
        System.out.println(s);
    }

    private static void test_correct_movement(Cell[][] c, Piece p, int io, int jo, int id, int jd) {
        boolean b = io >= 0 && jo >= 0 && id >= 0 && jd >= 0 && io < 8 && jo < 8 && id < 8 && jd < 8;
        if (b) b = p.correct_movement(c, c[io][jo], c[id][jd]);
        System.out.println(b);
    }

    private static void test_update_movement(Table t, Piece p) throws CloneNotSupportedException{
        Cell[][] c = t.getTable();
        Scanner sc = new Scanner(System.in);
        t.print_table();
        System.out.println("Select bishop: ");
        int i = sc.nextInt();
        int j = sc.nextInt();
        Cell co = new Cell();
        co = c[i][j];
        p = co.getPiece();
        List<Cell> movement = p.getMovement();
        Problem.print_list_of_movements(movement, "Bishop");
        i = sc.nextInt();
        j = sc.nextInt();
        Cell cd = new Cell();
        cd = c[i][j];
        cd.setPiece(p);
        c[i][j] = cd;
        p = cd.getPiece();
        p.updateMovement(c, cd);
        t.setTable(c);
        t.print_table();
        movement = p.getMovement();
        Problem.print_list_of_movements(movement, "Bishop");
        List<Piece> pieces = new ArrayList<Piece>();
        pieces.add(p);
        Problem.print_list_of_pieces(pieces);
    }

    public static void main(String[] args) throws CloneNotSupportedException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce your team: ");
        boolean b = sc.nextBoolean();
        Piece p = new Bishop(b);
        test_getColor(p);
        test_getName(p);
        System.out.println("FEN: ");
        sc.nextLine();
        String s = sc.nextLine();
        Table t = new Table(s);
        boolean stop = false;
        while (!stop) {
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
        test_update_movement(t, p);
    }
}
