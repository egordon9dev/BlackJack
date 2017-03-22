package blackjack;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Ethan Gordon
 */
public class Dealer {

    private Hand hand;
    private boolean active;

    /**
     * @return dealers hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * resets hand
     */
    public void resetHand() {
        hand = new Hand();
    }

    /**
     * prepares fields for next round
     */
    public void nextRound() {
        active = true;
        resetHand();
    }

    /**
     * @return whether or not the dealer is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * updates activity flag
     */
    public void updateActivity() {
        if (hand.isCharlie()
                || hand.isBust()
                || hand.isBlackjack()
                || hand.getVal() >= 17) {
            active = false;
        }
    }

    /**
     * makes a new dealer and sets it up
     */
    public Dealer() {
        active = true;
        hand = new Hand();
    }

    /**
     * @return string representation of dealer with first card hidden
     */
    @Override
    public String toString() {
        String s = hand.toString();
        return "??????" + s.substring(s.indexOf('\t'));
    }

    /**
     * @param hideFirst flag that says whether or not to hide the first card
     * @return an ArrayList of sprites to be shown on the screen
     */
    public ArrayList<BufferedImage> createCardClips(boolean hideFirst) {
        ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
        ArrayList<Card> cards = hand.getCards();
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && hideFirst) {
                imgs.add(GameGUI.cardSheet.getSubimage(0, 4 * 63, 44, 63));
                continue;
            }
            int n = -1;
            switch (cards.get(i).getSuit()) {
                case hearts:
                    n = 0;
                    break;
                case diamonds:
                    n = 1;
                    break;
                case clubs:
                    n = 2;
                    break;
                case spades:
                    n = 3;
                    break;
            }
            imgs.add(GameGUI.cardSheet.getSubimage((cards.get(i).getID() - 1) * 44, n * 63, 44, 63));
        }
        return imgs;
    }
}
