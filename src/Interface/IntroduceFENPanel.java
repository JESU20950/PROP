package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;

public class IntroduceFENPanel extends JPanel {
    private FrameProgram main;
    private JTextField FEN;
    private JSpinner number_of_plays;
    private JComboBox player_who_has_to_achive_the_check_mate;
    private JComboBox player_who_starts;
    IntroduceFENPanel(FrameProgram t){
        main = t;
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
    public class double_click extends MouseAdapter {
        public void mouseClicked(MouseEvent me){
            if (me.getClickCount() == 1) {
                if (!iscorrectFen(FEN.getText())) {
                    JOptionPane.showMessageDialog(main.getMiFrame(),
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
                    JOptionPane.showMessageDialog(main.getMiFrame(),
                            "Number of plays negative.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    iscorrectproblem = isCorrectProblem(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (!iscorrectproblem){
                    JOptionPane.showMessageDialog(main.getMiFrame(),
                            "Problem is incorrect.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                            return;
                }
                main.getActual_game().prepareTablewithParameters(FENtext, player_who_start_bool, player_who_has_to_win, number_of_plays_int*2);
                PlayerPanel Panel = new PlayerPanel(main);
                main.getMiFrame().getContentPane().removeAll();
                main.getMiFrame().setContentPane(Panel);
                main.getMiFrame().revalidate();
            }
        }
    }
}
