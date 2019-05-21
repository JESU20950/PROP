package Interface;

import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import static Domain.Problem.*;

public class IntroduceFEN extends JPanel {
    private FrameProgram frame;
    private JTextField FEN;
    private JSpinner number_of_plays;
    private JComboBox player_who_has_to_achive_the_check_mate;
    private JComboBox player_who_starts;
    private SampleGame table;

    IntroduceFEN(FrameProgram t) {
        frame = t;
        FEN = new JTextField();
        FEN.addFocusListener(new changetext());
        FEN.setPreferredSize(new Dimension(400, 30));
        player_who_starts = new JComboBox<String>();

        player_who_starts.addItem("White");
        player_who_starts.addItem("Black");
        player_who_has_to_achive_the_check_mate = new JComboBox<String>();
        number_of_plays = new JSpinner();
        number_of_plays.setPreferredSize(new Dimension(50, 30));
        player_who_has_to_achive_the_check_mate.addItem("White");
        player_who_has_to_achive_the_check_mate.addItem("Black");
        JButton introduceProblem = new JButton("Introduce Problem");
        JButton GoBack = new JButton("Back");
        introduceProblem.addMouseListener(new double_click());
        GoBack.addActionListener(new BackListener());
        FEN.setFont(new Font("Serif", Font.PLAIN, 20));
        player_who_starts.setFont(new Font("Serif", Font.PLAIN, 20));
        player_who_has_to_achive_the_check_mate.setFont(new Font("Serif", Font.PLAIN, 20));
        player_who_starts.setFont(new Font("Serif", Font.PLAIN, 20));
        introduceProblem.setFont(new Font("Serif", Font.PLAIN, 30));
        GoBack.setFont(new Font("Serif", Font.PLAIN, 30));
        number_of_plays.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel label1 = new JLabel("FEN");
        JLabel label2 = new JLabel("Player who starts playing");
        JLabel label3 = new JLabel("Player who has to achive checkmate");
        JLabel label4 = new JLabel("Number of plays");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        label4.setFont(new Font("Serif", Font.PLAIN, 20));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        this.add(FEN, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 3;
        this.add(player_who_has_to_achive_the_check_mate, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 5;
        this.add(player_who_starts, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 7;
        this.add(number_of_plays, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 10;
        this.add(introduceProblem, c);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 10;
        this.add(GoBack, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(label1, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 2;
        this.add(label2, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 4;
        this.add(label3, c);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 6;
        this.add(label4, c);

        table = new SampleGame("8/8/8/8/8/8/8/8 w w 2");
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 20;
        this.add(table, c);

    }

    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ModificatePanel Panel = new ModificatePanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    private class double_click extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 1) {
                if (!iscorrectFen(FEN.getText())) {
                    JOptionPane.showMessageDialog(frame.getMiFrame(),
                            "FEN is incorrect.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String FENtext = FEN.getText();
                boolean player_who_start_bool = player_who_starts.getSelectedItem() == "White";
                boolean player_who_has_to_win = player_who_has_to_achive_the_check_mate.getSelectedItem() == "White";
                boolean iscorrectproblem;
                int number_of_plays_int = (int) number_of_plays.getValue();
                if (number_of_plays_int < 0) {
                    JOptionPane.showMessageDialog(frame.getMiFrame(),
                            "Number of plays negative.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                number_of_plays_int = number_of_plays_int*2;
                iscorrectproblem = isCorrectProblem(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int);
                if (!iscorrectproblem) {
                    JOptionPane.showMessageDialog(frame.getMiFrame(),
                            "Problem is incorrect.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    number_of_plays_int = number_of_plays_int/2;
                    introduce_problem_toBD(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame.getMiFrame(),
                        "Problem introduced",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class changetext extends FocusAdapter {
        public void focusLost(final FocusEvent evt) {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            c.gridx = 0;
            c.gridy = 20;
            if (iscorrectFen(FEN.getText())) {
                frame.getMiFrame().getContentPane().remove(table);
                table = new SampleGame(FEN.getText() + " w w 2");
                frame.getMiFrame().getContentPane().add(table, c);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            } else {
                frame.getMiFrame().getContentPane().remove(table);
                table = new SampleGame("8/8/8/8/8/8/8/8 w w 2");
                frame.getMiFrame().getContentPane().add(table, c);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }
    }
}
