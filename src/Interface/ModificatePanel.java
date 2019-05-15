package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificatePanel extends JPanel{
    FrameProgram frame;
    public ModificatePanel(FrameProgram t) {
        frame = t;
        JButton Introduce_FEN = new JButton();
        JButton Eliminate_FEN = new JButton();
        JButton Modificate_FEN = new JButton();
        Introduce_FEN.setLabel("Introduce FEN");
        Eliminate_FEN.setLabel("Eliminate FEN");
        Modificate_FEN.setLabel("Modificate FEN");
        Introduce_FEN.setPreferredSize(new Dimension(300, 40));
        Eliminate_FEN.setPreferredSize(new Dimension(300, 40));
        Modificate_FEN.setPreferredSize(new Dimension(300, 40));
        Introduce_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        Eliminate_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        Modificate_FEN.setFont(new Font("Serif", Font.PLAIN, 30));
        Introduce_FEN.addActionListener(new introduceListener());
        Eliminate_FEN.addActionListener(new eliminateListener());
        Modificate_FEN.addActionListener(new modificateListener());
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
    }
    private class introduceListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            IntroduceFEN panel = new IntroduceFEN(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }
    }
    private class eliminateListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
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
