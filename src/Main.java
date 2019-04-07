import java.util.Scanner;

import static Data.Problem.iscorrectFen;


public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println(s);
        boolean b = iscorrectFen(s);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
    }

}

