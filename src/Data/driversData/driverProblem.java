package Data.driversData;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Data.Cell;
import Data.Pieces.Piece;
import Data.Problem;
import Data.Table;

public class driverProblem {

    private static void test_load_problem_fromBD_Easy_Mode()throws IOException {
        List<String> l = Problem.load_problem_fromBD_Easy_Mode();
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    private static void test_load_problem_fromBD_Hard_Mode()throws IOException {
        List<String> l = Problem.load_problem_fromBD_Hard_Mode();
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    private static boolean test_iscorrectFen(String FEN){
        return Problem.iscorrectFen(FEN);
    }

    private static String test_ConvertInputtoFEN(String input){
        return Problem.ConvertInputtoFEN(input);
    }

    private static String test_ConvertInputtoplayer_who_start(String input){
        if(Problem.ConvertInputtoplayer_who_start(input)) return "white";
        else{
            return "black";
        }
    }
    private static String test_ConvertInputtoplayer_who_who_has_to_win(String input){
        if(Problem.ConvertInputtoplayer_who_has_to_win(input)) return "white";
        else{
            return "black";
        }
    }
    private static int test_ConvertInputtonumber_of_play(String input){
        return Problem.ConvertInputtonumber_of_play(input);
    }

    private static boolean test_isCorrectProblem(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) throws CloneNotSupportedException{
        return Problem.isCorrectProblem(FEN,player_who_start,player_who_has_to_win,number_of_play);
    }

    private static void test_print_list_of_pieces(List<Piece> p){
        Problem.print_list_of_pieces(p);
    }

    private static void test_print_list_of_movements(List<Cell> m, String name){
        Problem.print_list_of_movements(m,name);
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
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
    }
}
