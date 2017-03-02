/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author ethan
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubPanel extends JPanel implements ActionListener{
    
    public SubPanel() {
        super();
        setFocusable(true);
        setBackground(new Color(104, 148, 165));
        setDoubleBuffered(true);
        
        Timer timer = new Timer(20, this);
        timer.start();
    }
    
    public SubPanel(LayoutManager layout) {
        super(layout);
        setFocusable(true);
        setBackground(new Color(104, 148, 165));
        setDoubleBuffered(true);
        
        Timer timer = new Timer(20, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}