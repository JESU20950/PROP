package Data;



import java.io.*;
import java.util.*;

import Data.Pieces.Piece;



import static Data.driversData.driverTable.print_table;

public class Problem {
    public static boolean iscorrectFen(String FEN) {
        int K, k, Q, q, R, r, B, b, N, n, P, p, index;
        K = k = Q = q = R = r = B = b = N = n = P = p = index = 0;
        while (index < FEN.length() && FEN.charAt(index) == ' ') { //erase the spaces at the front
            ++index;
        }
        int total_rows = 0;
        while (index < FEN.length() && FEN.charAt(index) != ' ') {
            int row = 0;
            while (index < FEN.length() && FEN.charAt(index) != '/' && FEN.charAt(index) != ' ') {
                if (FEN.charAt(index) == 'K') {
                    ++K;
                    ++row;
                }
                else if (FEN.charAt(index) == 'k') {
                    ++k;
                    ++row;
                }
                else if (FEN.charAt(index) == 'Q') {
                    ++Q;
                    ++row;
                }
                else if (FEN.charAt(index) == 'q') {
                    ++q;
                    ++row;
                }
                else if (FEN.charAt(index) == 'R') {
                    ++R;
                    ++row;
                }
                else if (FEN.charAt(index) == 'r') {
                    ++r;
                    ++row;
                }
                else if (FEN.charAt(index) == 'B') {
                    ++B;
                    ++row;
                }
                else if (FEN.charAt(index) == 'b') {
                    ++b;
                    ++row;
                }
                else if (FEN.charAt(index) == 'N') {
                    ++N;
                    ++row;
                }
                else if (FEN.charAt(index) == 'n') {
                    ++n;
                    ++row;
                }
                else if (FEN.charAt(index) == 'P') {
                    ++P;
                    ++row;
                }
                else if (FEN.charAt(index) == 'p') {
                    ++p;
                    ++row;
                }
                else if (Character.getNumericValue(FEN.charAt(index)) > 0 && Character.getNumericValue(FEN.charAt(index)) <= 8) {
                    row += Character.getNumericValue(FEN.charAt(index));
                }
                else return false;
                if (K > 1 || k > 1 || Q > 1 || q > 1 || R > 2 || r > 2 || B > 2 || b > 2 || N > 2 || n > 2 || P > 8 || p > 8) return false;
                ++index;
            }
            ++total_rows;
            if (row != 8) return false;
            if (index < FEN.length() && FEN.charAt(index) == ' ' && total_rows != 8) return false;
            if (index < FEN.length() && FEN.charAt(index) == '/') ++index;
        }
        while (index < FEN.length()) {
            if (FEN.charAt(index) != ' ') return false;
            ++index;
        }
        return (total_rows == 8 && k == 1 && K == 1);
    }


        static public String ConvertInputtoFEN(String input){
        String FEN = "";
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (w < input.length() && input.charAt(w) != ' '){
         FEN = FEN + input.charAt(w);
         ++w;
            }
        return FEN;
        }
        static public boolean ConvertInputtoplayer_who_start(String input){
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        while (input.charAt(w) == ' ') ++w;
        return input.charAt(w) == 'w';
        }
        static public boolean ConvertInputtoplayer_who_has_to_win(String input){
            int w = 0;
            while (input.charAt(w) == ' ') ++w;
            while (input.charAt(w) != ' ') ++w;
            while (input.charAt(w) == ' ') ++w;
            ++w;
            while (input.charAt(w) == ' ') ++w;
            return input.charAt(w) == 'w';
        }
        static public int ConvertInputtonumber_of_play(String s){
            int w = 0;
            String input = String.valueOf(s);
            while (input.charAt(w) == ' ') ++w;
            while (input.charAt(w) != ' ') ++w;
            while (input.charAt(w) == ' ') ++w;
            ++w;
            while (input.charAt(w) == ' ') ++w;
            ++w;
            while (input.charAt(w) == ' ') ++w;
            StringBuilder str = new StringBuilder(s);
            str.delete(0,w);
            input = str.toString();
            return Integer.parseInt(input);
        }

        static public boolean isCorrectProblem(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) throws CloneNotSupportedException{
            FEN = ConvertInputtoFEN(FEN);
            Table t = new Table(FEN);
            return true;
        }
        static public boolean achieve_the_goal(Table t, boolean player_who_start, int number_of_play, boolean player_who_has_to_win) throws CloneNotSupportedException{
            if (number_of_play == 1 &&  t.checkmate_to(!player_who_has_to_win)) return true;
            else if (number_of_play == 1) return false;
            List <Piece> l = t.getPieces(player_who_start);
            if (t.mate_from(player_who_has_to_win) && player_who_start == !player_who_has_to_win) {
                for (int i = 0; i < l.size(); ++i) {
                    List<Cell> movements = l.get(i).getMovement();
                    int origen_i = l.get(i).getPosition().getI();
                    int origen_j = l.get(i).getPosition().getJ();
                    for (int j = 0; j < movements.size(); ++j) {
                        int destino_i = movements.get(j).getI();
                        int destino_j = movements.get(j).getJ();
                        Table auxiliar = new Table();
                        auxiliar.setTable(t.getTable());
                        if (auxiliar.MovePiece(origen_i, origen_j, destino_i, destino_j) && !auxiliar.mate_from(player_who_has_to_win)){
                            boolean b = achieve_the_goal(auxiliar, !player_who_start, number_of_play - 1, player_who_has_to_win);
                            System.out.println("jugada salvacion " + number_of_play+ " " + l.get(i).getName());
                            print_table(auxiliar.getTable());
                            System.out.println(player_who_start);
                            System.out.println();
                            return b;
                        }
                    }
                }
            }else {
                for (int i = 0; i < l.size(); ++i) {
                    List<Cell> movements = l.get(i).getMovement();
                    int origen_i = l.get(i).getPosition().getI();
                    int origen_j = l.get(i).getPosition().getJ();
                    for (int j = 0; j < movements.size(); ++j) {
                        int destino_i = movements.get(j).getI();
                        int destino_j = movements.get(j).getJ();
                        Table auxiliar = new Table();
                        auxiliar.setTable(t.getTable());
                        if (auxiliar.MovePiece(origen_i, origen_j, destino_i, destino_j)) {
                            /*
                        if (auxiliar.checkmate_to(!player_who_has_to_win)){
                            System.out.println("jugada legendaria" + number_of_play);
                            System.out.println(l.get(i).getName());
                            print_table(auxiliar.getTable());
                            System.out.println("");
                            return  true;
                        }
                            */
                        if (achieve_the_goal(auxiliar, !player_who_start, number_of_play - 1, player_who_has_to_win)) {
                                System.out.println("jugada maestra" + number_of_play);
                                System.out.println(l.get(i).getName());
                                print_table(auxiliar.getTable());
                                System.out.println("");
                                return true;
                         }
                        }
                    }
                }
            }
            return false;
        }
        public static List<String> load_problem_fromBD_Easy_Mode() throws IOException {
            File file = new File("BD_EASYMODE");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List <String> result = new ArrayList<String >();
            String linea;
            while ((linea = br.readLine()) != null) {
                result.add(linea);
            }
            return result;
        }
        public static List<String> load_problem_fromBD_Hard_Mode() throws IOException{
            File file = new File("BD_HARDMODE");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List <String> result = new ArrayList<String >();
            String linea;
            while ((linea = br.readLine()) != null) {
                result.add(linea);
            }
            return result;
        }

        public static void introduce_problem_toBD(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) throws IOException{
            FileWriter file = new FileWriter("BD");
        }
}
