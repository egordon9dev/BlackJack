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
public class PartialPlayer {
    private boolean active;
    private boolean standing;
    private boolean doubled;
    public boolean isDoubled() { return doubled; }
    public void setDoubled() { doubled = true; }
    public boolean isActive() { return active; }
    public void setActive(boolean b) { active = b; }
    public boolean isStanding() { return standing; }
    public void setStanding(boolean b) { standing = b; }
    
    private Hand hand;
    public Hand getHand() { return hand; }
    public void setHand(Hand hand) {
        this.hand = hand;
    }
    public PartialPlayer() {
        hand = new Hand();
        standing = false;
        doubled = false;
        active = true;
    }
    
    @Override
    public String toString() {
        return hand.toString();
    }
}
