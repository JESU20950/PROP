package Data.driversData;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Data.Problem;

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

    }
}
