package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainInterface extends JFrame {
    private JFrame miFrame;
    private String Problem;


    public MainInterface() {
        // Se instancian tres botones con textos indicando lo que
        // hacen cuando se pulse sobre ellos

        miFrame = new JFrame("ChessGameUPC");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        miFrame.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        miFrame.setVisible(true);
        miFrame.getContentPane().removeAll();
        System.out.println("hoolla");
        miFrame.setContentPane(new Cover(this));
        miFrame.revalidate();
        // Instancia y registra un objeto WindowListener sobre el objeto
        // Frame para terminar el programa cuando el usuario haga click
        // con el raton sobre el boton de cerrar la ventana que se
        // coloca sobre el objeto Frame
        miFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                // Concluye la aplicacion cuando el usuario cierra la
                // ventana
                System.exit(0);
            }
        });

    }

    public JFrame getMiFrame() {
        return miFrame;
    }

    public void setMiFrame(JFrame miFrame) {
        this.miFrame = miFrame;
    }
}
