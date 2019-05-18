package Interface;

import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class RankingPanel extends JPanel {
    private FrameProgram frame;
    private java.awt.List graphic_list;
    private SampleRank table;
    public RankingPanel(FrameProgram t){
            frame = t;
            JButton loadButton = new JButton("Choose Problem");
            JButton goBackButton = new JButton("Back");
            //JButton introduceButton = new JButton("Introduce your Problem");
            //introduceButton.addActionListener(new introduceListener());
            loadButton.addActionListener(new RankingPanel.loadListener());
            goBackButton.addActionListener(new RankingPanel.goBackListener());
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
                Cover Panel = new Cover(frame);
                frame.getMiFrame().setContentPane(Panel);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }

        private class goBack2Listener implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                RankingPanel Panel = new RankingPanel(frame);
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
                Hard_Problems.addActionListener(new RankingPanel.ModeListener());
                Easy_Problems.addActionListener(new RankingPanel.ModeListener());
                User_Problems.addActionListener(new RankingPanel.ModeListener());
                Back.addActionListener(new RankingPanel.goBack2Listener());
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
                java.util.List<String> problem = new ArrayList<String>();
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
                graphic_list.setFont(new Font("Serif", Font.PLAIN, 30));
                graphic_list.setMultipleMode(false);
                graphic_list.addMouseListener(new RankingPanel.double_click());
                JLabel label = new JLabel();
                label.setText("FEN + Player who start playing + Player who has to achive checkmate + number of plays");
                JPanel panelgraphiclist = new JPanel();
                panelgraphiclist.setSize(400, 400);
                panelgraphiclist.add(graphic_list);

                JButton Back = new JButton("Back");
                Back.addActionListener(new RankingPanel.goBack2Listener());
                Back.setPreferredSize(new Dimension(300, 40));
                Back.setFont(new Font("Serif", Font.PLAIN, 30));


                if (problem.get(0) != null) table = new SampleRank(problem.get(0));
                panel.add(table, BorderLayout.CENTER);

                panel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 0;
                panel.add(label, c);
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 1;
                panel.add(panelgraphiclist, c);
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 3;
                panel.add(Back, c);
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 2;
                panel.add(table, c);

                frame.getMiFrame().getContentPane().removeAll();
                frame.getMiFrame().setContentPane(panel);
                frame.getMiFrame().revalidate();
            }
        }

        private class double_click extends MouseAdapter {
            public void mouseClicked(MouseEvent me) {
                table = new SampleRank(graphic_list.getSelectedItem());
                frame.getMiFrame().getContentPane().remove(3);
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 2;
                frame.getMiFrame().getContentPane().add(table, c);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }
}
