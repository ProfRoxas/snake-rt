package snake;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import snake.tools.Gui;
import snake.tools.KeyEventListener;
import snake.tools.Logic;
import snake.tools.Settings;

class Main extends JFrame {
    private Logic __gameLogic;
    private static Timer __timer;
    private int __fps = 10;
    private Runnable __timertask;

    public Main() {
        System.out.println("Test");
        initUI();
    }

    private void initUI() {
        // TODO: Add the Actual Canvas
        // add(__canvas);

        setTitle("Snake RT");
        setSize(Settings.getScreen());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        __gameLogic = new Logic();
        
        addKeyListener(new KeyEventListener(__gameLogic));
        __timer = new Timer();
        // = new Thread();
        __timertask = new Runnable(){
            
            @Override
            public void run() {
                //Add GUI here for Thread Safety
                // TODO: fix GUI update
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

        Gui g = new Gui(__gameLogic);
        this.add(g);
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