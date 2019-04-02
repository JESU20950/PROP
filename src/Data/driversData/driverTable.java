package Data.driversData;
import Data.Cell;
import Data.Table;

public class driverTable {


    public static void main(String[] args){
        String = args[0];
        Table t = testConstructor(args);
        Cell[][] t2 = testgettable(t);
    }

    private static Table testConstructor(String[] args){
        return new Table(args);
    }
    private static Cell[][] testgettable(Table t){
        return t.gettable();
    }

}
