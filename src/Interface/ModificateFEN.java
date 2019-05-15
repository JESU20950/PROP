package Interface;

import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import static Domain.Problem.*;

public class ModificateFEN extends JPanel {
    private FrameProgram frame;

    public ModificateFEN(FrameProgram t) {
        frame = t;
        Modificateproblem modificatefen  = new Modificateproblem();
        modificatefen.setPreferredSize(new Dimension(400,900));
        this.add(modificatefen,BorderLayout.WEST);
        Introuduceproblem introducefen = new Introuduceproblem();
        introducefen.setPreferredSize(new Dimension(400,900));
        this.add(introducefen);
    }

    private class Introuduceproblem extends JPanel {
        JPanel miPanel;
        private JTextField FEN;
        private JSpinner number_of_plays;
        private JComboBox player_who_has_to_achive_the_check_mate;
        private JComboBox player_who_starts;
        private SampleGame table;

        private Introuduceproblem() {
            miPanel = this;
            FEN = new JTextField();
            FEN.addFocusListener(new changetext());
            FEN.setPreferredSize(new Dimension(200, 15));
            player_who_starts = new JComboBox<String>();

            player_who_starts.addItem("White");
            player_who_starts.addItem("Black");
            player_who_has_to_achive_the_check_mate = new JComboBox<String>();
            number_of_plays = new JSpinner();
            number_of_plays.setPreferredSize(new Dimension(25, 15));
            player_who_has_to_achive_the_check_mate.addItem("White");
            player_who_has_to_achive_the_check_mate.addItem("Black");
            JButton introduceProblem = new JButton("Modificate Problem");
            introduceProblem.addMouseListener(new double_click());
            FEN.setFont(new Font("Serif", Font.PLAIN, 10));
            player_who_starts.setFont(new Font("Serif", Font.PLAIN, 10));
            player_who_has_to_achive_the_check_mate.setFont(new Font("Serif", Font.PLAIN, 10));
            player_who_starts.setFont(new Font("Serif", Font.PLAIN, 10));
            introduceProblem.setFont(new Font("Serif", Font.PLAIN, 15));
            number_of_plays.setFont(new Font("Serif", Font.PLAIN, 10));
            JLabel label1 = new JLabel("FEN");
            JLabel label2 = new JLabel("Player who starts playings");
            JLabel label3 = new JLabel("Player who has to achive checkmate");
            JLabel label4 = new JLabel("Number of plays");
            label1.setFont(new Font("Serif", Font.PLAIN, 10));
            label2.setFont(new Font("Serif", Font.PLAIN, 10));
            label3.setFont(new Font("Serif", Font.PLAIN, 10));
            label4.setFont(new Font("Serif", Font.PLAIN, 10));
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
            c.anchor = GridBagConstraints.NORTH;
            c.gridx = 0;
            c.gridy = 10;
            this.add(introduceProblem, c);
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
            table.setPreferredSize(new Dimension(20,20));
            this.add(table, c);
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
                    boolean iscorrectproblem = false;
                    int number_of_plays_int = (int) number_of_plays.getValue();
                    if (number_of_plays_int < 0) {
                        JOptionPane.showMessageDialog(frame.getMiFrame(),
                                "Number of plays negative.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    iscorrectproblem = isCorrectProblem(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int);

                    if (!iscorrectproblem) {
                        JOptionPane.showMessageDialog(frame.getMiFrame(),
                                "Problem is incorrect.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
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
                    miPanel.remove(9);
                    table = new SampleGame(FEN.getText() + " w w 2");
                    miPanel.add(table, c);
                    miPanel.revalidate();
                    miPanel.repaint();
                } else {
                    miPanel.remove(9);
                    table = new SampleGame("8/8/8/8/8/8/8/8 w w 2");
                    miPanel.add(table, c);
                    miPanel.revalidate();
                    miPanel.repaint();
                }
            }
        }
    }

    public class Modificateproblem extends JPanel {
        private java.awt.List graphic_list;
        private SampleGame table;
        private JPanel miPanel;
        private Modificateproblem() {
            miPanel = this;
            this.setLayout(new BorderLayout());
            java.util.List<String> problem = new ArrayList<String>();
            try {
                problem = Problem.load_problem_fromBD("BD_USERPROBLEMS");
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            graphic_list = new java.awt.List();
            for (int i = 0; i < problem.size(); ++i) {
                graphic_list.add(problem.get(i));
            }
            graphic_list.setFont(new Font("Serif", Font.PLAIN, 15));
            graphic_list.setMultipleMode(false);
            graphic_list.addMouseListener(new double_click());
            JLabel label = new JLabel();
            label.setText("FEN + Player who start playing + Player who has to achive checkmate + number of plays");
            this.add(label, BorderLayout.BEFORE_FIRST_LINE);
            JPanel panelgraphiclist = new JPanel();
            //panelgraphiclist.setPreferredSize(new Dimension(20,20));
            //panelgraphiclist.add(graphic_list);
            //this.add(graphic_list, BorderLayout.CENTER);

            if (problem.get(0) != null) table = new SampleGame(problem.get(0));
            table.getChessBoardPanel().setPreferredSize(new Dimension(500,500));
            this.add(table, BorderLayout.CENTER);
        }

        private class double_click extends MouseAdapter {
            public void mouseClicked(MouseEvent me) {
                table = new SampleGame(graphic_list.getSelectedItem());
                table.getChessBoardPanel().setPreferredSize(new Dimension(500,500));
                miPanel.remove(2);
                miPanel.add(table, BorderLayout.CENTER);
                miPanel.revalidate();
                miPanel.repaint();
            }
        }
    }
}
