package snake.tools;

import java.awt.event.*;
import snake.tools.Logic;

public class KeyEventListener implements KeyListener{

    private Logic logic;

    public KeyEventListener(Logic logic){
        this.logic = logic;
    }

    /** Handle the key pressed event.*/
    public void keyPressed(KeyEvent e) {
        int __code = e.getKeyCode();
            if (__code == KeyEvent.VK_LEFT || __code == KeyEvent.VK_RIGHT 
                || __code == KeyEvent.VK_UP || __code == KeyEvent.VK_DOWN) {
                logic.setNextDirection(__code);
            }else if(__code == KeyEvent.VK_ENTER){
                logic.setPaused(false);
            }
            else if(__code == KeyEvent.VK_ESCAPE) {
                logic.setExit(true);
            }
    }

    /** Handle the key released event. */
    public void keyReleased(KeyEvent e){}

    /** Handle the key typed event. */
    public void keyTyped(KeyEvent e){}
}