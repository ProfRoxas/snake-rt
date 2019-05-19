package snake.tools;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.enums.EntityTypes;

public class Gui extends JPanel {
    private ImageIcon fruitIcon;
    private ImageIcon groundIcon;
    private ImageIcon snakeIcon;
    private ImageIcon wallIcon;
    private ImageIcon fastIcon;
    private ImageIcon slowIcon;
    private ImageIcon unknownIcon;
    private Logic __gameLogic;
    private int x;
    private int y;

    public Gui(Logic gm) {
        __gameLogic = gm;
        x = (int)gm.getMap().getSize().getX();
        y = (int)gm.getMap().getSize().getY();
        System.out.println(x + " " + y);
        this.setLayout(new GridLayout(y, x, 0, 0));
        try {
            InputStream url = this.getClass().getResourceAsStream("/fruit.png");
            BufferedImage fruitImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/fruit_fast.png");
            BufferedImage fastImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/fruit_slow.png");
            BufferedImage slowImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/what.png");
            BufferedImage unknownImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/ground.PNG");
            BufferedImage groundImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/snake.PNG");
            BufferedImage snakeImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/wall.PNG");
            BufferedImage wallImg = ImageIO.read(url);
            fruitIcon = new ImageIcon(fruitImg);
            groundIcon = new ImageIcon(groundImg);
            snakeIcon = new ImageIcon(snakeImg);
            wallIcon = new ImageIcon(wallImg);
            slowIcon = new ImageIcon(slowImg);
            fastIcon = new ImageIcon(fastImg);
            unknownIcon = new ImageIcon(unknownImg);
            Point point = new Point();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    ImagePanel label = new ImagePanel();
                    point.move(j,i);
                    if(__gameLogic.getMap().get(point) == null){
                        label.changeImage(groundIcon);
                    }else if(   __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEBODY ||
                        __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKETAIL ||
                        __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEHEAD){
                        label.changeImage(snakeIcon);
                    }else if(__gameLogic.getMap().get(point).getType() == EntityTypes.WALL){
                        label.changeImage(wallIcon);
                    } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.BASICFRUIT){
                        label.changeImage(fruitIcon);
                    } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.SPEEDUPFRUIT){
                        label.changeImage(fastIcon);
                    } else{
                        label.changeImage(unknownIcon);
                    }
                    this.add(label, point);
                }
            }
            refreshMap();
        }catch(IOException e){
            System.out.println("Image(s) not found.");
        }
    }

    public void refreshMap(){
        Point point = new Point();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                ImagePanel label = (ImagePanel)this.getComponent(j*x + i);
                point.move(i,j);
                if(__gameLogic.getMap().get(point) == null){
                    label.changeImage(groundIcon);
                }else if(   __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEBODY ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKETAIL ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.FATSNAKEBODY ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEHEAD){
                    label.changeImage(snakeIcon);
                }else if(__gameLogic.getMap().get(point).getType() == EntityTypes.WALL){
                    label.changeImage(wallIcon);
                } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.BASICFRUIT){
                    label.changeImage(fruitIcon);
                } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.SPEEDUPFRUIT){
                    label.changeImage(fastIcon);
                } else{
                    label.changeImage(unknownIcon);
                }
            }
        }
        repaint();
    }
}

class ImagePanel extends JPanel {
    private Image img;
    public void changeImage(ImageIcon bi) {
        img = bi.getImage();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}