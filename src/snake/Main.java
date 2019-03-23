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
    private int __fps = 10;
    private Runnable __sleepertimer;
    private Runnable __timertask;

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
        // = new Thread();
        __timertask = new Runnable(){
            
            @Override
            public void run() {
                //TODO: Update GameLogic
                System.out.println("Logic Goes Here!");
                __sleepertimer.run();
            }
        };
        __sleepertimer = new Runnable(){
            private int frame = 0;
            private long lasttime = 0;
            @Override
            public void run() {
                frame++;
                long newtime = System.nanoTime();
                long delta = newtime - lasttime;
                float deltams = delta/1000000.0f;
                //System.out.println("Hello World "+frame+", frametime: "+deltams+"ms");
                long target = 1000/__fps;
                if(deltams < target) {
                    try{
                        System.out.println("Sleeping for "+(target-deltams)+"ms (target: "+target+"ms");
                        Thread.sleep((target*1000000-delta)/1000000);
                    }
                    catch(InterruptedException e) {System.out.println("InterruptedException");}
                }
                newtime = System.nanoTime();
                System.out.println("Hello World "+frame+", frametime: "+(newtime-lasttime)/1000000+"ms");
                lasttime = newtime;
                EventQueue.invokeLater(__timertask);
            }
        };
        EventQueue.invokeLater(__timertask);
        //__timer.schedule( __timertask, 1000/__fps);
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