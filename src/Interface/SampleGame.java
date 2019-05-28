package Interface;

import Domain.Game;

import javax.swing.*;
import java.awt.*;

import static Domain.Problem.ConvertInputtonumber_of_play;
import static Domain.Problem.ConvertInputtoplayer_who_has_to_win;

public class SampleGame extends JPanel {
    private JPanel ChessBoardPanel;
    private ChessButton chessBoard[][];
    private Game sample_game;
    public SampleGame(String FEN){
        sample_game = new Game();
        sample_game.prepareTablewithFEN(FEN);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        paint_table();
    }
    public void paint_table() {
        ChessBoardPanel = new JPanel();
        this.add(ChessBoardPanel,BorderLayout.NORTH);
        ChessBoardPanel.setLayout(new GridLayout(9, 9));
        ChessBoardPanel.setPreferredSize(new Dimension(7 * 80, 7 * 80));
        chessBoard = new ChessButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = new ChessButton(i, j);
                chessBoard[i][j].setEnabled(false);
                chessBoard[i][j].setDisabledIcon(chessBoard[i][j].getIcon());
                ChessBoardPanel.add(chessBoard[i][j]);
            }
        }
    }

    public JPanel getChessBoardPanel() {
        return ChessBoardPanel;
    }

    public void setChessBoardPanel(JPanel chessBoardPanel) {
        ChessBoardPanel = chessBoardPanel;
    }

    private class ChessButton extends JButton {
        private int i;
        private int j;

        private ChessButton(int i, int j) {
            this.i = i;
            this.j = j;
            paint_cell();
            if (sample_game.getTable().getTable()[i][j].getPiece() == null) {
                this.setIcon(null);
            } else {
                String c = sample_game.getTable().getTable()[i][j].getPiece().getName();
                switch (c) {
                    case "k":
                        this.setIcon(new ImageIcon("src/Data/" + "blackking.png"));
                        break;
                    case "q":
                        this.setIcon(new ImageIcon("src/Data/" + "blackqueen.png"));
                        break;
                    case "r":
                        this.setIcon(new ImageIcon("src/Data/" + "blackrook.png"));
                        break;
                    case "b":
                        this.setIcon(new ImageIcon("src/Data/" + "blackbishop.png"));
                        break;
                    case "n":
                        this.setIcon(new ImageIcon("src/Data/" + "blackknight.png"));
                        break;
                    case "p":
                        this.setIcon(new ImageIcon("src/Data/" + "blackpawn.png"));
                        break;
                    case "K":
                        this.setIcon(new ImageIcon("src/Data/" + "whiteking.png"));
                        break;
                    case "Q":
                        this.setIcon(new ImageIcon("src/Data/" + "whitequeen.png"));
                        break;
                    case "R":
                        this.setIcon(new ImageIcon("src/Data/" + "whiterook.png"));
                        break;
                    case "B":
                        this.setIcon(new ImageIcon("src/Data/" + "whitebishop.png"));
                        break;
                    case "N":
                        this.setIcon(new ImageIcon("src/Data/" + "whiteknight.png"));
                        break;
                    case "P":
                        this.setIcon(new ImageIcon("src/Data/" + "whitepawn.png"));
                        break;
                }
            }
        }

        private void paint_cell() {
            if (i % 2 == 0) {
                if (j % 2 == 0) this.setBackground(Color.white);
                else this.setBackground(Color.black);

            } else {
                if (j % 2 == 0) this.setBackground(Color.black);
                else this.setBackground(Color.white);
            }
        }

    }
}
