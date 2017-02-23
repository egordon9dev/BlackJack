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
public class Hand {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private boolean bust = false;
    
    public boolean isBust() { return bust; }
    
    public int getVal() {
        int val = 0;
        for(int i = 0; i < cards.size(); i++) {
            val += cards.get(i).getval();
        }
        return val;
    }
    
    public void drawCard(Card c) { 
        cards.add(c);
        if(getVal() > 21) bust = true;
    }
    
    public void clear() {
        cards = new ArrayList<Card>();
        bust = false;
    }
}
