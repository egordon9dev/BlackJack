/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    public ArrayList<BufferedImage> createCardClips() {
        ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
        ArrayList<Card> cards = hand.getCards();
        for(int i = 0; i < cards.size(); i++) {
            imgs.add(GameGUI.cardSheet.getSubimage((cards.get(i).getID()-1)*44, 0, 44, 63));
        }
        return imgs;
    }
}
