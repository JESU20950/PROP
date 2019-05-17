package Interface;


import Domain.Game;
import Domain.Player.Player;
import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Domain.Problem.isCorrectProblem;
import static Domain.Problem.iscorrectFen;

public class ProblemPanel extends JPanel {
    private FrameProgram frame;
    private java.awt.List graphic_list;
    private SampleGame table;

    public ProblemPanel(FrameProgram t) {
        frame = t;
        JButton loadButton = new JButton("Load Problem");
        JButton goBackButton = new JButton("Back");
        //JButton introduceButton = new JButton("Introduce your Problem");
        //introduceButton.addActionListener(new introduceListener());
        loadButton.addActionListener(new loadListener());
        goBackButton.addActionListener(new goBackListener());
        loadButton.setPreferredSize(new Dimension(400, 40));
        goBackButton.setPreferredSize(new Dimension(400, 40));
        //introduceButton.setPreferredSize(new Dimension(400, 40));
        loadButton.setFont(new Font("Serif", Font.PLAIN, 30));
        goBackButton.setFont(new Font("Serif", Font.PLAIN, 30));
        //introduceButton.setFont(new Font("Serif", Font.PLAIN, 30));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(loadButton, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        //this.add(introduceButton, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        this.add(goBackButton, c);
    }
/*
    public class introduceListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            IntroduceFENPanel panel = new IntroduceFENPanel(frame);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }
    }
*/
    private class goBackListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PlayerPanel Panel = new PlayerPanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    private class goBack2Listener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ProblemPanel Panel = new ProblemPanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    public class loadListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JPanel Dificulty = new JPanel();
            JButton Hard_Problems = new JButton("Hard Problems");
            JButton Easy_Problems = new JButton("Easy Problems");
            JButton User_Problems = new JButton("User Problems");
            JButton Back = new JButton("Back");
            Hard_Problems.addActionListener(new ModeListener());
            Easy_Problems.addActionListener(new ModeListener());
            User_Problems.addActionListener(new ModeListener());
            Back.addActionListener(new goBack2Listener());
            Hard_Problems.setPreferredSize(new Dimension(300, 40));
            Easy_Problems.setPreferredSize(new Dimension(300, 40));
            User_Problems.setPreferredSize(new Dimension(300, 40));
            Back.setPreferredSize(new Dimension(300, 40));
            Easy_Problems.setFont(new Font("Serif", Font.PLAIN, 30));
            Hard_Problems.setFont(new Font("Serif", Font.PLAIN, 30));
            User_Problems.setFont(new Font("Serif", Font.PLAIN, 30));
            Back.setFont(new Font("Serif", Font.PLAIN, 30));
            Dificulty.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 0;
            Dificulty.add(Easy_Problems, c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 1;
            Dificulty.add(Hard_Problems, c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 2;
            Dificulty.add(User_Problems, c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 3;
            Dificulty.add(Back, c);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Dificulty);
            frame.getMiFrame().revalidate();
        }
    }

    private class ModeListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            List<String> problem = new ArrayList<String>();
            try {
                JButton src = (JButton) evt.getSource();
                if (src.getLabel().equals("Hard Problems")) {
                    problem = Problem.load_problem_fromBD("BD_HARDMODE");
                } else if (src.getLabel().equals("Easy Problems")) {
                    problem = Problem.load_problem_fromBD("BD_EASYMODE");
                } else problem = Problem.load_problem_fromBD("BD_USERPROBLEMS");


            } catch (IOException e) {
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
            panel.add(label, BorderLayout.BEFORE_FIRST_LINE);
            JPanel panelgraphiclist = new JPanel();
            panelgraphiclist.setSize(400, 400);
            panelgraphiclist.add(graphic_list);
            panel.add(graphic_list, BorderLayout.CENTER);


            if (problem.get(0) != null) table = new SampleGame(problem.get(0));
            panel.add(table, BorderLayout.SOUTH);

            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(panel);
            frame.getMiFrame().revalidate();
        }

    }

    private class double_click extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {
                frame.getActual_game().prepareTablewithFEN(graphic_list.getSelectedItem());
                GameInterface Panel = new GameInterface(frame);
                frame.getMiFrame().getContentPane().removeAll();
                frame.getMiFrame().getContentPane().add(Panel);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            } else {
                table = new SampleGame(graphic_list.getSelectedItem());
                frame.getMiFrame().getContentPane().remove(2);
                frame.getMiFrame().getContentPane().add(table, BorderLayout.SOUTH);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }
    }
}
/*
    private class IntroduceFENPanel extends JPanel {
        private FrameProgram frame;
        private JTextField FEN;
        private JSpinner number_of_plays;
        private JComboBox player_who_has_to_achive_the_check_mate;
        private JComboBox player_who_starts;
        IntroduceFENPanel(FrameProgram t){
            frame = t;
            FEN= new JTextField();
            FEN.setPreferredSize( new Dimension( 400, 30 ) );
            player_who_starts=new JComboBox<String>();

            player_who_starts.addItem("White");
            player_who_starts.addItem("Black");
            player_who_has_to_achive_the_check_mate= new JComboBox<String>();
            number_of_plays = new JSpinner();
            number_of_plays.setPreferredSize( new Dimension( 50, 30 ) );
            player_who_has_to_achive_the_check_mate.addItem("White");
            player_who_has_to_achive_the_check_mate.addItem("Black");
            JButton introduceProblem = new JButton("Introduce Problem");
            introduceProblem.addMouseListener(new double_click());
            FEN.setFont(new Font("Serif", Font.PLAIN, 20));
            player_who_starts.setFont(new Font("Serif", Font.PLAIN, 20));
            player_who_has_to_achive_the_check_mate.setFont(new Font("Serif", Font.PLAIN, 20));
            player_who_starts.setFont(new Font("Serif", Font.PLAIN, 20));
            introduceProblem.setFont(new Font("Serif", Font.PLAIN, 30));
            number_of_plays.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel label1 = new JLabel("FEN");
            JLabel label2 =new JLabel("Player who starts playings");
            JLabel label3 = new JLabel("Player who has to achive checkmate");
            JLabel label4 =  new JLabel("Number of plays");
            label1.setFont(new Font("Serif", Font.PLAIN, 20));
            label2.setFont(new Font("Serif", Font.PLAIN, 20));
            label3.setFont(new Font("Serif", Font.PLAIN, 20));
            label4.setFont(new Font("Serif", Font.PLAIN, 20));
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 1;
            this.add(FEN,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 3;
            this.add(player_who_has_to_achive_the_check_mate,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 5;
            this.add(player_who_starts,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 7;
            this.add(number_of_plays,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 10;
            this.add(introduceProblem,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 0;
            this.add(label1,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 2;
            this.add(label2,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 4;
            this.add(label3,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 6;
            this.add(label4,c);

        }
        private class double_click extends MouseAdapter {
            public void mouseClicked(MouseEvent me){
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

                    if (!iscorrectproblem){
                        JOptionPane.showMessageDialog(frame.getMiFrame(),
                                "Problem is incorrect.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    frame.getActual_game().prepareTablewithParameters(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int);
                    PlayerPanel Panel = new PlayerPanel(frame);
                    frame.getMiFrame().getContentPane().removeAll();
                    frame.getMiFrame().setContentPane(Panel);
                    frame.getMiFrame().revalidate();
                }
            }
        }
    }
}

*/