/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/*
 *
 * @author ethan
 */
public class Dealer {
    private Hand hand = new Hand();
    private boolean active = true;
    public Hand getHand() { return hand; }
    public void resetHand() { hand = new Hand(); }
    public void reset() {
        resetHand();
        active = true;
    }
    public boolean isActive() { return active; }
    public void setActive(boolean b) { active = b; }
    
    public Dealer() {
        
    }
    @Override
    public String toString() {
        String s = hand.toString();
        return "??????" + s.substring(s.indexOf('\t'));
    }
}
