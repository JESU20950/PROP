import Data.Cell;
import Data.Game;
import Data.Table;
import Interface.MainInterface;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;



import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String args[]) throws IOException, CloneNotSupportedException{
        System.out.println("hola");
        MainInterface start = new MainInterface();
    }

}
    /*
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        Game t = new Game();
    }
    */
    /*


    String s = "2QR3r/1q2Pb2/1bpBpp2/p6n/KNpkpN2/1P3P2/3PP3/n3r3 w w 2";
    Table t = testConstructor(s);
    Cell[][] t2 = testgettable(t);
    print_table(t2);
        System.out.println(" ");
                boolean b;


        b = t.MovePiece(2,6,4,7);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
        t2 = testgettable(t);
        print_table(t2);


                System.out.println("prueba2");
                System.out.println("");

                b =  isCorrectProblem(s);
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
        */