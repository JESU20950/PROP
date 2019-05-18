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
    private JButton Back;
    private ArrayList<String> FENs;
    private java.awt.List graphic_list;
    private SampleGame table;

    public MultipleProblems (FrameProgram t, int n){
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
        graphic_list.addMouseListener(new double_click());
        JPanel panelgraphiclist = new JPanel();
        panelgraphiclist.setSize(400, 400);
        panelgraphiclist.add(graphic_list);
        //this.add(graphic_list, BorderB.CENTER);
        if (problems.get(0) != null) table = new SampleGame(problems.get(0));
        //this.add(table, BorderLayout.SOUTH);
        Back = new JButton("Back");
        Back.setFont(new Font("Serif", Font.PLAIN, 30));
        Back.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.add(graphic_list, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        this.add(Back, c);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            MachinePanel Panel = new MachinePanel(frame);
            frame.getMiFrame().setContentPane(Panel);
            frame.getMiFrame().revalidate();
            frame.getMiFrame().repaint();
        }
    }

    public class double_click extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {

        }
    }
}
