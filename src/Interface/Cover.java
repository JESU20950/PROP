package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Cover extends JPanel {
    private JFrame miFrame;
    private MainInterface main;
    Cover(MainInterface t){
    miFrame = t.getMiFrame();
    main = t;
    this.setLayout(new GridBagLayout());
    JButton botonPlayGame = new JButton("Play Game");
    JButton botonRanking = new JButton("Ranking");
    botonPlayGame.setPreferredSize(new Dimension(400, 40));
    botonRanking.setPreferredSize(new Dimension(200, 40));
    GridBagConstraints c = new GridBagConstraints();

    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 0;
    this.add(botonPlayGame,c);
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 0;
    c.gridy = 1;
    this.add(botonRanking,c);

    miFrame.setContentPane(this);
    botonPlayGame.addActionListener(new PlayGameListener());
    botonRanking.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            RankingPanel Panel = new RankingPanel();
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(Panel);
            miFrame.revalidate();
        }
    });

}


private class PlayGameListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
        PlayGamePanel Panel = new PlayGamePanel(main);
        miFrame.getContentPane().removeAll();
        miFrame.setContentPane(Panel);
        miFrame.revalidate();
    }
}

}
