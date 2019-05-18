package Interface;

import Domain.Problem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class SampleRank extends JPanel{
    private JPanel RankPanel;
    private List<String> ranks;
    private java.awt.List ranks_list;
    public SampleRank(String FEN){
        ranks_list = new java.awt.List();
        try {
            ranks = Problem.marks_of_problem(FEN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ranks_list.add("TOP PLAYERS:");
        if(ranks.size()==0) {
            ranks_list.add("No one played!");
            ranks_list.add("Your chance to be RANK1!");
        }
        else {
            for (int i = 0; i < ranks.size(); i++) {
                ranks_list.add((i + 1) + ": " + ranks.get(i));
            }
        }
        paint_rank();
    }
    public void paint_rank() {
        /*
        RankPanel = new JPanel();
        RankPanel.setPreferredSize(new Dimension(7 * 80, 7 * 80));
        ranks_list.setFont(new Font("Serif", Font.PLAIN, 15));
        ranks_list.setMultipleMode(false);
        RankPanel.add(ranks_list, BorderLayout.CENTER);
        this.add(RankPanel, BorderLayout.SOUTH);
        */
        RankPanel = new JPanel();
        ranks_list.setFont(new Font("Serif", Font.PLAIN, 50));
        ranks_list.setMultipleMode(false);
        JPanel panelgraphiclist = new JPanel();
        panelgraphiclist.setSize(1000000000  , 1000000000);
        panelgraphiclist.add(ranks_list);
        this.add(ranks_list, BorderLayout.CENTER);
    }
}
