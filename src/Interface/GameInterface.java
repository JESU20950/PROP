package Interface;

import Data.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GameInterface extends JPanel {
    Game actual_game;
    GameInterface(MainInterface t){
        actual_game = t.getActual_game();
    }


}
