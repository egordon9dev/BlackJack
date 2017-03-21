package blackjack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ethan Gordon
 */
public class SubPanel extends JPanel implements ActionListener {

    /**
     * construct new SubPanel with FlowLayout (default)
     */
    public SubPanel() {
        super();
        setFocusable(true);
        setBackground(new Color(104, 148, 165));
        setDoubleBuffered(true);

        Timer timer = new Timer(20, this);
        timer.start();
    }

    /**
     * construct new SubPanel with given layout
     *
     * @param layout
     */
    public SubPanel(LayoutManager layout) {
        super(layout);
        setFocusable(true);
        setBackground(new Color(104, 148, 165));
        setDoubleBuffered(true);

        Timer timer = new Timer(20, this);
        timer.start();
    }

    /**
     * does nothing
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
