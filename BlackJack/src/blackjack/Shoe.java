
package blackjack;
import java.util.*;
/**
 *
 * @author ethan
 */
public class Shoe {
    ArrayList<Card> cards = new ArrayList<Card>();
    private final int NUM_OF_DECKS = 5;
    
    public void shuffle() {
        ArrayList<Card> newCards = new ArrayList<Card>();
        for(int i = cards.size(); i > 0; i--) {
            int n = (int)(Math.random() * i);
            newCards.add( cards.get(n) );
            cards.remove(n);
        }
        cards = newCards;
    }
    
    private void refill() {
        cards = new ArrayList<Card>();
        for(int i = 0; i < NUM_OF_DECKS; i++) {
            Deck deck = new Deck();
            for(Card c : deck.getCards()) {
                cards.add(c);
            }
        }
    }
    public Shoe() {
        refill();
        shuffle();
    }
    
    public ArrayList<Card> getCards() { return cards; }
    
    public Card drawCard() {
        if(cards.size() < 52) refill();
        int n = cards.size() - 1;
        Card c = cards.get(n);
        cards.remove(n);
        return c;
    }
}
