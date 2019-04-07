package Data;


import java.util.Arrays;
import Data.Table;
import Data.Pieces.Piece;

public class Problem {


        public static boolean iscorrectFen(String FEN){
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
        }


        static private String ConvertInputtoFEN(String input){
        String FEN = "";
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' '){
         FEN = FEN + input.charAt(w);
         ++w;
            }
        return FEN;
        }
        static private boolean ConvertInputtoplayer(String input){
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        return input.charAt(w) == 'w';
        }

        static boolean isCorrectProblem(String input,int number_of_play, boolean player_who_has_to_win){
            String FEN = ConvertInputtoFEN(input);
            boolean player_who_start = ConvertInputtoplayer(input);
            if (!iscorrectFen(FEN)) return false;
            Table t = new Table(FEN);
            return achieve_the_number_of_play(t,player_who_start, number_of_play*2, player_who_has_to_win);

        }
        static private boolean achieve_the_number_of_play(Table t, boolean player_who_plays, int depth, boolean player_Max){

            if (depth >= 0 && t.getKing(!player_Max) == null) return true;
            else if (depth == 0 && t.getKing(!player_Max) == null) return true;

            for (int i = 0; i<8; ++i){
                for (int j = 0; j<8; ++j){
                    Cell aux = t.getnextpieceofcolor(player_who_plays, i,j);
                    for (int ii = 0; ii<8; ++ii){
                        for (int jj = 0; jj<8; ++jj){
                         if (t.MovePiece(aux.getI(),aux.getJ(),ii,jj)){
                         achieve_the_number_of_play(t,!player_who_plays,--depth,player_Max);
                         t.undoMovePiece(ii,jj, aux.getI(),aux.getJ());
                         }

                        }
                    }
                }
            }

            return false;
        }



}
