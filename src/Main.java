import Data.Cell;
import Data.Game;
import Data.Player.*;
import Data.Problem;
import Data.Table;
import Interface.FrameProgram;
import Interface.MainInterface;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;


import java.awt.*;
import java.awt.event.*;

import static Data.Problem.*;
import static java.lang.System.exit;

public class Main {
    private static void error_choice() {
        System.out.println("Error, this option is not available");
        exit(1);
    }

    private static void quit() {
        exit(0);
    }
    private static void load_problem_ranking(List<String> l) throws IOException {
        for(int i = 0; i < l.size(); i++) {
            System.out.println(i + " : " +l.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the problem by typing the number at the start of each one");
        int aux = sc.nextInt();
        while(aux < 0 || aux > l.size()){
            System.out.println("There is no problem there...");
            System.out.println("Select the problem by typing the number at the start of each one");
            aux = sc.nextInt();

        }
        List <String> marks = marks_of_problem(l.get(aux));
        for (int i = 0; i<marks.size();++i){
            System.out.println(marks.get(i));
        }
        exit(0);

    }
    private static void easy_problems_ranking() throws CloneNotSupportedException, IOException {
        List<String> l = Problem.load_problem_fromBD("BD_EASYMODE");
        load_problem_ranking(l);
    }

    private static void hard_problems_ranking() throws CloneNotSupportedException, IOException {
        List<String> l = Problem.load_problem_fromBD("BD_HARDMODE");
        load_problem_ranking(l);
    }
    private static void ranking() throws IOException, CloneNotSupportedException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Easy Problems");
        System.out.println("2. Hard Problems");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) easy_problems_ranking();
        else if (instr == 2) hard_problems_ranking();
        else error_choice();
        exit(0);
    }

    private static void player_vs_player(Game g) throws CloneNotSupportedException {
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
        //System.out.println(g.getTable().checkmate(false) + " " + g.getTable().checkmate(true) + " " + g.getTable().check(true) + " " + g.getTable().check(false));
        while (g.getNumber_of_play() > 0 && !g.getTable().checkmate(false) && !g.getTable().checkmate(true)) {
            boolean error;
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

    private static void handleGame(String s, boolean player_who_start, boolean player_who_has_to_win, int number_of_plays)  throws CloneNotSupportedException, IOException{
        Scanner sc = new Scanner(System.in);
        Game g = new Game();
        g.prepareTablewithParameters(s, player_who_start, player_who_has_to_win, number_of_plays);
        System.out.println("Select your option by writting the number");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player vs CPU(Easy)");
        System.out.println("3. Player vs CPU(Hard)");
        System.out.println("4. CPU1(Easy) vs CPU2(Easy)");
        System.out.println("5. CPU1(Easy) vs CPU2(Hard)");
        System.out.println("6. CPU1(Hard) vs CPU2(Hard)");
        int instr = sc.nextInt();
        if (instr == 1) player_vs_player(g);
        else if (instr == 2) player_vs_CPUe(g);
        else if (instr == 3) player_vs_CPUh(g);
        else if (instr == 4) CPUe_vs_CPUe(g);
        else if (instr == 5) CPUe_vs_CPUh(g);
        else if (instr == 6) CPUh_Vs_CPUh(g);
        else error_choice();
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
        //b = isCorrectProblem(s, player_who_start, player_who_has_to_win, number_of_plays);
        //System.out.println(b);
        if (!b) {
            System.out.println("The problems has no solution in " + number_of_plays + " movements!");
            start_game();
            return;
        }
    handleGame(s, player_who_start, player_who_has_to_win, number_of_plays);
}

    private static void easy_problems() throws CloneNotSupportedException, IOException {
        List<String> l = Problem.load_problem_fromBD("BD_EASYMODE");
        load_problem(l);
    }

    private static void hard_problems() throws CloneNotSupportedException, IOException {
        List<String> l = Problem.load_problem_fromBD("BD_HARDMODE");
        load_problem(l);
    }

    private static void load_problem(List<String> l) throws CloneNotSupportedException, IOException {
        for(int i = 0; i < l.size(); i++) {
            System.out.println(i + " : " +l.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the problem by typing the number at the start of each one");
        int aux = sc.nextInt();
        while(aux < 0 || aux > l.size()){
            System.out.println("There is no problem there...");
            System.out.println("Select the problem by typing the number at the start of each one");
            aux = sc.nextInt();

        }
        String s = l.get(aux);
        String FEN;
        boolean whoStarts;
        boolean whoWins;
        int N;
        FEN = Problem.ConvertInputtoFEN(s);
        whoStarts = Problem.ConvertInputtoplayer_who_start(s);
        whoWins = Problem.ConvertInputtoplayer_who_has_to_win(s);
        N = Problem.ConvertInputtonumber_of_play(s);
        handleGame(FEN,whoStarts,whoWins,N);
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
