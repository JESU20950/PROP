import java.util.Scanner;

import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;


public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
        System.out.println(s);
        boolean b =  isCorrectProblem(s,2,true);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");

    }

}

