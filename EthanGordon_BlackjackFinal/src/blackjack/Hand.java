package blackjack;

import java.util.ArrayList;

/**
 * @author Ethan Gordon
 */
public class Hand {

    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * @return cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * sets cards
     *
     * @param cards
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * @return whether or not the hand can be split
     */
    public boolean isSplitable() {
        return (cards.size() == 2 && cards.get(0).getVal() == cards.get(1).getVal());
    }

    /**
     * @return whether or not the hand has busted
     */
    public boolean isBust() {
        return getVal() > 21;
    }

    /**
     * @return whether or not the hand is a five-card-charlie
     */
    public boolean isCharlie() {
        return (cards.size() >= 5 && !isBust());
    }

    /**
     * @return whether or not the hand is a blackjack
     */
    public boolean isBlackjack() {
        return (cards.size() == 2 && getVal() == 21);
    }

    /**
     * @return total value of all the cards in the hand
     */
    public int getVal() {
        int val = 0;
        boolean ace = false;
        for (int i = 0; i < cards.size(); i++) {
            int n = cards.get(i).getVal();
            if (n == 1) {
                ace = true;
            }
            val += n;
        }
        if (ace && val <= 11) {
            val += 10;
        }

        return val;
    }

    /**
     * draws a card
     *
     * @param c
     */
    public void drawCard(Card c) {
        cards.add(c);
    }

    /**
     * clears the hand
     */
    public void clear() {
        cards = new ArrayList<Card>();
    }

    /**
     * @return string representation of the hand
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < cards.size(); i++) {
            s += cards.get(i) + "\t";
        }
        return s;
    }
}
