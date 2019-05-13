package snake.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Menu extends JPanel {
    private HighScores hs;
    public Menu(Gui g) {
        hs = new HighScores();
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(320, 240));
        JPanel menu = new JPanel(new GridLayout(4,1));

        JButton b = new JButton("New Game");
        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Container rp = Menu.this.getRootPane().getContentPane();
                rp.removeAll();
                rp.add(g);
                rp.revalidate();
                SwingUtilities.getWindowAncestor(g).requestFocus();
            }
        });
        menu.add(b);

        b = new JButton("High Scores");
        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.remove(menu);
                JPanel p = new JPanel(new BorderLayout());
                JButton hsb = new JButton("Return");
                hsb.addActionListener(new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Menu.this.remove(p);
                        Menu.this.add(menu, BorderLayout.CENTER);
                        Menu.this.repaint();
                        Menu.this.validate();
                        hs.save();
                    }
                });
                p.add(hsb, BorderLayout.SOUTH);
                List<PersonScore> l = hs.getHighScores();
                Object o[][] = new Object[l.size()][];int i = 0;
                for (PersonScore ps : l) {
                    o[i++] = new Object[] {ps.getName(), ps.getScore()};
                }Object[] cn = new Object[] {"Player", "Score"};
                JTable t = new JTable(o, cn);
                //! My Precious
                
                p.add(t, BorderLayout.CENTER);
                Menu.this.add(p, BorderLayout.CENTER);
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