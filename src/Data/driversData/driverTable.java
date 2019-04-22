package Data.driversData;

import java.util.List;
import java.util.Scanner;

import Data.*;
import Data.Pieces.Piece;

public class driverTable {

        private static Table test_constructor(String s) {
            System.out.println("Testing constructor and print_table ... ");
            return new Table(s);
        }

        private static void test_print_table(Table t) {
            t.print_table();
        }

        private static Cell[][] test_getTable(Table t) {
            System.out.println("Testing getTable ... ");
            t.print_table();
            return t.getTable();
        }

        private static void test_setTable(Table t1, Table t2) throws CloneNotSupportedException{
            Cell[][] c = test_getTable(t2);
            t1.setTable(c);
            System.out.println("Testing setTable ... ");
            t1.print_table();
        }

        private static void test_CorrectMove(Table t, int io, int jo, int id, int jd) {
            System.out.println("Testing CorrectMove ... ");
            System.out.println(t.CorrectMove(io, jo, id, jd));
        }

        private static void test_MovePiece(Table t, int io, int jo, int id, int jd) {
            System.out.println("Testing MovePiece (if CorrectMove) ... ");
            boolean b = t.CorrectMove(io, jo, id, jd);
            if (b) t.MovePiece(io, jo, id, jd);
            t.print_table();
        }

        private static void test_update_all_pieces_movement(Table t) {
            System.out.println("Testing update_all_pieces_movement and getPieces ... ");
            System.out.println("Choose a team: ");
            List<Piece> pieces = test_getPieces(t);
            t.print_table();
            Problem.print_list_of_pieces(pieces);
            System.out.println("Testing update_all_pieces_movement ... ");
            t.update_all_pieces_movement();
            for (int i = 0; i < pieces.size(); ++i) {
                List<Cell> movement = pieces.get(i).getMovement();
                Problem.print_list_of_movements(movement, pieces.get(i).getName());
            }
        }

        private static List<Piece> test_getPieces(Table t) {
            Scanner sc = new Scanner(System.in);
            boolean b = sc.nextBoolean();
            System.out.println("Testing getPieces ... ");
            return t.getPieces(b);
        }

        private static Cell test_king_position(Table t) {
            Scanner sc = new Scanner(System.in);
            boolean b = sc.nextBoolean();
            System.out.println("Testing king_position ... ");
            return t.king_position(b);
        }

        private static void test_check() throws CloneNotSupportedException{
            System.out.println("Introduce a FEN for check:");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Table t = new Table(s);
            System.out.println("Testing check ... ");
            System.out.println("Chose the team who has check: ");
            boolean b = sc.nextBoolean();
            t.print_table();
            System.out.println(t.check(b));
        }

        private static void test_checkmate() throws CloneNotSupportedException{
            System.out.println("Introduce a FEN for checkmate:");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Table t = new Table(s);
            System.out.println("Testing checkmate ... ");
            System.out.println("Choose the team who has checkmate: ");
            boolean b = sc.nextBoolean();
            t.print_table();
        }

        public static void main(String[] args) throws CloneNotSupportedException{
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce FEN: ");
            String s = sc.nextLine();
            Table t1 = test_constructor(s);
            test_print_table(t1);
            System.out.println("Introduce annother FEN: ");
            s = sc.nextLine();
            System.out.println("Testing setTable and getTable ... ");
            Table t2 = new Table(s);
            test_setTable(t1, t2);
            t1.print_table();
            int io, jo, id, jd;
            boolean b = false;
            while (!b) {
                System.out.println("Testing CorrectMove and MovePiece ... ");
                System.out.println("Write -1 -1 -1 -1 to exit the loop");
                System.out.println("Select the origin cell: ");
                io = sc.nextInt();
                jo = sc.nextInt();
                System.out.println("Select the destination cell: ");
                id = sc.nextInt();
                jd = sc.nextInt();
                test_CorrectMove(t1, io, jo, id, jd);
                test_MovePiece(t1, io, jo, id, jd);
                b = io == -1 && jo == -1 && id == -1 && jd == -1;
            }
            test_update_all_pieces_movement(t1);
            Cell ck = new Cell();
            System.out.println("Select king's team: ");
            ck = test_king_position(t1);
            System.out.println(ck.getPiece().getName() + " is on: " + ck.getI() + " " + ck.getJ());
            test_check();
            test_checkmate();
        }
}

