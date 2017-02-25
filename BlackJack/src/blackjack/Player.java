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
    private double money = 0;
    public void bet(double bet) { money -= bet; }
    public void earn(double earnings) { money += earnings; }
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    
    private Hand hand = new Hand();
    public Hand getHand() { return hand; }
    public void resetHand() { hand = new Hand(); }
    
    public Player(double money) {
        this.money = money;
    }
    
}
