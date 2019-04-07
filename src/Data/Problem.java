package Data;

import java.util.Arrays;

public class Problem {


        static public boolean iscorrectFen(String FEN){
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
            while (FEN.charAt(w) == ' ') ++w;
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



}
