package Interface;

import Data.Game;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameInterface extends JPanel {
    private JFrame main;
    private Game actual_game;
    private ChessButton chessBoard[][];
    private int i_origen_piece;
    private int j_origen_piece;

    GameInterface(MainInterface t) {
        main = t.getMiFrame();
        actual_game = t.getActual_game();
        paint_table();
    }


    public void paint_table() {
        JTable g = new JTable();
        this.setLayout(new GridLayout(9, 9));
        this.setPreferredSize(new Dimension(8 * 80, 8 * 80));
        chessBoard = new ChessButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new ChessButton(i, j);
                this.add(chessBoard[i][j]);
            }
        }
    }


    public class ChessButton extends JButton {
        Icon piece;
        private int i;
        private int j;

        public ChessButton(int i, int j) {
            this.i = i;
            this.j = j;
            paint_cell();
            if (actual_game.getTable().getTable()[i][j].getPiece() == null) {
                piece = null;
                this.setIcon(null);
                return;
            } else {
                String c = actual_game.getTable().getTable()[i][j].getPiece().getName();
                switch (c) {
                    case "k":
                        piece = new ImageIcon("blackking.png");
                        this.setIcon(piece);
                        break;
                    case "q":
                        piece = new ImageIcon("blackqueen.png");
                        this.setIcon(piece);
                        break;
                    case "r":
                        piece = new ImageIcon("blackrook.png");
                        this.setIcon(piece);
                        break;
                    case "b":
                        piece = new ImageIcon("blackbishop.png");
                        this.setIcon(piece);
                        break;
                    case "n":
                        piece = new ImageIcon("blackknight.png");
                        this.setIcon(piece);
                        break;
                    case "p":
                        piece = new ImageIcon("blackpawn.png");
                        this.setIcon(piece);
                        break;
                    case "K":
                        piece = new ImageIcon("whiteking.png");
                        this.setIcon(piece);
                        break;
                    case "Q":
                        piece = new ImageIcon("whitequeen.png");
                        this.setIcon(piece);
                        break;
                    case "R":
                        piece = new ImageIcon("whiterook.png");
                        this.setIcon(piece);
                        break;
                    case "B":
                        piece = new ImageIcon("whitebishop.png");
                        this.setIcon(piece);
                        break;
                    case "N":
                        piece = new ImageIcon("whiteknight.png");
                        this.setIcon(piece);
                        break;
                    case "P":
                        piece = new ImageIcon("whitepawn.png");
                        this.setIcon(piece);
                        break;
                }
            }
            this.addActionListener(new moveofhuman());
        }
        void paint_cell(){
             if (i % 2 == 0) {
                 if (j % 2 == 0) this.setBackground(Color.white);
                 else this.setBackground(Color.black);

             } else {
                 if (j % 2 == 0) this.setBackground(Color.black);
                 else this.setBackground(Color.white);
             }
        }
        public Icon getPiece() {
            return piece;
        }

        private class moveofhuman implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                if (actual_game.Piececanmoveinthismoment(i, j)) {
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    i_origen_piece = i;
                    j_origen_piece = j;
                    chessBoard[i_origen_piece][j_origen_piece].setBackground(Color.RED);
                    return;
                } else {
                    if (!actual_game.getTable().MovePiece(i_origen_piece, j_origen_piece, i, j)) {
                        JOptionPane.showMessageDialog(main,
                                "Incorrect movement.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    chessBoard[i_origen_piece][j_origen_piece].paint_cell();
                    chessBoard[i][j].setIcon(chessBoard[i_origen_piece][j_origen_piece].getPiece());
                    chessBoard[i_origen_piece][j_origen_piece].setIcon(null);
                    actual_game.setPlayer_who_plays(!actual_game.getPlayer_who_plays());
                    actual_game.setNumber_of_play(actual_game.getNumber_of_play() - 1);
                }
            }
        }
    }
}