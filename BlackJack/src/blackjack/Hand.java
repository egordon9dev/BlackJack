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
    public ArrayList<Card> getCards() { return cards; }
    private boolean bust = false;
    private boolean charlie = false;
    private boolean blackjack = false;
    public boolean isBust() { return bust; }
    public boolean isCharlie() { return charlie; }
    public boolean isBlackjack() { return blackjack; }
    
    public int getVal() {
        int val = 0;
        boolean ace = false;
        for(int i = 0; i < cards.size(); i++) {
            int n = cards.get(i).getVal();
            if(n == 1) ace = true;
            val += n;
        }
        if(ace && val <= 11) val += 10;
        
        return val;
    }
    
    public void drawCard(Card c) { 
        cards.add(c);
        if(getVal() > 21) bust = true;
        if(cards.size() >= 5) charlie = true;
        if( (cards.size() == 2) &&
            (  (cards.get(0).getVal() == 10 && cards.get(1).getVal() == 1) ||
               (cards.get(0).getVal() == 1 && cards.get(1).getVal() == 10)
            )
          )     {
            blackjack = true;
        }
                
    }
    
    public void clear() {
        cards = new ArrayList<Card>();
        bust = false;
    }
    @Override
    public String toString() {
        String s = "";
        for(Card i : cards) {
            s += i + "\t";
        }
        return s;
    }
}
