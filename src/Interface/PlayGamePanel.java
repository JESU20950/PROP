package Interface;

import Data.Player.Player;
import Data.Problem;
import Data.Ranking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayGamePanel extends JPanel {
    private JFrame miFrame;
    private  MainInterface main;
    private java.awt.List graphic_list;
    public PlayGamePanel(MainInterface t){
        main = t;
        miFrame = t.getMiFrame();
        JButton loadButton = new JButton("Load Problem");
        JButton introduceButton = new JButton( "Introduce Problem");
        introduceButton.addActionListener(new introduceListener());
        loadButton.addActionListener(new loadListener());
        loadButton.setPreferredSize(new Dimension(400, 40));
        introduceButton.setPreferredSize(new Dimension(400, 40));
        loadButton.setFont(new Font("Serif", Font.PLAIN, 30));
        introduceButton.setFont(new Font("Serif", Font.PLAIN, 30));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(loadButton,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(introduceButton,c);
    }
    public class introduceListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            IntroduceFENPanel panel = new IntroduceFENPanel(main);
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(panel);
            miFrame.revalidate();
        }
    }
    public class loadListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JPanel Dificulty = new JPanel();
            JButton Hard_Problems = new JButton("Hard Problems");
            JButton Easy_Problems = new JButton( "Easy Problems");
            Hard_Problems.addActionListener(new HardModeListener());
            Easy_Problems.addActionListener(new EasyModeListener());
            Hard_Problems.setPreferredSize(new Dimension(300, 40));
            Easy_Problems.setPreferredSize(new Dimension(300, 40));
            Easy_Problems.setFont(new Font("Serif", Font.PLAIN, 30));
            Hard_Problems.setFont(new Font("Serif", Font.PLAIN, 30));
            Dificulty.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 0;
            Dificulty.add(Easy_Problems,c);
            c.anchor = GridBagConstraints.CENTER;
            c.gridx = 0;
            c.gridy = 1;
            Dificulty.add(Hard_Problems,c);
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(Dificulty);
            miFrame.revalidate();

        }
    }
    public class HardModeListener implements ActionListener {
        public void actionPerformed(ActionEvent evt){
            JPanel panel = new JPanel();
            panel.setLayout( new BorderLayout());
            List<String> problem = new ArrayList<String >();
            try {
                problem = Problem.load_problem_fromBD_Hard_Mode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            graphic_list = new java.awt.List();
            for (int i = 0; i<problem.size(); ++i){
                graphic_list.add(problem.get(i));
            }
            graphic_list.setMultipleMode(false);
            graphic_list.addMouseListener(new double_click());
            JLabel label = new JLabel();
            label.setText("FEN + Player who start playing + Player who has to achive checkmate + number of plays");
            panel.add(label,BorderLayout.BEFORE_FIRST_LINE);
            JPanel panelgraphiclist = new JPanel();
            panelgraphiclist.setSize(400,400);
            panelgraphiclist.add(graphic_list);
            panel.add(graphic_list,BorderLayout.CENTER);
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(panel);
            miFrame.revalidate();
        }

    }
    public class EasyModeListener implements ActionListener {
        public void actionPerformed(ActionEvent evt){
            JPanel panel = new JPanel();
            panel.setLayout( new BorderLayout());
            List<String> problem = new ArrayList<String >();
            try {
                problem = Problem.load_problem_fromBD_Easy_Mode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            graphic_list = new java.awt.List();
            for (int i = 0; i<problem.size(); ++i){
                graphic_list.add(problem.get(i));
            }
            graphic_list.setMultipleMode(false);
            graphic_list.addMouseListener(new double_click());
            JLabel label = new JLabel();
            label.setText("FEN + Player who starts playing + Player who has to achive checkmate + Number of plays");
            panel.add(label,BorderLayout.BEFORE_FIRST_LINE);
            JPanel panelgraphiclist = new JPanel();
            panelgraphiclist.setSize(400,400);
            panelgraphiclist.add(graphic_list);
            panel.add(graphic_list,BorderLayout.CENTER);
            miFrame.getContentPane().removeAll();
            miFrame.setContentPane(panel);
            miFrame.revalidate();
        }

    }
      public class double_click extends MouseAdapter {
          public void mouseClicked(MouseEvent me) {
              if (me.getClickCount() == 2) {
                  PlayerPanel Panel = new PlayerPanel(main);
                  main.getActual_game().prepareTablewithFEN(graphic_list.getSelectedItem());
                  miFrame.getContentPane().removeAll();
                  miFrame.setContentPane(Panel);
                  miFrame.revalidate();
              }
          }
      }
}
