package snake;

import java.awt.Canvas;
import java.awt.EventQueue;

import javax.swing.JFrame;

import snake.tools.KeyEventListener;
import snake.tools.Logic;

class Main extends JFrame {
    private Canvas __canvas;
    private Logic __gameLogic;

    public Main() {
        System.out.println("Test");
        initUI();
    }
    private void initUI() {
        //add(__canvas);

        setTitle("Snake RT");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        __gameLogic = new Logic();
        addKeyListener(new KeyEventListener(/* passing gameLogic later*/));
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