package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Cover extends JPanel {
    private FrameProgram frame;
    Cover(FrameProgram t){
        frame = t;
        this.setLayout(new GridBagLayout());
        JButton botonPlayGame = new JButton("Play Game");
        JButton botonRanking = new JButton("Ranking");
        JButton botonModificateProblem = new JButton("Modificate User Problem");
        JButton botonQuit = new JButton("Quit");
        botonPlayGame.setPreferredSize(new Dimension(600, 100));
        botonRanking.setPreferredSize(new Dimension(500, 50));
        botonModificateProblem.setPreferredSize(new Dimension(500, 50));
        botonQuit.setPreferredSize(new Dimension(200, 50));
        botonPlayGame.setFont(new Font("Serif", Font.PLAIN, 70));
        botonRanking.setFont(new Font("Serif", Font.PLAIN, 30));
        botonModificateProblem.setFont(new Font("Serif", Font.PLAIN, 30));
        botonQuit.setFont(new Font("Serif", Font.PLAIN, 20));
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(botonPlayGame,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(botonRanking,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        this.add(botonModificateProblem,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        this.add(botonQuit,c);
        frame.getMiFrame().setContentPane(this);
        botonPlayGame.addActionListener(new PlayGameListener());
        botonRanking.addActionListener(new RankingListener());
        botonModificateProblem.addActionListener(new ModificateProblemListener());
        botonPlayGame.addActionListener(new PlayGameListener());
        botonQuit.addActionListener(new Quit());
    }

    private class Quit implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    }

    private class PlayGameListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PlayerPanel Panel= new PlayerPanel(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
        }
    }
    private class ModificateProblemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ModificatePanel Panel= new ModificatePanel(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
        }
    }
    private class RankingListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            RankingPanel Panel= new RankingPanel(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
        }
    }
}
