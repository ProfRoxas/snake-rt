package snake;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
        setSize(Settings.getScreen());
        setMinimumSize(Settings.getScreen());
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
                    Main.this.remove(Main.this.g);
                    JPanel p = new JPanel(new GridLayout(4,1));
                    p.add(new JLabel("Game over"));
                    p.add(new JLabel("Player Name: "+Settings.getName()));
                    p.add(new JLabel("Score: "+__gameLogic.getScore()));
                    JButton b = new JButton("Return to Menu");
                    b.addActionListener(new ActionListener(){
                    
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Main.this.remove(p);
                            Main.this.add(new Menu());
                            Main.this.repaint();
                            Main.this.validate();
                        }
                    });
                    p.add(b);
                    Main.this.add(p);
                    Main.this.repaint();
                    Main.this.validate();
                    this.cancel();
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