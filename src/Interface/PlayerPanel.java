package Interface;



import Domain.Player.Human;
import Domain.Player.Machine1;
import Domain.Player.Machine2;
import Domain.Player.Player;
import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private FrameProgram frame;
    private JComboBox<String> Player1;
    private JComboBox<String> Player2;
    private java.awt.List graphic_list;
    private SampleGame table;

    PlayerPanel(FrameProgram t){
        frame = t;
        this.setLayout(new GridBagLayout());
        Player1 = new JComboBox<String>();
        Player2 = new JComboBox<String>();
        Player1.addItem("Human");
        Player2.addItem("Human");
        Player1.addItem("Machine1");
        Player2.addItem("Machine1");
        Player1.addItem("Machine2");
        Player2.addItem("Machine2");
        Player1.setFont(new Font("Serif", Font.PLAIN, 30));
        Player2.setFont(new Font("Serif", Font.PLAIN, 30));
        JLabel label = new JLabel();
        label.setText("VS");
        label.setFont(new Font("Serif", Font.PLAIN, 40));

        GridBagConstraints c = new GridBagConstraints();
        JButton button = new JButton("PLAY");
        JButton buttonGoBack = new JButton("Back");
        button.addActionListener(new playaction());
        buttonGoBack.addActionListener(new backaction());
        button.setFont(new Font("Serif", Font.PLAIN, 30));
        buttonGoBack.setFont(new Font("Serif", Font.PLAIN, 30));
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(Player1,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 0;
        this.add(Player2,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        this.add(label,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        this.add(button,c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        this.add(buttonGoBack, c);
    }

    private class backaction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            Cover Panel = new Cover(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    private class backaction2 implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PlayerPanel Panel = new PlayerPanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    private class ModeListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
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

    private class easyProblemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class hardProblemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class userProblemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private void machine_vs_machine() {
        JPanel CPU_vs_CPU = new JPanel();
        JLabel label1 = new JLabel("Select the number of evaluations");
        label1.setFont(new Font("Serif", Font.PLAIN, 30));
        label1.setPreferredSize(new Dimension(500, 40));
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Serif", Font.PLAIN, 30));
        Back.setPreferredSize(new Dimension(100, 50));
        Back.addActionListener(new backaction2());
        JButton Easy_problems = new JButton("Easy Problems");
        Easy_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        Easy_problems.setPreferredSize(new Dimension(300, 50));
        Easy_problems.addActionListener(new ModeListener());
        JButton Hard_problems = new JButton("Hard Problems");
        Hard_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        Hard_problems.setPreferredSize(new Dimension(300, 50));
        Hard_problems.addActionListener(new ModeListener());
        JButton User_problems = new JButton("User Problems");
        User_problems.setFont(new Font("Serif", Font.PLAIN, 30));
        User_problems.setPreferredSize(new Dimension(300, 50));
        User_problems.addActionListener(new ModeListener());
        CPU_vs_CPU.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        CPU_vs_CPU.add(label1, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        CPU_vs_CPU.add(Easy_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        CPU_vs_CPU.add(Hard_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 3;
        CPU_vs_CPU.add(User_problems, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 4;
        CPU_vs_CPU.add(Back, c);
        frame.getMiFrame().getContentPane().removeAll();
        frame.getMiFrame().setContentPane(CPU_vs_CPU);
        frame.getMiFrame().revalidate();
        frame.getMiFrame().repaint();
    }

    private class playaction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ProblemPanel Panel = new ProblemPanel(frame);
            boolean machine1 = false;
            boolean machine2 = false;
            if (Player1.getSelectedItem().equals("Human")){
                frame.getActual_game().setPlayer1(new Human());
            }else if(Player1.getSelectedItem().equals("Machine1")){
                machine1 = true;
                frame.getActual_game().setPlayer1(new Machine1());
            }else if (Player1.getSelectedItem().equals("Machine2")){
                machine1 = true;
                frame.getActual_game().setPlayer1(new Machine2());
            }
            if (Player2.getSelectedItem().equals("Human")){
                frame.getActual_game().setPlayer2(new Human());
            }else if(Player2.getSelectedItem().equals("Machine1")){
                machine2 = true;
                frame.getActual_game().setPlayer2(new Machine1());
            }else if (Player2.getSelectedItem().equals("Machine2")) {
                machine2 = true;
                frame.getActual_game().setPlayer2(new Machine2());
            }
            frame.getActual_game().getPlayer1().setColor(true);
            frame.getActual_game().getPlayer2().setColor(false);
            if (machine1 && machine2) machine_vs_machine();
            else {
                frame.getMiFrame().getContentPane().removeAll();
                frame.getMiFrame().getContentPane().add(Panel);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }
    }

}
