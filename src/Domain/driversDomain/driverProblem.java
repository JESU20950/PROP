package Domain.driversDomain;

import Domain.Cell;
import Domain.Pieces.Piece;
import Domain.Problem;
import Domain.Table;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

import static Domain.Problem.*;


public class driverProblem {

    private static void test_load_problem_fromBD_Easy_Mode()throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_EASYMODE");
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    private static void test_load_problem_fromBD_Hard_Mode()throws IOException {
        List<String> l = Problem.load_problem_fromBD("BD_HARDMODE");
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    private static boolean test_iscorrectFen(String FEN){
        return Problem.iscorrectFen(FEN);
    }

    private static String test_ConvertInputtoFEN(String input){
        return ConvertInputtoFEN(input);
    }

    private static String test_ConvertInputtoplayer_who_start(String input){
        if(ConvertInputtoplayer_who_start(input)) return "white";
        else{
            return "black";
        }
    }
    private static String test_ConvertInputtoplayer_who_who_has_to_win(String input){
        if(ConvertInputtoplayer_who_has_to_win(input)) return "white";
        else{
            return "black";
        }
    }
    private static int test_ConvertInputtonumber_of_play(String input){
        return Problem.ConvertInputtonumber_of_play(input);
    }

    private static boolean test_isCorrectProblem(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) {
        return Problem.isCorrectProblem(FEN,player_who_start,player_who_has_to_win,number_of_play);
    }

    private static void test_print_list_of_pieces(List<Piece> p){
        Problem.print_list_of_pieces(p);
    }

    private static void test_print_list_of_movements(List<Cell> m, String name){
        Problem.print_list_of_movements(m,name);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Print of easy problems");
        test_load_problem_fromBD_Easy_Mode();
        System.out.println();
        System.out.println("Print of hard problems");
        test_load_problem_fromBD_Hard_Mode();
        System.out.println();
        System.out.println("Introducir FEN: ");
        String s = sc.nextLine();
        String FEN = test_ConvertInputtoFEN(s);
        while(!test_iscorrectFen(FEN)){
            System.out.println("FEN incorrect");
            System.out.println("Introducir FEN: ");
            s = sc.nextLine();
            FEN = test_ConvertInputtoFEN(s);
        }
        System.out.println("FEN is correct.");
        String whoStarts = test_ConvertInputtoplayer_who_start(s);
        System.out.println("Player starting is: " + whoStarts);
        String whoWins = test_ConvertInputtoplayer_who_who_has_to_win(s);
        System.out.println("Player thas has to win is: " + whoWins);
        int N = test_ConvertInputtonumber_of_play(s);
        System.out.println("Number of plays the problem has: " + N);
        boolean boolwhoStarts;
        boolean boolwhoWins;
        if(whoStarts == "white") boolwhoStarts= true;
        else boolwhoStarts=false;
        if(whoWins == "white") boolwhoWins = true;
        else boolwhoWins = false;
        boolean b = test_isCorrectProblem(FEN,boolwhoStarts,boolwhoWins,N);
        if(b) System.out.println("The problem has a way to victory");
        else{
            System.out.println("The problem has no solution");
        }
        Table t = new Table(FEN);
        System.out.println();
        System.out.println("White pieces: ");
        List<Piece> p1 = t.getPieces(true);
        test_print_list_of_pieces(p1);
        System.out.println("Possible moves of the white pieces: ");
        for(int i = 0; i < p1.size(); i++){
            Piece pi = p1.get(i);
            test_print_list_of_movements(pi.getMovement(),pi.getName());
        }
        System.out.println();
        System.out.println("Black pieces: ");
        List<Piece> p2 = t.getPieces(false);
        test_print_list_of_pieces(p2);
        System.out.println("Possible moves of the black pieces: ");
        for(int i = 0; i < p2.size(); i++){
            Piece pi = p2.get(i);
            test_print_list_of_movements(pi.getMovement(),pi.getName());
        }
        System.out.println();

        test_marks_of_problem();
        test_introduce_user_result();
        testintroduce_problem_toBD();
        testdelete_problem_fromBD();
        testmodificate_problem_fromBD();
    }

    private static void testintroduce_problem_toBD() throws IOException {
        System.out.println("Test introduce problem to BD");
        System.out.println("Introducir FEN");
        Scanner sc = new Scanner(System.in);
        String FEN = sc.nextLine();
        introduce_problem_toBD(ConvertInputtoFEN(FEN),ConvertInputtoplayer_who_start(FEN), ConvertInputtoplayer_who_has_to_win(FEN), ConvertInputtonumber_of_play(FEN));
        System.out.println("Problem introduced successfully!");
    }

    private static void test_marks_of_problem() throws IOException {
        System.out.println("Test marks of problem");
        System.out.println("Introducir FEN");
        Scanner sc = new Scanner(System.in);
        String FEN = sc.nextLine();
        List <String> result = marks_of_problem(FEN);
        for (int i = 0; i<result.size(); ++i){
            System.out.println(result.get(i));
        }
    }
    private static void test_introduce_user_result() throws InterruptedException, IOException {
        System.out.println("Test introduce user result");
        System.out.println("Introducir FEN: ");
        Scanner sc = new Scanner(System.in);
        String FEN = sc.nextLine();
        System.out.println("Introduce a time duration of game");
        sc = new Scanner(System.in);
        int s = sc.nextInt();
        Instant instant_start = Instant.now();
        Thread.sleep(s);
        Instant instant_end_of_game = Instant.now();
        System.out.println("Introduce a username");
        sc = new Scanner(System.in);
        String username = sc.nextLine();
        introduce_user_result(FEN,username, instant_start,instant_end_of_game);
        System.out.println("User result introduced successfully!");
    }
    private static void testdelete_problem_fromBD() throws IOException {
        System.out.println("Test delete problem to BD");
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
        System.out.println("Deleted successfully!");
    }
    private static void  testmodificate_problem_fromBD() throws IOException {
        System.out.println("Test modificate problem to BD");
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
        sc = new Scanner(System.in);
        System.out.println("Introduce the new problem: ");
        String new_porblem = sc.nextLine();
        modificate_problem_fromBD(old_problem,new_porblem);
        System.out.println("Modified successfully!");
    }
}
