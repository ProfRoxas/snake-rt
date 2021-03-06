package snake;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import snake.tools.Gui;
import snake.tools.KeyEventListener;
import snake.tools.Logic;
import snake.tools.Menu;
import snake.tools.Settings;

public class Main extends JFrame {
    private Logic __gameLogic;
    private static Timer __logicTimer;
    private int __fps = 10;
    private Runnable __guiTask;
    private Gui g;

    public Main() {
        System.out.println("Test");
        initUI();
    }

    private void initUI() {
        // TODO: Add the Actual Canvas
        // add(__canvas);

        setTitle("Snake RT");
        //setSize(Settings.getScreen());
        //setMinimumSize(Settings.getScreen());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        __logicTimer = new Timer();
        // = new Thread();
        __guiTask = new Runnable(){
            
            @Override
            public void run() {
                //Add GUI here for Thread Safety
                // TODO: fix GUI update
                g.refreshMap();
                //System.out.println("Updating GUI");
            }
        };

        Menu m = new Menu();
        this.add(m);
        this.pack();
    }
    public void setGame(Gui g, Logic l) {
        this.g = g;
        __gameLogic = l;
        startTimer(1.0d);
        addKeyListener(new KeyEventListener(__gameLogic));
        pack();

        int x = Settings.getMapSize().x;
        int y = Settings.getMapSize().y;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double width = (screen.getWidth()*0.8)/x;
        double height = (screen.getHeight()*0.8)/y;
        Double blocksize = width<height?width:height;
        Dimension size = new Dimension(x*blocksize.intValue(), y*blocksize.intValue());
        setPreferredSize(size);
        
        setSize(size);
        setLocationRelativeTo(null);
    }
    
    private void startTimer(double rate) {
        Double delay = (1000/Settings.getSpeed()) / rate;
        if(delay < 1.0d) delay = 1.0d; //! Can't schedule thread faster than 1ms
        __logicTimer.scheduleAtFixedRate(new TimerTask() {
            double newRate = rate;
            @Override
            public void run() {
                if (__gameLogic == null) {
                    //no use for thread if no logic, it starts anew when new logic is created
                    this.cancel();
                    return;}
                if(rate != newRate) {
                    this.cancel();
                    startTimer(newRate);
                }
                newRate = __gameLogic.update();
                //newRate = rate/1.1d;   //? Tested for acceleration and proper speed change
                if(newRate < 0) { //-1.0 -> Game over
                    this.cancel();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Player Name: ").append(Settings.getName()).append("\n");
                    sb.append("Score: ").append(__gameLogic.getScore());

                    JOptionPane.showMessageDialog(Main.this, sb.toString(), "Game Over", JOptionPane.PLAIN_MESSAGE);
                    Main.this.remove(Main.this.g);
                    Main.this.add(new Menu());
                    Main.this.setPreferredSize(null);
                    Main.this.repaint();
                    Main.this.validate();
                    Main.this.pack();
                }
                System.out.println(newRate);
                //GUI Schedule for Thread Safety
                EventQueue.invokeLater(__guiTask);
            }

        }, delay.longValue(), delay.longValue());
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