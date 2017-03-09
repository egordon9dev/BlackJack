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
    private boolean splitable = false;
    public boolean isSplitable() {
        return (cards.size() == 2 && cards.get(0).getVal() == cards.get(1).getVal());
    }
    public boolean isBust() { return getVal() > 21; }
    public boolean isCharlie() { return (cards.size() >= 5 && !isBust()); }
    public boolean isBlackjack() { return (cards.size() == 2 && getVal() == 21); }
    
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
    }
    
    public void clear() {
        cards = new ArrayList<Card>();
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
