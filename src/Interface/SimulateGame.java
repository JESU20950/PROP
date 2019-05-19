package Interface;

import Domain.Game;
import Domain.Player.Machine1;
import Domain.Player.Machine2;
import Domain.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulateGame extends JPanel implements ActionListener {
    private FrameProgram frame;
    private JButton back;
    private JButton menu;
    private JButton play;
    private JLabel player1;
    private JLabel player2;
    private ArrayList<String> problems;
    private Game actualGame;
    private boolean machine1;
    private boolean machine2;

    public SimulateGame(FrameProgram t, ArrayList<String> FENs, boolean CPU1, boolean CPU2) {
        machine1 = CPU1;
        machine2 = CPU2;
        this.setLayout(new GridBagLayout());
        frame = t;
        problems = FENs;
        back = new JButton("Back");
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.addActionListener(this);
        menu = new JButton("Main menu");
        menu.setFont(new Font("Serif", Font.PLAIN, 30));
        menu.addActionListener(this);
        play = new JButton("Play");
        play.setFont(new Font("Serif", Font.PLAIN, 30));
        play.addActionListener(this);
        player1 = new JLabel("Player1 has won: ");
        player1.setFont(new Font("Serif", Font.PLAIN, 20));
        player2 = new JLabel("Player2 has won: ");
        player2.setFont(new Font("Serif", Font.PLAIN, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        this.add(back, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 2;
        this.add(menu, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        this.add(play, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(player1, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(player2, c);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            MachinePanel Panel = new MachinePanel(frame, machine1, machine2);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == menu) {
            Cover Panel = new Cover(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == play) {
            int p1wins = 0;
            int p2wins = 0;
            player1.setText("Player1 has won: ");
            player2.setText("Player2 has won: ");
            for (int i = 0; i < problems.size(); ++i) {
                actualGame = new Game();
                Player p1, p2;
                if (machine1) p1 = new Machine2();
                else p1 = new Machine1();
                if (machine2) p2 = new Machine2();
                else p2 = new Machine1();
                actualGame.prepareTablewithFEN(problems.get(i));
                actualGame.setPlayer1(p1);
                actualGame.setPlayer2(p2);
                while (!actualGame.endofgame()) {
                    if (actualGame.getPlayer_who_plays()) {
                        actualGame.getPlayer1().move_piece(actualGame);
                    }
                    else {
                        actualGame.getPlayer2().move_piece(actualGame);
                    }
                    actualGame.setNumber_of_play(actualGame.getNumber_of_play()-1);
                    actualGame.setPlayer_who_plays(!actualGame.getPlayer_who_plays());
                }
                if (actualGame.getTable().checkmate(true, true) || actualGame.getTable().checkmate(true, false)) p1wins++;
                else p2wins++;
            }
            player1.setText(player1.getText() + p1wins + " times");
            player2.setText(player2.getText() + p2wins + " times");
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }
}
