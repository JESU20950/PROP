package Interface;

import javax.print.attribute.HashAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.CharsetDecoder;

public class MachinePanel extends JPanel implements ActionListener {
    private FrameProgram frame;
    private JButton easy_problems;
    private JButton hard_problems;
    private JButton user_problems;
    private JButton back;

    public MachinePanel(FrameProgram t) {
        frame = t;
        this.setLayout(new GridBagLayout());
        easy_problems = new JButton("Easy Problems");
        hard_problems = new JButton("Hard Problems");
        user_problems = new JButton("User Problems");
        back = new JButton("Back");
        easy_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        hard_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        user_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        easy_problems.addActionListener(this);
        hard_problems.addActionListener(this);
        user_problems.addActionListener(this);
        back.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(easy_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(hard_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        this.add(user_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        this.add(back, c);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easy_problems) {
            MultipleProblems Panel = new MultipleProblems(frame, 0);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == hard_problems) {
            MultipleProblems Panel = new MultipleProblems(frame, 1);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == user_problems) {
            MultipleProblems Panel = new MultipleProblems(frame, 2);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == back) {
            PlayerPanel Panel = new PlayerPanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }
}
