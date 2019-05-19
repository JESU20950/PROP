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
    private SampleGame table;

    public PlayerPanel(FrameProgram t){
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

    private void machine_vs_machine(int machine1, int machine2) {
        boolean m1 = machine1 == 2;
        boolean m2 = machine2 == 2;
        MachinePanel Panel = new MachinePanel(frame, m1, m2);
        frame.getMiFrame().getContentPane().removeAll();
        frame.getMiFrame().setContentPane(Panel);
        frame.getMiFrame().revalidate();
        frame.getMiFrame().repaint();
    }

    private class playaction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            ProblemPanel Panel = new ProblemPanel(frame);
            int machine1 = 0;
            int machine2 = 0;
            if (Player1.getSelectedItem().equals("Human")){
                frame.getActual_game().setPlayer1(new Human());
            }else if(Player1.getSelectedItem().equals("Machine1")){
                machine1 = 1;
                frame.getActual_game().setPlayer1(new Machine1());
            }else if (Player1.getSelectedItem().equals("Machine2")){
                machine1 = 2;
                frame.getActual_game().setPlayer1(new Machine2());
            }
            if (Player2.getSelectedItem().equals("Human")){
                frame.getActual_game().setPlayer2(new Human());
            }else if(Player2.getSelectedItem().equals("Machine1")){
                machine2 = 1;
                frame.getActual_game().setPlayer2(new Machine1());
            }else if (Player2.getSelectedItem().equals("Machine2")) {
                machine2 = 2;
                frame.getActual_game().setPlayer2(new Machine2());
            }
            frame.getActual_game().getPlayer1().setColor(true);
            frame.getActual_game().getPlayer2().setColor(false);
            if (machine1 != 0 && machine2 != 0) machine_vs_machine(machine1, machine2);
            else {
                frame.getMiFrame().getContentPane().removeAll();
                frame.getMiFrame().getContentPane().add(Panel);
                frame.getMiFrame().revalidate();
                frame.getMiFrame().repaint();
            }
        }
    }

}
