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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import snake.Main;

public class Menu extends JPanel {
    private HighScores hs;
    private JPanel menu;

    public Menu() {
        hs = new HighScores();
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(320, 240));
        menu = new JPanel(new GridLayout(4, 1));

        JButton b = new JButton("New Game");
        b.addActionListener(getNewGameListener());
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

    private ActionListener getNewGameListener() {
        return new ActionListener() {
            private Logic logic;
            private Gui gui;
            @Override
            public void actionPerformed(ActionEvent e) {
                logic = new Logic();
                gui = new Gui(logic);
                
                Container rp = Menu.this.getRootPane().getContentPane();
                rp.removeAll();
                rp.add(gui);
                rp.revalidate();
                Main m = (Main)SwingUtilities.getWindowAncestor(gui);
                m.setGame(gui, logic);
                SwingUtilities.getWindowAncestor(gui).requestFocus();
            }
        };
    }

    private ActionListener getReturnToMenuListener(JPanel from) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                from.removeAll();
                Menu.this.remove(from);
                Menu.this.add(menu, BorderLayout.CENTER);
                Menu.this.repaint();
                Menu.this.validate();
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
            

            @Override
            public void actionPerformed(ActionEvent e) {
                Point maps = Settings.getMapSize();
                JPanel p = new JPanel(new GridLayout(15, 1));

                p.add(new JLabel("UserName:"));
                JTextField tf = new JTextField(Settings.getName());
                p.add(tf);

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

                p.add(new JLabel("Number of walls (0-100) (max 20% of tiles)"));
                JLabel lw = new JLabel("Current: " + Settings.getWalls());
                p.add(lw);
                JSlider sw = new JSlider(0, 100, Settings.getWalls());
                sw.addChangeListener(getChangeListener(lw));
                p.add(sw);

                p.add(new JLabel("Speed of the game (1-10)"));
                JLabel ls = new JLabel("Current: " + Settings.getSpeed());
                p.add(ls);
                JSlider ss = new JSlider(1, 10, Settings.getSpeed());
                ss.addChangeListener(getChangeListener(ls));
                p.add(ss);

                JButton b = new JButton("Save and Return");
                b.addActionListener(getSaveListener(sx, sy, sw, ss, tf));
                b.addActionListener(getReturnToMenuListener(p));
                p.add(b);

                Menu.this.remove(menu);
                Menu.this.add(p);
                Menu.this.validate();
            }
        };
    }

    private ActionListener getSaveListener(JSlider x, JSlider y, JSlider w, JSlider s, JTextField tf) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving: x=" + x.getValue() + ", y=" + y.getValue());
                Settings.setMapSize(x.getValue(), y.getValue());
                Settings.setName(tf.getText());
                Settings.setSpeed(s.getValue());

                int max = x.getValue()*y.getValue()/5;
                int wall = w.getValue();
                Settings.setWalls(wall>max?max:wall);
                System.out.println("Walls: "+Settings.getWalls());
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