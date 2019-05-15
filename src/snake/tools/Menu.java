package snake.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JPanel {
    private HighScores hs;
    private JPanel menu;

    public Menu(Gui g) {
        hs = new HighScores();
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(320, 240));
        menu = new JPanel(new GridLayout(4, 1));

        JButton b = new JButton("New Game");
        b.addActionListener(getNewGameListener(g));
        menu.add(b);

        b = new JButton("High Scores");
        b.addActionListener(getHighScoreListener());
        menu.add(b);

        b = new JButton("Settings");
        b.addActionListener(getSettingsListener());
        menu.add(b);

        b = new JButton("Exit");
        b.addActionListener(new ActionListener() {

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

    private ActionListener getNewGameListener(Gui g) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Container rp = Menu.this.getRootPane().getContentPane();
                rp.removeAll();
                rp.add(g);
                rp.revalidate();
                SwingUtilities.getWindowAncestor(g).requestFocus();
            }
        };
    }

    private ActionListener getReturnToMenuListener(JPanel from) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.remove(from);
                Menu.this.add(menu, BorderLayout.CENTER);
                Menu.this.repaint();
                Menu.this.validate();
                hs.save();
            }
        };
    }

    private ActionListener getHighScoreListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.this.remove(menu);
                JPanel p = new JPanel(new BorderLayout());
                JButton hsb = new JButton("Return");
                hsb.addActionListener(getReturnToMenuListener(p));
                p.add(hsb, BorderLayout.SOUTH);
                List<PersonScore> l = hs.getHighScores();
                Object o[][] = new Object[l.size()][];
                int i = 0;
                for (PersonScore ps : l) {
                    o[i++] = new Object[] { ps.getName(), ps.getScore() };
                }
                Object[] cn = new Object[] { "Player", "Score" };
                JTable t = new JTable(o, cn);
                // ! My Precious

                p.add(t, BorderLayout.CENTER);
                Menu.this.add(p, BorderLayout.CENTER);
                // Menu.this.repaint();
                Menu.this.validate();
            }
        };
    }

    private ActionListener getSettingsListener() {
        return new ActionListener() {
            Point maps = Settings.getMapSize();

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = new JPanel(new GridLayout(8, 1));

                p.add(new JLabel("Width of the map (5-100)"));
                JLabel lx = new JLabel("Current: " + maps.x);
                p.add(lx);
                JSlider sx = new JSlider(5, 100, maps.x);
                sx.addChangeListener(getChangeListener(lx));
                p.add(sx);

                p.add(new JLabel("Height of the map(5-100)"));
                JLabel ly = new JLabel("Current: " + maps.y);
                p.add(ly);
                JSlider sy = new JSlider(5, 100, maps.y);
                sy.addChangeListener(getChangeListener(ly));
                p.add(sy);

                JButton b = new JButton("Save");
                b.addActionListener(getSaveListener(sx, sy));
                p.add(b);
                b = new JButton("Return");
                b.addActionListener(getReturnToMenuListener(p));
                p.add(b);

                Menu.this.remove(menu);
                Menu.this.add(p);
                Menu.this.validate();
            }
        };
    }

    private ActionListener getSaveListener(JSlider x, JSlider y) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving: x=" + x.getValue() + ", y=" + y.getValue());
                Settings.setMapSize(x.getValue(), y.getValue());
            }
        };

    }

    private ChangeListener getChangeListener(JLabel l) {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                l.setText("Current: " + ((JSlider)e.getSource()).getValue());
            }
        };
    }
}