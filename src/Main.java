
/*
import Domain.Game;
import Domain.Player.Human;
import Domain.Player.Machine1;
import Domain.Player.Player;
import Domain.Problem;

import java.io.IOException;

import java.util.Scanner;
import java.util.List;


import static Domain.Problem.*;
import static Domain.Problem.delete_problem_fromBD;
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
    private static void feature_not_available(){
        System.out.println("Feature not available");
        System.exit(0);
    }
    private static void easy_problems_ranking() throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_EASYMODE");
        load_problem_ranking(l);
    }

    private static void hard_problems_ranking() throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_HARDMODE");
        load_problem_ranking(l);
    }
    private static void ranking() throws IOException {
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

    private static void handleEndGame(Game g) {
        g.getTable().print_table();
        if(g.getNumber_of_play() <= 0) System.out.println("Number of plays ended, GAME OVER.");
        if(g.getTable().checkmate(true,true) && g.getTable().checkmate(true,false) ) System.out.println("Check mate, White team won");
        if(g.getTable().checkmate(false,true) && g.getTable().checkmate(false, false)) System.out.println("White didn't reach check mate.");
        System.out.println("GG WP");
    }

    private static void player_vs_player(Game g) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your option by writting the number");
        System.out.print("Enter Player1's name(will play white team): ");
        String name1 = sc.nextLine();
        System.out.print("Enter Player2's name(will play black team): ");
        String name2 = sc.nextLine();
        Player p1 = new Human(name1);
        Player p2 = new Human(name2);
        p1.setColor(true);
        p2.setColor(false);
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        String move;
            while (g.getNumber_of_play() > 0 && !g.getTable().checkmate(true,false) && !g.getTable().checkmate(false,true)) {
            boolean error;
            if (g.getPlayer_who_plays()) {
                g.getPlayer1().move_piece(g);
                error = false;
            }
            else {
                g.getPlayer2().move_piece(g);
                error = false;
            }
            if (!error) {
                g.setPlayer_who_plays(!g.getPlayer_who_plays());
                g.setNumber_of_play(g.getNumber_of_play() - 1);
            }
            else System.out.println("Wrong move, try again!");
            }
            handleEndGame(g);
    }

    private static void player_vs_CPUe(Game g) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your option by writting the number");
        System.out.print("Enter Player's name(will play white team): ");
        String name1 = sc.nextLine();
        Player p1 = new Human(name1);
        Player p2 = new Machine1();
        p1.setColor(true);
        p2.setColor(false);
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        while (g.getNumber_of_play() > 0 && !g.getTable().checkmate(true,false) && !g.getTable().checkmate(false,true)){
            boolean error;
            if (g.getPlayer_who_plays()) {
                g.getPlayer1().move_piece(g);
                error = false;
            }
            else {
                g.getTable().print_table();
                System.out.println("Black Team turn");
                g.getPlayer2().move_piece(g);
                error = false;
            }
            if (!error) {
                g.setPlayer_who_plays(!g.getPlayer_who_plays());
                g.setNumber_of_play(g.getNumber_of_play() - 1);
            }
            else System.out.println("Wrong move, try again!");
        }
        handleEndGame(g);
    }

    private static void CPUe_vs_player(Game g) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your option by writting the number");
        System.out.print("Enter Player's name(will play black team): ");
        String name1 = sc.nextLine();
        Player p1 = new Machine1();
        Player p2 = new Human(name1);
        p1.setColor(true);
        p2.setColor(false);
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        while (g.getNumber_of_play() > 0 && !g.getTable().checkmate(true,false) && !g.getTable().checkmate(false,true)){
            boolean error;
            if (g.getPlayer_who_plays()) {
                g.getTable().print_table();
                System.out.println("White Team turn");
                g.getPlayer1().move_piece(g);
                error = false;
            }
            else {
                g.getPlayer2().move_piece(g);
                error = false;
            }
            if (!error) {
                g.setPlayer_who_plays(!g.getPlayer_who_plays());
                g.setNumber_of_play(g.getNumber_of_play() - 1);
            }
            else System.out.println("Wrong move, try again!");
        }
        handleEndGame(g);
    }

    private static void player_vs_CPUh(Game g) {
        feature_not_available();
    }

    private static void CPUe_vs_CPUe(Game g) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your option by writting the number");
        Player p1 = new Machine1();
        Player p2 = new Machine1();
        p1.setColor(true);
        p2.setColor(false);
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        while (g.getNumber_of_play() > 0 && !g.getTable().checkmate(true,false) && !g.getTable().checkmate(false,true)){
            boolean error;
            if (g.getPlayer_who_plays()) {
                g.getTable().print_table();
                System.out.println("White Team turn");
                g.getPlayer1().move_piece(g);
                error = false;
            }
            else {
                g.getTable().print_table();
                System.out.println("Black Team turn");
                g.getPlayer2().move_piece(g);
                error = false;
            }
            if (!error) {
                g.setPlayer_who_plays(!g.getPlayer_who_plays());
                g.setNumber_of_play(g.getNumber_of_play() - 1);
            }
            else System.out.println("Wrong move, try again!");
        }
        handleEndGame(g);
    }

    private static void CPUe_vs_CPUh(Game g) {
        feature_not_available();
    }

    private static void CPUh_Vs_CPUh(Game g) {
        feature_not_available();
    }

    private static void handleGame(String s, boolean player_who_start, boolean player_who_has_to_win, int number_of_plays) {
        Scanner sc = new Scanner(System.in);
        Game g = new Game();
        g.prepareTablewithParameters(s, player_who_start, player_who_has_to_win, number_of_plays);
        System.out.println("Select your option by writting the number");
        System.out.println("Disclaimer: options that require CPU Hard mode are not yet available.");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player vs CPU(Easy)");
        System.out.println("3. CPU(Easy) vs Player");
        System.out.println("4. Player vs CPU(Hard)");
        System.out.println("5. CPU1(Easy) vs CPU2(Easy)");
        System.out.println("6. CPU1(Easy) vs CPU2(Hard)");
        System.out.println("7. CPU1(Hard) vs CPU2(Hard)");
        int instr = sc.nextInt();
        if (instr == 1) player_vs_player(g);
        else if (instr == 2) player_vs_CPUe(g);
        else if (instr == 3) CPUe_vs_player(g);
        else if (instr == 4) player_vs_CPUh(g);
        else if (instr == 5) CPUe_vs_CPUe(g);
        else if (instr == 6) CPUe_vs_CPUh(g);
        else if (instr == 7) CPUh_Vs_CPUh(g);
        else error_choice();
    }

    private static void introduceFEN() throws IOException {
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
        b = isCorrectProblem(s, player_who_start, player_who_has_to_win, number_of_plays*2);
        System.out.println(b);
        if (!b) {
            System.out.println("The problems has no solution in " + number_of_plays + " movements!");
            start_game();
            return;
        }
    handleGame(s, player_who_start, player_who_has_to_win, number_of_plays);
    }

    private static void easy_problems() throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_EASYMODE");
        load_problem(l);
    }

    private static void hard_problems() throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_HARDMODE");
        load_problem(l);
    }

    private static void load_problem(List<String> l) {
        for(int i = 0; i < l.size(); i++) {
            System.out.println(i + " : " +l.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the problem by typing the number at the start of each one");
        int aux = sc.nextInt();
        while(aux < 0 || aux >= l.size()){
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

    private static void selectFEN() throws IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Easy Problems");
        System.out.println("2. Hard Problems");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) easy_problems();
        else if (instr == 2) hard_problems();
        else error_choice();
    }

    private static void start_game() throws IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Introduce your own FEN");
        System.out.println("2. Select a FEN from the Database");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) introduceFEN();
        else if (instr == 2) selectFEN();
        else error_choice();
    }

    private static void eliminarFEN() throws IOException {
        List<String> problems = load_problem_fromBD("BD_USERPROBLEMS");
        for(int i = 0; i < problems.size(); i++) {
            System.out.println(i + " : " +problems.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the problem by typing the number at the start of each one");
        int aux = sc.nextInt();
        while(aux < 0 || aux > problems.size()){
            System.out.println("There is no problem there...");
            System.out.println("Select the problem by typing the number at the start of each one");
            aux = sc.nextInt();

        }
        String problem = problems.get(aux);
        delete_problem_fromBD(problem);
    }
    private static void modificateFEN() throws IOException {
        List<String> problems = load_problem_fromBD("BD_USERPROBLEMS");
        for(int i = 0; i < problems.size(); i++) {
            System.out.println(i + " : " +problems.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the problem by typing the number at the start of each one");
        int aux = sc.nextInt();
        while(aux < 0 || aux > problems.size()){
            System.out.println("There is no problem there...");
            System.out.println("Select the problem by typing the number at the start of each one");
            aux = sc.nextInt();

        }
        String old_problem = problems.get(aux);
        System.out.println("Introduce FEN");
        sc = new Scanner(System.in);
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
            modificateFEN();
        }
        modificate_problem_fromBD(old_problem,convertParameterstoFEN(s,player_who_start,player_who_has_to_win,number_of_plays));
    }
    private static void modificar_problema() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Introduce FEN");
        System.out.println("2. Eliminate FEN");
        System.out.println("3. Modificate FEN");
        System.out.println("4. Quit");
        int instr = sc.nextInt();
        if (instr == 1) introduceFEN();
        else if (instr == 2) eliminarFEN();
        else if (instr == 3) modificateFEN();
        else if (instr == 4) quit();
        else error_choice();
    }

    private static void start_menu() throws IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Start a new game");
        System.out.println("2. See the ranking table");
        System.out.println("3. Modificar Problemas de usuario");
        System.out.println("4. Quit");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) start_game();
        else if (instr == 2) ranking();
        else if (instr == 3) modificar_problema();
        else if (instr == 4) quit();
        else error_choice();
    }

    public static void main(String[] args) throws IOException {
        start_menu();
    }



}*/


import Interface.FrameProgram;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        FrameProgram a = new FrameProgram();
    }
}