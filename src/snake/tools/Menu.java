package snake.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Menu extends JPanel {
    public Menu() {
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(320, 240));
        JPanel menu = new JPanel(new GridLayout(4,1));

        JButton b = new JButton("New Game");
        menu.add(b);

        b = new JButton("High Scores");
        menu.add(b);

        b = new JButton("Settings");
        menu.add(b);

        b = new JButton("Exit");
        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Window w = SwingUtilities.getWindowAncestor(Menu.this);
                w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
            }
        });
        menu.add(b);
        
        
        this.add(menu, BorderLayout.CENTER);
        this.add(new JLabel("Made for ELTE Project Tools Course @ 2019"), BorderLayout.SOUTH);
        this.add(new JLabel("[PUT LOGO HERE]"), BorderLayout.NORTH);
        
    }
}