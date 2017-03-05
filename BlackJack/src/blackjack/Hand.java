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
    public void setCards(ArrayList<Card> cards) { this.cards = cards; }
    private boolean bust = false;
    private boolean charlie = false;
    private boolean blackjack = false;
    private boolean splitable = false;
    public boolean isSplitable() { return splitable; }
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
        splitable = false;
        if(cards.size() == 2) {
            Card card0 = cards.get(0);
            Card card1 = cards.get(1);
            if((card0.getVal() == 10 && card1.getVal() == 1) ||
               (card0.getVal() == 1 && card1.getVal() == 10)) {
                blackjack = true;
            }
            if(card0.getVal() == card1.getVal()) splitable = true;
        }
                
    }
    
    public void clear() {
        cards = new ArrayList<Card>();
        bust = false;
    }
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < cards.size(); i++) {
            s += cards.get(i) + "\t";
        }
        return s;
    }
}
