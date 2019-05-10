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
    private BufferedImage fruitImg;
    private BufferedImage groundImg;
    private BufferedImage snakeImg;
    private BufferedImage wallImg;
    private Logic __gameLogic;

    public Gui(Logic gm) {
        __gameLogic = gm;
        this.setLayout(new GridLayout(10, 10, 0, 0));
        try {
            InputStream url = this.getClass().getResourceAsStream("/fruit.png");
            fruitImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/ground.PNG");
            groundImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/snake.PNG");
            snakeImg = ImageIO.read(url);
            url = this.getClass().getResourceAsStream("/wall.PNG");
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
    }
}