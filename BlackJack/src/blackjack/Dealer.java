/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author ethan
 */
public class Dealer {
    private Hand hand = new Hand();
    public Hand getHand() { return hand; }
    public void resetHand() { hand = new Hand(); }
    
    public Dealer() {
        
    }
}
