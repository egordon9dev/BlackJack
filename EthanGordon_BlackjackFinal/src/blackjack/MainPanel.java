package blackjack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ethan Gordon
 */
public class MainPanel extends JPanel implements ActionListener {

    public static final Color color = new Color(183, 200, 206);

    /**
     * constructs a new main panel with BorderLayout
     */
    public MainPanel() {
        super(new BorderLayout());
        setFocusable(true);
        setBackground(color);
        setDoubleBuffered(true);

        Timer timer = new Timer(20, this);
        timer.start();
    }

    /**
     * doesn't do anything
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * repaints screen
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
