package Data.driversData;

import java.util.Scanner;

public class driverProblem {



    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Print of easy problems");
        test_load_problem_fromBD_Easy_Mode();
        System.out.println("Print of hard problems");
        test_load_problem_fromBD_Hard_Mode();
    }
}
