
package blackjack;
import java.util.*;
/**
 *
 * @author ethan
 */
public class Shoe {
    ArrayList<Card> cards = new ArrayList<Card>();
    private final int NUM_OF_DECKS;
    /**
     * shuffles cards
     */
    public void shuffle() {
        ArrayList<Card> newCards = new ArrayList<Card>();
        for(int i = cards.size(); i > 0; i--) {
            int n = (int)(Math.random() * i);
            newCards.add( cards.get(n) );
            cards.remove(n);
        }
        cards = newCards;
    }
    /**
     * declares a new ArrayList of cards and fills it
     */
    private void refill() {
        cards = new ArrayList<Card>();
        for(int i = 0; i < NUM_OF_DECKS; i++) {
            Deck deck = new Deck();
            for(Card c : deck.getCards()) {
                cards.add(c);
            }
        }
    }
    
    /**
     * @param x number of decks
     */
    public Shoe(int x) {
        NUM_OF_DECKS = x;
        refill();
        shuffle();
    }
    
    /**
     * @return cards
     */
    public ArrayList<Card> getCards() { return cards; }
    
    /**
     * @return next card in the shoe
     */
    public Card drawCard() {
        if(cards.size() < 52 * (NUM_OF_DECKS-1)) {
            refill();
            shuffle();
        }
        int n = cards.size() - 1;
        Card c = cards.get(n);
        cards.remove(n);
        return c;
    }
}
