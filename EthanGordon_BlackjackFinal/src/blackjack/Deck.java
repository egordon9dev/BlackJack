package blackjack;

import java.util.ArrayList;

/**
 * @author Ethan Gordon
 */
public class Deck {

    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * declares new deck and fills it with cards (un-shuffled)
     */
    public Deck() {
        int val;
        for (int i = 1; i <= 13; i++) {
            val = i;
            if (val > 10) {
                val = 10;
            }
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        cards.add(new Card(val, i, Suit.hearts));
                        break;
                    case 1:
                        cards.add(new Card(val, i, Suit.diamonds));
                        break;
                    case 2:
                        cards.add(new Card(val, i, Suit.spades));
                        break;
                    case 3:
                        cards.add(new Card(val, i, Suit.clubs));
                        break;
                };
            }
        }
    }

    /**
     * @return cards in deck
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
}
