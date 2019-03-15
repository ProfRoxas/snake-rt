package snake.tools;

import java.awt.event.*;

public class KeyEventListener implements KeyListener{

    /** Handle the key pressed event.*/
    public void keyPressed(KeyEvent e) {
        int __code = e.getKeyCode();
            if (__code == KeyEvent.VK_LEFT || __code == KeyEvent.VK_RIGHT 
                || __code == KeyEvent.VK_UP || __code == KeyEvent.VK_DOWN) {
                //TODO: move snake;
            }
    }

    /** Handle the key released event. */
    public void keyReleased(KeyEvent e){}

    /** Handle the key typed event. */
    public void keyTyped(KeyEvent e){}
}