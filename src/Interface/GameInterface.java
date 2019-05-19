package Interface;





import Domain.Cell;
import Domain.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

import static java.lang.System.exit;


public class GameInterface extends JPanel {
    private FrameProgram frame;
    private JPanel ChessBoardPanel;
    private JPanel infoPanel;
    private Game actual_game;
    private ChessButton chessBoard[][];
    private int i_origen_piece;
    private int j_origen_piece;
    private JLabel Player_who_plays;
    private JLabel Player_who_has_to_win;
    private JLabel Number_of_plays;
    private JLabel timer;
    GameInterface(FrameProgram t) {
        frame = t;
        actual_game = t.getActual_game();
        this.setLayout(new BorderLayout());
        paint_table();
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        this.add(infoPanel,BorderLayout.NORTH);
        Player_who_plays = new JLabel();
        Player_who_has_to_win = new JLabel();
        Number_of_plays = new JLabel();
        paint_info();
        infoPanel.add(Player_who_has_to_win);
        infoPanel.add(Player_who_plays);
        infoPanel.add(Number_of_plays);
        Threadinfo threadinfo = new Threadinfo();
        Thread r = new Thread(threadinfo);
        r.start();
        timer = new JLabel();
        timer.setFont(new java.awt.Font("Tahoma", 3, 60));
        this.add(timer,BorderLayout.SOUTH);

        ThreadMachine machine = new ThreadMachine();
        Thread s = new Thread(machine);
        s.start();
    }
    public void paint_info(){
        setPlayer_who_plays_label(actual_game.getPlayer_who_plays());
        setPlayer_who_has_to_win_label(actual_game.getPlayer_who_has_to_win());
        setNumber_of_plays_label(actual_game.getNumber_of_play());
    }
    public void setPlayer_who_plays_label(boolean player){
        if (player) Player_who_plays.setText("Player who plays: White");
        else Player_who_plays.setText("Player who plays: Black");
    }
    public void setPlayer_who_has_to_win_label(boolean player){
        if (player) Player_who_has_to_win.setText("Player who has to win: White");
        else Player_who_has_to_win.setText("Player who has to win: Black");
    }
    public void setNumber_of_plays_label(int number_of_play){
        Number_of_plays.setText("Number of play: " + number_of_play);
    }
    public void paint_table() {
        ChessBoardPanel = new JPanel();
        this.add(ChessBoardPanel,BorderLayout.CENTER);
        ChessBoardPanel.setLayout(new GridLayout(9, 9));
        ChessBoardPanel.setPreferredSize(new Dimension(8 * 80, 8 * 80));
        chessBoard = new ChessButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new ChessButton(i, j);
                ChessBoardPanel.add(chessBoard[i][j]);
            }
        }
    }

    public ChessButton[][] getChessBoard() {
        return chessBoard;
    }

    public void dialaog_end_of_game(){
        if (actual_game.getTable().checkmate(true, true) || actual_game.getTable().checkmate(true, false) )
            JOptionPane.showMessageDialog(frame.getMiFrame(),
                "Congratulations white player",
                "CONGRATULATIONS",
                JOptionPane.NO_OPTION);
        else{
            JOptionPane.showMessageDialog(frame.getMiFrame(),
                    "Congratulations black player",
                    "CONGRATULATIONS",
                    JOptionPane.NO_OPTION);
        }
        Cover Panel = new Cover(frame);
        frame.getMiFrame().setContentPane(Panel);
        frame.getMiFrame().revalidate();
        frame.getMiFrame().repaint();
    }

    private class ChessButton extends JButton {
        private int i;
        private int j;

        private ChessButton(int i, int j) {
            this.i = i;
            this.j = j;
            paint_cell();
            if (actual_game.getTable().getTable()[i][j].getPiece() == null) {
                this.setIcon(null);
            } else {
                String c = actual_game.getTable().getTable()[i][j].getPiece().getName();
                switch (c) {
                    case "k":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackking.png"));
                        break;
                    case "q":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackqueen.png"));
                        break;
                    case "r":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackrook.png"));
                        break;
                    case "b":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackbishop.png"));
                        break;
                    case "n":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackknight.png"));
                        break;
                    case "p":
                        this.setIcon(new ImageIcon("src/Data/"+ "blackpawn.png"));
                        break;
                    case "K":
                        this.setIcon(new ImageIcon("src/Data/"+ "whiteking.png"));
                        break;
                    case "Q":
                        this.setIcon(new ImageIcon("src/Data/"+ "whitequeen.png"));
                        break;
                    case "R":
                        this.setIcon(new ImageIcon("src/Data/"+ "whiterook.png"));
                        break;
                    case "B":
                        this.setIcon(new ImageIcon("src/Data/"+ "whitebishop.png"));
                        break;
                    case "N":
                        this.setIcon(new ImageIcon("src/Data/"+ "whiteknight.png"));
                        break;
                    case "P":
                        this.setIcon(new ImageIcon("src/Data/"+ "whitepawn.png"));
                        break;
                }
            }
            this.addActionListener(new movementofhuman());
        }
        private void paint_cell(){
             if (i % 2 == 0) {
                 if (j % 2 == 0) this.setBackground(Color.white);
                 else this.setBackground(Color.black);

             } else {
                 if (j % 2 == 0) this.setBackground(Color.black);
                 else this.setBackground(Color.white);
             }
        }

        private class movementofhuman implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                    boolean turn_of_machine = actual_game.getPlayerwhoplays().isMachine();
                    boolean correct_move = actual_game.getTable().CorrectMove(i_origen_piece, j_origen_piece, i, j);

                if (!turn_of_machine && correct_move && actual_game.getTable().getTable()[i_origen_piece][j_origen_piece].getPiece().getColor() == actual_game.getPlayer_who_plays()){
                    chessBoard[i][j].setIcon(chessBoard[i_origen_piece][j_origen_piece].getIcon());
                    chessBoard[i_origen_piece][j_origen_piece].setIcon(null);
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    actual_game.getTable().MovePiece(i_origen_piece, j_origen_piece, i, j);
                    actual_game.setPlayer_who_plays(!actual_game.getPlayer_who_plays());
                    actual_game.setNumber_of_play(actual_game.getNumber_of_play() - 1);
                    paint_info();
                }else if (!turn_of_machine && actual_game.getTable().getTable()[i][j].getPiece() != null && actual_game.getTable().getTable()[i][j].getPiece().getColor() == actual_game.getPlayer_who_plays()){
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    chessBoard[i][j].setBackground(Color.BLUE);
                    i_origen_piece = i;
                    j_origen_piece = j;
                }
            }
        }
    }
    class Threadinfo implements Runnable {
        public void run() {
            actual_game.startchronometer();
            long duration = Duration.between(actual_game.getStart(),actual_game.getStart()).toMillis();
            duration = duration/1000;
            String duration_string = Long.toString(duration );
            timer.setText(duration_string + " SEGUNDOS");
            while(!actual_game.endofgame() || actual_game.getTable().king_position(true) != null || actual_game.getTable().king_position(false) != null){
                duration = Duration.between(actual_game.getStart(), Instant.now()).toMillis();
                duration = duration/1000;
                duration_string = Long.toString(duration);
                timer.setText(duration_string + " SEGUNDOS");
                repaint();
            }
            System.out.println(actual_game.endofgame());
            dialaog_end_of_game();
        }
    }
    class ThreadMachine implements Runnable {
        public void run(){
            while(!actual_game.endofgame()){
                if (actual_game.getPlayerwhoplays().isMachine()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(actual_game.endofgame());
                    Cell[] movement = actual_game.getPlayerwhoplays().move_piece(actual_game);
                    int iorigen = movement[0].getI();
                    int jorigen = movement[0].getJ();
                    int idestino = movement[1].getI();
                    int jdestino = movement[1].getJ();
                    chessBoard[idestino][jdestino].setIcon(chessBoard[iorigen][jorigen].getIcon());
                    chessBoard[iorigen][jorigen].setIcon(null);
                    actual_game.setPlayer_who_plays(!actual_game.getPlayer_who_plays());
                    actual_game.setNumber_of_play(actual_game.getNumber_of_play() - 1);
                    actual_game.getTable().print_table();
                    paint_info();
                }
            }
            dialaog_end_of_game();
        }
    }

}