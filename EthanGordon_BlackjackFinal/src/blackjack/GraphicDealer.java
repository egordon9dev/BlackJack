package blackjack;

import static blackjack.GameGUI.joinCards;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Ethan Gordon
 */
public class GraphicDealer extends Dealer {

    private static ArrayList<ArrayList<BufferedImage>> dealerCardClips;
    private static JLabel labelDealerCards, labelDealer;

    /**
     * @return dealer label
     */
    public JLabel getLabelDealer() {
        return labelDealer;
    }

    /**
     * @return label with card image clips
     */
    public JLabel getLabelDealerCards() {
        return labelDealerCards;
    }

    /**
     * updates dealers card image clips
     *
     * @param b flag that signifies whether or not to hide the first card
     */
    public void updateDealerCardClips(boolean b) {
        dealerCardClips.clear();
        dealerCardClips.add(createCardClips(b));
        labelDealerCards.setIcon(new ImageIcon(joinCards(dealerCardClips)));
    }

    /**
     * constructs a new GraphicDealer
     */
    public GraphicDealer() {
        super();
        dealerCardClips = new ArrayList<ArrayList<BufferedImage>>();
        labelDealer = new JLabel("Dealer: ");
        labelDealerCards = new JLabel();
    }
}
