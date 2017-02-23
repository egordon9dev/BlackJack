/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.ArrayList;
/**
 *
 * @author ethan
 */
public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    public Deck() {
        int val;
        for(int i = 1; i <= 14; i++) {
            val = i;
            if(i > 10) val = 10;
            for(int j = 0; j < 4; j++) {
                switch(j) {
                    case 0:
                        cards.add(new Card(val, Suit.hearts));
                        break;
                    case 1:
                        cards.add(new Card(val, Suit.diamonds));
                        break;
                    case 2:
                        cards.add(new Card(val, Suit.spades));
                        break;
                    case 3:
                        cards.add(new Card(val, Suit.clubs));
                        break;
                };
            }
        }
    }
    public ArrayList<Card> getCards() { return cards; }
}
