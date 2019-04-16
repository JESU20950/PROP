package Interface;

import Data.Player.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    MainInterface main;
    JComboBox<String> Player1;
    JComboBox<String> Player2;
    PlayerPanel(MainInterface t){
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

}
