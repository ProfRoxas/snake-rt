package snake;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import snake.tools.Gui;
import snake.tools.KeyEventListener;
import snake.tools.Logic;
import snake.tools.Menu;
import snake.tools.Settings;

class Main extends JFrame {
    private Logic __gameLogic;
    private static Timer __timer;
    private int __fps = 10;
    private Runnable __timertask;
    private Gui g;

    public Main() {
        System.out.println("Test");
        initUI();
    }

    private void initUI() {
        // TODO: Add the Actual Canvas
        // add(__canvas);

        setTitle("Snake RT");
        setSize(Settings.getScreen());
        setMinimumSize(Settings.getScreen());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        __gameLogic = new Logic();
        g = new Gui(__gameLogic);
        addKeyListener(new KeyEventListener(__gameLogic));
        __timer = new Timer();
        // = new Thread();
        __timertask = new Runnable(){
            
            @Override
            public void run() {
                //Add GUI here for Thread Safety
                // TODO: fix GUI update
                g.refreshMap();
                //System.out.println("Updating GUI");
            }
        };
        __timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                __gameLogic.update();
                //GUI Schedule for Thread Safety
                EventQueue.invokeLater(__timertask);
            }

        }, 1000, 1000/__fps);

        Menu m = new Menu(g, __gameLogic);
        this.add(m);
        this.pack();
    }

    

    public static void main(String[] args) {
        new Main();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}