package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Cover extends JFrame {
    private JFrame miFrame;
    public Cover() {
        // Se instancian tres botones con textos indicando lo que
        // hacen cuando se pulse sobre ellos

        miFrame = new JFrame("ChessGameUPC");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        miFrame.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        miFrame.setVisible(true);


        JPanel Panel= new JPanel();
        Panel.setLayout(new GridBagLayout());
        JButton botonPlayGame = new JButton("Play Game");
        JButton botonRanking = new JButton("Ranking");
        botonPlayGame.setPreferredSize(new Dimension(400, 40));
        botonRanking.setPreferredSize(new Dimension(200, 40));
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        Panel.add(botonPlayGame,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        Panel.add(botonRanking,c);

        miFrame.setContentPane(Panel);
        botonPlayGame.addActionListener(new PlayGameListener());
        botonRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RankingPanel Panel = new RankingPanel();
                miFrame.getContentPane().removeAll();
                miFrame.setContentPane(Panel);
                miFrame.revalidate();
            }
        });

        // Instancia y registra un objeto WindowListener sobre el objeto
        // Frame para terminar el programa cuando el usuario haga click
        // con el raton sobre el boton de cerrar la ventana que se
        // coloca sobre el objeto Frame
        miFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                // Concluye la aplicacion cuando el usuario cierra la
                // ventana
                System.exit(0);
            }
        });
    }


    private class PlayGameListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PlayGamePanel Panel = new PlayGamePanel(miFrame);
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(Panel);
            miFrame.revalidate();
        }
    }

}
