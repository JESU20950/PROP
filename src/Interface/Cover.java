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
    botonPlayGame.setPreferredSize(new Dimension(600, 100));
    botonRanking.setPreferredSize(new Dimension(500, 50));
    botonModificateProblem.setPreferredSize(new Dimension(500, 50));
    botonPlayGame.setFont(new Font("Serif", Font.PLAIN, 70));
    botonRanking.setFont(new Font("Serif", Font.PLAIN, 30));
    botonModificateProblem.setFont(new Font("Serif", Font.PLAIN, 30));
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
    frame.getMiFrame().setContentPane(this);
    botonPlayGame.addActionListener(new PlayGameListener());
    botonRanking.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            RankingPanel Panel = new RankingPanel();
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
        }
    });
    botonModificateProblem.addActionListener(new ModificateProblemListener());
    botonPlayGame.addActionListener(new PlayGameListener());

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
}
