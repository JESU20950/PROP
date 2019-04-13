package Data;

import Data.Player.Player;

import java.io.IOException;
import java.util.Scanner;

import static Data.Problem.*;


public class Game {
    private int number_of_play;
    private Table table;
    private boolean player_who_plays;
    private boolean player_who_has_to_win;
    private Player player1;
    private Player player2;

    public Game () throws CloneNotSupportedException, IOException {
        System.out.println("Selecciona opciones con numero");
        System.out.println("1.Jugar Partida");
        System.out.println("2.Ver Ranking problemas");
        Scanner teclado = new Scanner (System.in);
        int input = teclado.nextInt();
        if (input == 1){
            System.out.println("Selecciona opciones con numero");
            System.out.println("1. Cargar Problema");
            System.out.println("2. Introducir Problema");
            input = teclado.nextInt();
            String FEN;
            if (input == 1){
                FEN = load_problem_fromBD();
            }else{
                FEN = introduce_problem();
            }
            table = new Table(ConvertInputtoFEN(FEN));
            number_of_play = ConvertInputtonumber_of_play(FEN);
            player_who_plays = ConvertInputtoplayer_who_start(FEN);
            player_who_has_to_win = ConvertInputtoplayer_who_has_to_win(FEN);


        }else {



        }

    }
}
