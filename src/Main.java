import Data.Cell;
import Data.Game;
import Data.Player.*;
import Data.Table;
import Interface.FrameProgram;
import Interface.MainInterface;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;



import java.awt.*;
import java.awt.event.*;

public class Main {
    /*public static void main(String[] args) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        //String s = "5BR1/6P1/5KBk/8/8/8/8/8";
        //http://ajedrezutea.com/blancas-dan-mate-en-3-jugadas
        String s = "3K4/8/8/p2k4/pp1B4/N5N1/P2Q4/8";
        //String s = "8/8/2K5/p7/pp1B4/N5N1/P2Q4/8";
        Table t = testConstructor(s);
        Cell[][] t2 = testgettable(t);
        t.print_table();
        System.out.println(" ");
        boolean b;
        //b = t.checkmate_to(false);
        b = isCorrectProblem(s, true, true, 3);
        System.out.println(b);
        t2 = testgettable(t);
        t.print_table();

    }

    private static Table testConstructor(String s) throws CloneNotSupportedException {
        return new Table(s);
    }

    private static Cell[][] testgettable(Table t) {
        return t.getTable();
    }

    public static void print_table(Cell[][] table) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (table[i][j].getPiece() == null) System.out.printf(".");
                else System.out.printf(table[i][j].getPiece().getName());

            }
            System.out.println(" ");
        }
    }
}


    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        MainInterface t = new MainInterface();
    }


    public static void main(String[] args) throws CloneNotSupportedException{
        Scanner input = new Scanner(System.in);
        //String s = "5BR1/6P1/5KBk/8/8/8/8/8";
        //http://ajedrezutea.com/blancas-dan-mate-en-3-jugadas
        String s = "3K4/8/8/p2k4/pp1B4/N5N1/P2Q4/8";
        Table t = testConstructor(s);
        Cell[][] t2 = testgettable(t);
        t.print_table();
        System.out.println(" ");
        boolean b = isCorrectProblem(s, true, true, 6);
        //1.Cxf6+ Dxf6 2.Df8++
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
        t2 = testgettable(t);
        t.print_table();

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
}*/
    private static void error_choice() throws CloneNotSupportedException, IOException {
        System.out.println("Error, this option is not available");
        System.exit(1);
    }

    private static void quit() throws CloneNotSupportedException, IOException {
        System.exit(0);
    }

    private static void ranking() throws CloneNotSupportedException, IOException {
        System.out.println("Feature not available yet");
        System.exit(0);
    }

    private static void player_vs_player(Game g) throws CloneNotSupportedException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your option by writting the number");
        System.out.print("Enter Player1's name: ");
        String name1 = sc.nextLine();
        System.out.print("Enter Player2's name: ");
        String name2 = sc.nextLine();
        System.out.println("1. " + name1 + " plays the White Team and " + name2 + " plays the Black Team");
        System.out.println("2. " + name1 + " plays the Black Team and " + name2 + " plays the White Team");
        Player p1 = new Human(name1);
        Player p2 = new Human(name2);
        int instr = sc.nextInt();
        if (instr == 1) {
            p1.setColor(true);
            p2.setColor(false);
        }
        else if (instr == 2) {
            p1.setColor(false);
            p2.setColor(true);
        }
        else error_choice();
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        sc.nextLine();
        String move;
        while (g.getNumber_of_play() > 0 && !(g.getTable().checkmate(false) || g.getTable().checkmate(true))) {
            boolean error = false;
            if (g.getPlayer_who_plays()) {
                g.getTable().print_table();
                System.out.println("White Team turn");
                System.out.print("Choose the piece to move: ");
                move = sc.nextLine();
                int index = 0;
                int io, jo;
                io = jo = -1;
                while (index < move.length() && move.charAt(index) == ' ') ++index;
                if (index >= move.length()) error = true;
                else {
                    jo = move.charAt(index) - 'A';
                    ++index;
                    while (index < move.length() && move.charAt(index) == ' ') ++index;
                    if (index >= move.length()) error = true;
                    else {
                        io = 8 - Character.getNumericValue(move.charAt(index));
                        ++index;
                        while (index < move.length() && move.charAt(index) == ' ') ++index;
                        if (index < move.length()) error = true;
                    }
                }
                if (io < 0 || io >= 8  || jo < 0 || jo >= 8) error = true;
                else {
                    System.out.print("Chose the destination: ");
                    move = sc.nextLine();
                    index = 0;
                    int id, jd;
                    id = jd = -1;
                    while (index < move.length() && move.charAt(index) == ' ') ++index;
                    if (index >= move.length()) error = true;
                    else {
                        jd = move.charAt(index) - 'A';
                        ++index;
                        while (index < move.length() && move.charAt(index) == ' ') ++index;
                        if (index >= move.length()) error = true;
                        else {
                            id = 8 - Character.getNumericValue(move.charAt(index));
                            ++index;
                            while (index < move.length() && move.charAt(index) == ' ') ++index;
                            if (index < move.length()) error = true;
                        }
                    }
                    if (id < 0 || id >= 8 || jd < 0 || jd >= 8) error = true;
                    else error = g.getTable().CorrectMove(io, jo, id, jd);
                }
            }
            else {
                g.getTable().print_table();
                System.out.println("Black Team turn");
                System.out.print("Choose the piece to move: ");
                move = sc.nextLine();
                int index = 0;
                int io, jo;
                io = jo = -1;
                while (index < move.length() && move.charAt(index) == ' ') ++index;
                if (index >= move.length()) error = true;
                else {
                    io = move.charAt(index) - 'A';
                    ++index;
                    while (index < move.length() && move.charAt(index) == ' ') ++index;
                    if (index >= move.length()) error = true;
                    else {
                        jo = 8 - Character.getNumericValue(move.charAt(index));
                        ++index;
                        while (index < move.length() && move.charAt(index) == ' ') ++index;
                        if (index < move.length()) error = true;
                    }
                }
                if (io < 0 || io >= 8  || jo < 0 || jo >= 8) error = true;
                else {
                    System.out.print("Chose the destination: ");
                    move = sc.nextLine();
                    index = 0;
                    int id, jd;
                    id = jd = -1;
                    while (index < move.length() && move.charAt(index) == ' ') ++index;
                    if (index >= move.length()) error = true;
                    else {
                        jd = move.charAt(index) - 'A';
                        ++index;
                        while (index < move.length() && move.charAt(index) == ' ') ++index;
                        if (index >= move.length()) error = true;
                        else {
                            id = 8 - Character.getNumericValue(move.charAt(index));
                            ++index;
                            while (index < move.length() && move.charAt(index) == ' ') ++index;
                            if (index < move.length()) error = true;
                        }
                    }
                    if (id < 0 || id >= 8 || jd < 0 || jd >= 8) error = true;
                    else error = g.getTable().CorrectMove(io, jo, id, jd);
                    System.out.println(error + " ERROR");
                }
            }
            if (!error) {
                g.setPlayer_who_plays(!g.getPlayer_who_plays());
                g.setNumber_of_play(g.getNumber_of_play() - 1);
            }
            else System.out.println("Wrong move, try again!");
        }
    }

    private static void player_vs_CPUe(Game g) throws CloneNotSupportedException, IOException {

    }

    private static void player_vs_CPUh(Game g) throws CloneNotSupportedException, IOException {

    }

    private static void CPUe_vs_CPUe(Game g) throws CloneNotSupportedException, IOException {

    }

    private static void CPUe_vs_CPUh(Game g) throws CloneNotSupportedException, IOException {

    }

    private static void CPUh_Vs_CPUh(Game g) throws CloneNotSupportedException, IOException {

    }

    private static void introduceFEN() throws CloneNotSupportedException, IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean b = iscorrectFen(s);
        while (!b) {
            System.out.println("FEN is incorrect, introduce the correct FEN: ");
            s = sc.nextLine();
            b = iscorrectFen(s);
        }
        System.out.println("Select your option by writting the number");
        System.out.println("1. White Team starts playing");
        System.out.println("2. Black Team starts playing");
        int instr = sc.nextInt();
        boolean player_who_start, player_who_has_to_win;
        int number_of_plays;
        number_of_plays = 0;
        player_who_has_to_win = player_who_start = false;
        if (instr == 1) player_who_start = true;
        else if (instr == 2) player_who_start = false;
        else error_choice();
        System.out.println("Select your option by writting the number");
        System.out.println("1. White Team wins the game");
        System.out.println("2. Black Team wins the game");
        instr = sc.nextInt();
        if (instr == 1) player_who_has_to_win = true;
        else if (instr == 2) player_who_has_to_win = false;
        else error_choice();
        System.out.print("Introduce the number of plays: ");
        number_of_plays = sc.nextInt();
        b = isCorrectProblem(s, player_who_start, player_who_has_to_win, number_of_plays);
        System.out.println(b);
        /*if (!b) {
            System.out.println("The problems has no solution in " + number_of_plays + " movements!");
            start_game();
            return;
        }*/
        Game g = new Game();
        g.prepareTablewithParameters(s, player_who_start, player_who_has_to_win, number_of_plays);
        System.out.println("Select your option by writting the number");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player vs CPU(Easy)");
        System.out.println("3. Player vs CPU(Hard)");
        System.out.println("4. CPU1(Easy) vs CPU2(Easy)");
        System.out.println("5. CPU1(Easy) vs CPU2(Hard)");
        System.out.println("6. CPU1(Hard) vs CPU2(Hard)");
        instr = sc.nextInt();
        if (instr == 1) player_vs_player(g);
        else if (instr == 2) player_vs_CPUe(g);
        else if (instr == 3) player_vs_CPUh(g);
        else if (instr == 4) CPUe_vs_CPUe(g);
        else if (instr == 5) CPUe_vs_CPUh(g);
        else if (instr == 6) CPUh_Vs_CPUh(g);
        else error_choice();
    }

    private static void easy_problems() throws CloneNotSupportedException, IOException {

    }

    private static void hard_problems() throws CloneNotSupportedException, IOException {

    }

    private static void selectFEN() throws CloneNotSupportedException, IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Easy Problems");
        System.out.println("2. Hard Problems");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) easy_problems();
        else if (instr == 2) hard_problems();
        else error_choice();
    }

    private static void start_game() throws CloneNotSupportedException, IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Introduce your own FEN");
        System.out.println("2. Select a FEN from the Database");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) introduceFEN();
        else if (instr == 2) selectFEN();
        else error_choice();
    }

    private static void start_menu() throws CloneNotSupportedException, IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Start a new game");
        System.out.println("2. See the ranking table");
        System.out.println("3. Quit");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) start_game();
        else if (instr == 2) ranking();
        else if (instr == 3) quit();
        else error_choice();
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        start_menu();
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