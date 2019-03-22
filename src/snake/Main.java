package snake;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import snake.tools.KeyEventListener;
import snake.tools.Logic;

class Main extends JFrame {
    private Canvas __canvas;
    private Logic __gameLogic;
    private static Timer __timer;
    private int __fps = 60;

    public Main() {
        System.out.println("Test");
        initUI();
    }
    private void initUI() {
        //TODO: Add the Actual Canvas
        //add(__canvas);

        setTitle("Snake RT");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        __gameLogic = new Logic(this);
        addKeyListener(new KeyEventListener(/* passing gameLogic later*/));
        __timer = new Timer();
        Main window = this;
        TimerTask __timertask = new TimerTask(){
            private int frame = 0;
            private long lasttime = 0;
            @Override
            public void run() {
                frame++;
                long newtime = System.nanoTime();
                window.setTitle("Hello World "+frame+", frametime: "+(newtime-lasttime)/1000000.0f+"ms");
                lasttime = newtime;

                //TODO: Update GameLogic
            }
        };
        __timer.scheduleAtFixedRate( __timertask, 0, 1000/__fps);
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