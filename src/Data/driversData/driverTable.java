package Data.driversData;

import java.util.List;
import java.util.Scanner;

import Data.*;
import Data.Pieces.Piece;

public class driverTable {
        public static void s(String[] args) throws CloneNotSupportedException{
            Scanner input = new Scanner(System.in);
            String s = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B";
            Table t = testConstructor(s);
            Cell[][] t2 = testgettable(t);
            print_table(t2);
            System.out.println(" ");
            boolean b = t.MovePiece(6,5,3,2);
            if (b) System.out.println("bieeenn");
            else System.out.println("ostia neeng");
            t2 = testgettable(t);
            print_table(t2);

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
