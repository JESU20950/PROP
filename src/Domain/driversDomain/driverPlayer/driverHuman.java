package Domain.driversDomain.driverPlayer;



import Domain.Game;
import Domain.Player.Human;
import Domain.Player.Player;

import java.util.Scanner;

public class driverHuman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Game g = new Game();
        g.prepareTablewithFEN(s);
        g.getTable().print_table();
        s = sc.nextLine();
        Player p = new Human(s);
        p.setColor(true);
        p.move_piece(g);
    }
}
