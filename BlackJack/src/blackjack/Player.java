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
public class Player {
    private boolean active = true;
    private boolean standing = false;
    private double money = 0;
    private double bet = 0;
    public boolean isActive() { return active; }
    public void setActive(boolean b) { active = b; }
    public double getBet() { return bet; }
    public void setBet(double bet) { this.bet = bet; }
    public void doubleDown() {
        betMoney(bet);
        bet *= 2;
    }
    public boolean isStanding() { return standing; }
    public void setStanding(boolean b) { standing = b; }
    public void betMoney(double bet) { money -= bet; }
    public void earn(double earnings) { money += earnings; }
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    
    private Hand hand = new Hand();
    public Hand getHand() { return hand; }
    public void resetHand() { hand = new Hand(); }
    public void reset() {
        resetHand();
        standing = false;
        active = true;
    }
    public Player(double money) {
        this.money = money;
    }
    
    @Override
    public String toString() {
        return hand.toString();
    }
}
