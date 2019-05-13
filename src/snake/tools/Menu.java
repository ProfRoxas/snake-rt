package snake.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class Menu extends JPanel {
    public Menu() {
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(320, 240));
        JPanel menu = new JPanel(new GridLayout(4,1));

        JButton b = new JButton("New Game");
        menu.add(b);

        b = new JButton("High Scores");
        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.remove(menu);
                System.out.println("menu removed");
                JPanel p = new JPanel(new BorderLayout());
                JButton hsb = new JButton("Return");
                hsb.addActionListener(new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Menu.this.remove(p);
                        Menu.this.add(menu, BorderLayout.CENTER);
                        Menu.this.repaint();
                        Menu.this.validate();
                    }
                });
                p.add(hsb, BorderLayout.SOUTH);
                JTable t = new JTable(new DefaultTableModel(
                                        new Vector<>(new HighScores().getHighScores()),
                                        new Vector<>(new LinkedList<String>())));
                p.add(t, BorderLayout.CENTER);
                Menu.this.add(p, BorderLayout.CENTER);
                System.out.println("Scorepanel added");
                //Menu.this.repaint();
                Menu.this.validate();
            }
        });
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