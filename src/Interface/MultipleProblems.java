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
import java.util.List;

public class MultipleProblems extends JPanel implements ActionListener {
    private FrameProgram frame;
    private JButton Simulate_Game;
    private JButton Back;
    private java.awt.List graphic_list;
    private SampleGame table;
    private JLabel label1;
    private ArrayList<String> problems_to_simulate;
    private boolean machine1;
    private boolean machine2;

    public MultipleProblems (FrameProgram t, int n, boolean CPU1, boolean CPU2){
        machine1 = CPU1;
        machine2 = CPU2;
        problems_to_simulate = new ArrayList<String>();
        frame = t;
        this.setLayout(new GridBagLayout());
        graphic_list = new java.awt.List();
        List<String> problems = new ArrayList<String>();
        try {
            if (n == 0) problems = Problem.load_problem_fromBD("BD_EASYMODE");
            if (n == 1) problems = Problem.load_problem_fromBD("BD_HARDMODE");
            if (n == 2) problems = Problem.load_problem_fromBD("BD_USERPROBLEMS");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        graphic_list = new java.awt.List();
        for (int i = 0; i < problems.size(); ++i) {
            graphic_list.add(problems.get(i));
        }
        graphic_list.setFont(new Font("Serif", Font.PLAIN, 15));
        graphic_list.setMultipleMode(false);
        graphic_list.addMouseListener(new showFEN());
        JPanel panelgraphiclist = new JPanel();
        panelgraphiclist.setSize(400, 400);
        panelgraphiclist.add(graphic_list);
        if (problems.get(0) != null) table = new SampleGame(problems.get(0));
        Back = new JButton("Back");
        Back.setFont(new Font("Serif", Font.PLAIN, 30));
        Back.addActionListener(this);
        Simulate_Game = new JButton("Simulate Game");
        Simulate_Game.setFont(new Font("Serif", Font.PLAIN, 30));
        Simulate_Game.addActionListener(this);
        if (problems.get(0) != null) table = new SampleGame(problems.get(0));
        label1 = new JLabel("Problems selected(index): ");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(graphic_list, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 3;
        this.add(Back, c);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        this.add(Simulate_Game, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        this.add(table, c);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        this.add(label1, c);
    }

    private class showFEN extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                frame.getMiFrame().getContentPane().remove(table);
                table = new SampleGame(graphic_list.getSelectedItem());
                GridBagConstraints c = new GridBagConstraints();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 2;
                frame.getMiFrame().getContentPane().add(table, c);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
            if (e.getClickCount() == 2) {
                label1.setText(label1.getText() + graphic_list.getSelectedIndex() + " ");
                problems_to_simulate.add(graphic_list.getSelectedItem());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            MachinePanel Panel = new MachinePanel(frame, machine1, machine2);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
        if (e.getSource() == Simulate_Game) {
            SimulateGame Panel = new SimulateGame(frame, problems_to_simulate, machine1, machine2);
            frame.getMiFrame().getContentPane().removeAll();
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }
}
