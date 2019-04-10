import Data.Cell;
import Data.Table;

import java.util.Scanner;




public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Table t = testConstructor(s);
        Cell[][] t2 = testgettable(t);
        print_table(t2);
        System.out.println(" ");
        boolean b = t.MovePiece(6,5,5,5);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
        t2 = testgettable(t);
        print_table(t2);

    }

    private static Table testConstructor(String s){
        return new Table(s);
    }
    private static Cell[][] testgettable(Table t){
        return t.gettable();
    }

    private static void print_table(Cell[][] table){
        for (int i = 0; i<8; ++i){
            for  (int j = 0; j<8; ++j){
                if (table[i][j].getPiece() == null)System.out.printf("X");
                else System.out.printf(table[i][j].getPiece().getName());

            }
            System.out.println(" ");
        }
    }
}

