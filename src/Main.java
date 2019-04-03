import Data.Cell;
import Data.Table;

public class Main {
    public static void main(String[] args){
        String s = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Table t = new Table(s);
        Cell[][] t2 = testgettable(t);
        print_table(t2);

    }

    private static Table testConstructor(String s){
        return new Table(s);
    }
    private static Cell[][] testgettable(Table t){
        return t.gettable();
    }

    private static void print_table(Cell[][] table){
        for (int i = 0; i<8; ++i){
            for  (int j = 0; j<8; ++j){
                if (table[i][j].getPiece() == null)System.out.println("X");
                else System.out.println(table[i][j].getPiece().getName());

            }
            System.out.println(System.getProperty("line.separator"));
        }
    }
}
