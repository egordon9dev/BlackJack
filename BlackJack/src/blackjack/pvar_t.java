/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ethan
 */
class pvar_t {
    public JPanel northPanel;
    public JPanel westPanel;
    public ArrayList<ArrayList<BufferedImage>> playerCardClips;
    public JLabel labelPlayerCards;
    public JLabel labelMoney, labelBet, labelIns, labelPlayer;
    public JTextField textMoney, textBet, textIns;
    public String strBet = "invalid", strIns = "invalid";
}