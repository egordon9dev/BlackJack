/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import static blackjack.GameGUI.joinCards;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ethan
 */
public class GraphicDealer extends Dealer{
    private static ArrayList<ArrayList<BufferedImage>> dealerCardClips;
    private static JLabel labelDealerCards, labelDealer;
    
    public JLabel getLabelDealer() { return labelDealer; }
    public JLabel getLabelDealerCards() { return labelDealerCards; }
    
    public void updateDealerCardClips(boolean b) {
        dealerCardClips.clear();
        dealerCardClips.add(createCardClips(b));
        labelDealerCards.setIcon(new ImageIcon(joinCards(dealerCardClips)));
    }
    public GraphicDealer() {
        super();
        dealerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        labelDealer = new JLabel("Dealer: ");
        labelDealerCards = new JLabel();
    }
}
