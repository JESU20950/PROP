package Interface;

import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ModificatePanel extends JPanel{
    FrameProgram frame;
    public ModificatePanel(FrameProgram t) {
        frame = t;
        JButton Introduce_FEN = new JButton("Introduce FEN");
        JButton Eliminate_FEN = new JButton("Eliminate FEN");
        JButton Modificate_FEN = new JButton("Modificate FEN");
        JButton GoBack = new JButton("Back");
        Introduce_FEN.setPreferredSize(new Dimension(300, 40));
        Eliminate_FEN.setPreferredSize(new Dimension(300, 40));
        Modificate_FEN.setPreferredSize(new Dimension(300, 40));
        GoBack.setPreferredSize(new Dimension(300, 40));
        Introduce_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        Eliminate_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        Modificate_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        GoBack.setFont(new Font("Serif", Font.PLAIN, 30));
        Introduce_FEN.addActionListener(new introduceListener());
        Eliminate_FEN.addActionListener(new eliminateListener());
        Modificate_FEN.addActionListener(new modificateListener());
        GoBack.addActionListener(new GoBackListener());
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(Introduce_FEN,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(Eliminate_FEN,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        this.add(Modificate_FEN,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        this.add(GoBack,c);
    }

    private class GoBackListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            Cover Panel = new Cover(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    private class introduceListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            IntroduceFEN panel = new IntroduceFEN(frame, false);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }
    }
    private class eliminateListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                if (Problem.load_problem_fromBD("BD_USERPROBLEMS").isEmpty()) {
                    Cover panel = new Cover(frame);
                    frame.getMiFrame().setContentPane(panel);
                    frame.getMiFrame().revalidate();
                    frame.getMiFrame().repaint();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            EliminateFEN panel = new EliminateFEN(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }
    }
    private class modificateListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ModificateFEN panel = new ModificateFEN(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }
    }
}
