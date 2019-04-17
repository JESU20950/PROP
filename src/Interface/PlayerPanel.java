package Interface;

import Data.Player.Human;
import Data.Player.Machine1;
import Data.Player.Machine2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanel extends JPanel {
    FrameProgram main;
    JComboBox<String> Player1;
    JComboBox<String> Player2;
    PlayerPanel(FrameProgram t){
        main = t;
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
        button.addActionListener(new playaction());
        button.setFont(new Font("Serif", Font.PLAIN, 30));
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

    }
    private class playaction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            GameInterface Panel = new GameInterface(main);
            if (Player1.getSelectedItem() == "Human"){
                main.getActual_game().setPlayer1(new Human());
            }else if(Player1.getSelectedItem() == "Machine1"){
                main.getActual_game().setPlayer1(new Machine1());
            }else if (Player1.getSelectedItem() == "Machine2"){
                main.getActual_game().setPlayer1(new Machine2());
            }
            if (Player2.getSelectedItem() == "Human"){
                main.getActual_game().setPlayer2(new Human());
            }else if(Player2.getSelectedItem() == "Machine1"){
                main.getActual_game().setPlayer2(new Machine1());
            }else if (Player2.getSelectedItem() == "Machine2") {
                main.getActual_game().setPlayer2(new Machine2());
            }
            main.getActual_game().getPlayer1().setColor(true);
            main.getActual_game().getPlayer2().setColor(false);
            main.getMiFrame().getContentPane().removeAll();
            main.getMiFrame().setContentPane(Panel);
            main.getMiFrame().revalidate();
        }
    }

}
