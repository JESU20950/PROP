package Interface;

import Data.Game;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


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
    GameInterface(FrameProgram t) {
        frame = t;
        actual_game = t.getActual_game();
        actual_game.setGameInterface(this);
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

    public void dialaog_end_of_game() throws Exception {
        if (actual_game.getplayerwhowins()) JOptionPane.showMessageDialog(frame.getMiFrame(),
                "Congratulation white player",
                "CONGRATULATION",
                JOptionPane.NO_OPTION);
        else{
            JOptionPane.showMessageDialog(frame.getMiFrame(),
                    "Congratulation black player",
                    "CONGRATULATION",
                    JOptionPane.NO_OPTION);
        }
        frame.newframe();
    }


    public class ChessButton extends JButton {
        private int i;
        private int j;

        public ChessButton(int i, int j) {
            this.i = i;
            this.j = j;
            paint_cell();
            if (actual_game.getTable().getTable()[i][j].getPiece() == null) {
                this.setIcon(null);
            } else {
                String c = actual_game.getTable().getTable()[i][j].getPiece().getName();
                switch (c) {
                    case "k":
                        this.setIcon(new ImageIcon("blackking.png"));
                        break;
                    case "q":
                        this.setIcon(new ImageIcon("blackqueen.png"));
                        break;
                    case "r":
                        this.setIcon(new ImageIcon("blackrook.png"));
                        break;
                    case "b":
                        this.setIcon(new ImageIcon("blackbishop.png"));
                        break;
                    case "n":
                        this.setIcon(new ImageIcon("blackknight.png"));
                        break;
                    case "p":
                        this.setIcon(new ImageIcon("blackpawn.png"));
                        break;
                    case "K":
                        this.setIcon(new ImageIcon("whiteking.png"));
                        break;
                    case "Q":
                        this.setIcon(new ImageIcon("whitequeen.png"));
                        break;
                    case "R":
                        this.setIcon(new ImageIcon("whiterook.png"));
                        break;
                    case "B":
                        this.setIcon(new ImageIcon("whitebishop.png"));
                        break;
                    case "N":
                        this.setIcon(new ImageIcon("whiteknight.png"));
                        break;
                    case "P":
                        this.setIcon(new ImageIcon("whitepawn.png"));
                        break;
                }
            }
            this.addActionListener(new movementofhuman());
        }
        public void paint_cell(){
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
                if (actual_game.Piececanmoveinthismoment(i, j)) {
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    i_origen_piece = i;
                    j_origen_piece = j;
                    chessBoard[i_origen_piece][j_origen_piece].setBackground(Color.RED);
                }else actual_game.graphic_move_and_internal_move(i_origen_piece, j_origen_piece, i, j);
                    /*
                    if (!actual_game.getTable().MovePiece(i_origen_piece, j_origen_piece, i, j)) return;
                    chessBoard[i][j].setIcon(chessBoard[i_origen_piece][j_origen_piece].getIcon());
                    chessBoard[i_origen_piece][j_origen_piece].setIcon(null);
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    actual_game.setPlayer_who_plays(!actual_game.getPlayer_who_plays());
                    actual_game.setNumber_of_play(actual_game.getNumber_of_play() - 1);
                    try {
                        actual_game.endofgame();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                     */

            }
        }
    }

}