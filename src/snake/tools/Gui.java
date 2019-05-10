package snake.tools;

import java.awt.GridLayout;
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
    private Logic __gameLogic;

    public Gui(Logic gm) {
        __gameLogic = gm;
        this.setLayout(new GridLayout(10, 10, 0, 0));
        try {
            InputStream url = this.getClass().getResourceAsStream("/fruit.png");
            BufferedImage fruitImg = ImageIO.read(url);
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
            Point point = new Point();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    JLabel label = new JLabel();
                    point.move(j,i);
                    if(__gameLogic.getMap().get(point) == null){
                        label.setIcon(groundIcon);
                    }else if(   __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEBODY ||
                        __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKETAIL ||
                        __gameLogic.getMap().get(point).getType() == EntityTypes.FATSNAKEBODY ||
                        __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEHEAD){
                        label.setIcon(snakeIcon);
                    }else if(__gameLogic.getMap().get(point).getType() == EntityTypes.WALL){
                        label.setIcon(wallIcon);
                    } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.BASICFRUIT){
                        label.setIcon(fruitIcon);
                    }
                    this.add(label);
                }
            }
        }catch(IOException e){
            System.out.println("Image(s) not found.");
        }
    }

    public void refreshMap(){
        Point point = new Point();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel label = (JLabel)this.getComponent(j*10 + i);
                point.move(i,j);
                if(__gameLogic.getMap().get(point) == null){
                    label.setIcon(groundIcon);
                }else if(   __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEBODY ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKETAIL ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.FATSNAKEBODY ||
                    __gameLogic.getMap().get(point).getType() == EntityTypes.SNAKEHEAD){
                    label.setIcon(snakeIcon);
                }else if(__gameLogic.getMap().get(point).getType() == EntityTypes.WALL){
                    label.setIcon(wallIcon);
                } else if(__gameLogic.getMap().get(point).getType() == EntityTypes.BASICFRUIT){
                    label.setIcon(fruitIcon);
                }
            }
        }
    }
}