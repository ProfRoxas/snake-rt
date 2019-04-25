package snake;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.tools.KeyEventListener;
import snake.tools.Logic;
import snake.tools.Settings;
import snake.enums.EntityTypes;

class Main extends JFrame {
    private Canvas __canvas;
    private Logic __gameLogic;
    private static Timer __timer;
    private int __fps = 10;
    private Runnable __sleepertimer;
    private Runnable __timertask;
    private BufferedImage fruitImg;
    private BufferedImage groundImg;
    private BufferedImage snakeImg;
    private BufferedImage wallImg;

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
        this.setLayout(new GridLayout(10, 10, 0, 0));
        try {
            URL url = this.getClass().getResource("./../fruit.png");
            fruitImg = ImageIO.read(url);
            url = this.getClass().getResource("./../ground.png");
            groundImg = ImageIO.read(url);
            url = this.getClass().getResource("./../snake.png");
            snakeImg = ImageIO.read(url);
            url = this.getClass().getResource("./../wall.png");
            wallImg = ImageIO.read(url);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    JLabel label = new JLabel();
                    if(__gameLogic.getMap().get(new Point(i,j)) == null){
                        label = new JLabel(new ImageIcon(groundImg));
                    }else if(   __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKEBODY ||
                        __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKETAIL ||
                        __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.FATSNAKEBODY ||
                        __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKEHEAD){
                        label = new JLabel(new ImageIcon(snakeImg));
                    }else if(__gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.WALL){
                        label = new JLabel(new ImageIcon(wallImg));
                    } else if(__gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.BASICFRUIT){
                        label = new JLabel(new ImageIcon(fruitImg));
                    }
                    this.add(label);
                }
            }
        }catch(IOException e){
            System.out.println("Image(s) not found.");
        }
        this.pack();
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
    }

    public void refreshMap(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel label = (JLabel)this.getComponentAt(i, j);
                if(__gameLogic.getMap().get(new Point(i,j)) == null){
                    label = new JLabel(new ImageIcon(groundImg));
                }else if(   __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKEBODY ||
                    __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKETAIL ||
                    __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.FATSNAKEBODY ||
                    __gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.SNAKEHEAD){
                    label = new JLabel(new ImageIcon(snakeImg));
                }else if(__gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.WALL){
                    label = new JLabel(new ImageIcon(wallImg));
                } else if(__gameLogic.getMap().get(new Point(i,j)).getType() == EntityTypes.BASICFRUIT){
                    label = new JLabel(new ImageIcon(fruitImg));
                }
            }
        }
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