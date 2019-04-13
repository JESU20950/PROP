import Data.Cell;
import Data.Table;

import java.util.Scanner;

import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException{
        /*
        String s = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
        */
        String s = "3r4/q3r3/1b6/2n2Rp1/2P2N2/QP2k1P1/4P3/BN2RnKB w - - 0 1";
        Table t = testConstructor(s);
        Cell[][] t2 = testgettable(t);
        print_table(t2);
        System.out.println(" ");
        boolean b;

        /*
        b = t.MovePiece(2,6,4,7);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
        t2 = testgettable(t);
        print_table(t2);
        */

        System.out.println("prueba2");
        System.out.println("");

        b =  isCorrectProblem(s,2,true);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");



    }

    private static Table testConstructor(String s) throws CloneNotSupportedException{
        return new Table(s);
    }
    private static Cell[][] testgettable(Table t){
        return t.getTable();
    }

    public static void print_table(Cell[][] table){
        for (int i = 0; i<8; ++i){
            for  (int j = 0; j<8; ++j){
                if (table[i][j].getPiece() == null)System.out.printf(".");
                else System.out.printf(table[i][j].getPiece().getName());

            }
            System.out.println(" ");
        }
    }

}

