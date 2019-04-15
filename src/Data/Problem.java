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
            if (index < FEN.length() && FEN.charAt(index) == ' ') return (total_rows == 8);
            if (index < FEN.length() && FEN.charAt(index) == '/') ++index;
        }
        return (total_rows == 8);
    }


        /*public static boolean iscorrectFen(String FEN){
            int n_K = 0;
            int n_k = 0;
            int n_Q = 0;
            int n_q = 0;
            int n_R = 0;
            int n_r = 0;
            int n_B = 0;
            int n_b = 0;
            int n_P = 0;
            int n_p = 0;
            int n_N = 0;
            int n_n = 0;
            int w = 0;
            while (w < FEN.length()) {
                int eight_elements = 0;
                while (w < FEN.length() && FEN.charAt(w) != '/') {
                    switch (FEN.charAt(w)) {
                        case 'k':
                            ++n_k;
                            ++eight_elements;
                            break;
                        case 'q':
                            ++n_q;
                            ++eight_elements;
                            break;
                        case 'r':
                            ++n_r;
                            ++eight_elements;
                            break;
                        case 'b':
                            ++n_b;
                            ++eight_elements;
                            break;
                        case 'n':
                            ++n_n;
                            ++eight_elements;
                            break;
                        case 'p':
                            ++n_p;
                            ++eight_elements;
                            break;
                        case 'K':
                            ++n_K;
                            ++eight_elements;
                            break;
                        case 'Q':
                            ++n_Q;
                            ++eight_elements;
                            break;
                        case 'R':
                            ++n_R;
                            ++eight_elements;
                            break;
                        case 'B':
                            ++n_B;
                            ++eight_elements;
                            break;
                        case 'N':
                            ++n_N;
                            ++eight_elements;
                            break;
                        case 'P':
                            ++n_P;
                            ++eight_elements;
                            break;
                        default:
                            int space = Character.getNumericValue(FEN.charAt(w));
                            if (space >= 0 && space <= 9) eight_elements = eight_elements + space;
                            else return false;
                    }
                    ++w;
                }
                if (n_K > 2 || n_k > 2 || n_Q > 1 || n_q > 1 || n_R > 2 || n_r  > 2 || n_b > 2 || n_B > 2 || n_P > 8 || n_p > 8 || n_N > 2 || n_n > 2) return false;
                if (eight_elements != 8) return false;
                ++w;
            }
            return !(n_K > 2 || n_k > 2 || n_Q > 1 || n_q > 1 || n_R > 2 || n_r  > 2 || n_b > 2 || n_B > 2 || n_P > 8 || n_p > 8 || n_N > 2 || n_n > 2);
        }*/


        static public String ConvertInputtoFEN(String input){
        String FEN = "";
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' '){
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

        static public boolean isCorrectProblem(String input) throws CloneNotSupportedException{
            String FEN = ConvertInputtoFEN(input);
            boolean player_who_start = ConvertInputtoplayer_who_start(input);
            boolean player_who_has_to_win = ConvertInputtoplayer_who_has_to_win(input);
            int number_of_play = ConvertInputtonumber_of_play(input);
            if (!iscorrectFen(FEN)) return false;
            Table t = new Table(FEN);
            return achieve_the_goal(t,player_who_start ,2*number_of_play, player_who_has_to_win);
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
            File file = new File("BD");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List <String> result = new ArrayList<String >();
            String linea;
            linea = br.readLine();
            while ((linea = br.readLine()).charAt(0) != 'H') {
                result.add(linea);
            }
            return result;
        }
        public static List<String> load_problem_fromBD_Hard_Mode() throws IOException{
            File file = new File("BD");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List <String> result = new ArrayList<String >();
            String linea;
            while ((linea = br.readLine()).charAt(0) != 'H');
            while ((linea = br.readLine()) != null) {
                result.add(linea);
            }
            return result;
        }
        static String introduce_problem() throws CloneNotSupportedException,IOException {
            System.out.println("Introduce FEN next line:");
            Scanner teclado = new Scanner (System.in);
            String input = teclado.nextLine();
            while (!isCorrectProblem(input)) input = teclado.nextLine();
            introduce_problem_toBD(input);
            return input;
        }
        static void introduce_problem_toBD(String input) throws IOException{
            FileWriter file = new FileWriter("BD",true);
            PrintWriter pw = new PrintWriter(file);
            pw.println(input);
        }
}
